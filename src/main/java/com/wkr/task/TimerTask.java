package com.wkr.task;

import UHF.Reader18;
import com.wkr.Tools.MyTools;
import com.wkr.bean.LogisticsInfoBean;
import com.wkr.bean.ReaderBean;
import com.wkr.service.GoodsService;
import com.wkr.service.LogisticsService;
import com.wkr.service.ReaderService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class TimerTask {

    @Autowired
    ReaderService readerService;
    @Autowired
    LogisticsService logisticsService;
    @Autowired
    GoodsService goodsService;

    /**
     * 阅读器定时标签巡查任务
     */
    @Scheduled(cron = "0/3 * * * * ?")//每隔3秒隔行一次
    public void ReaderFetchTagTimerTask() {
        try{
            System.loadLibrary("UHF_Reader18");
            Reader18 reader18 = new Reader18();
            ReaderBean temp = new ReaderBean();
            temp.setReaderSetMAC(MyTools.getLocalMac());
            ReaderBean readerBean = readerService.fetchReaderByMAC(temp);
            if (readerBean != null) {//设置了阅读器
                //连接阅读器函数输入参数
                /**
                 * 数组0：阅读器16进制地址
                 * 数组1：串口通信波特率 5标识57600 bps
                 */
                int[] AutoOpenComPort_input_parameter = {readerBean.getReaderHEXAddr(), 5};
                //连接阅读器返回集
                /**
                 * 数组0：执行标识位 0标识成功
                 * 数组1：使用的端口号
                 * 数组2：阅读器16进制地址
                 * 数组3：端口连接对应句柄
                 */
                int[] AutoOpenComPort_output_parameter= reader18.AutoOpenComPort(AutoOpenComPort_input_parameter);
                if (AutoOpenComPort_output_parameter[0] == 0){ //阅读器连接成功
                    /**读写标签部分代码*/
                    readerService.updateReaderIsOnline(readerBean.getReaderSetMAC(), 1);
                    //询查标签命令输入参数
                    /**
                     * 数组0：阅读器16进制地址
                     * 数组1：端口连接对应句柄
                     */
                    int[] Inventory_G2_input_parameter = {readerBean.getReaderHEXAddr(), AutoOpenComPort_output_parameter[3]};
                    //询查标签命令输出结果集
                    /**
                     * 数组0：执行标志位 1标识询查时间结束前返回
                     * 数组1：标签张数
                     * 数组2：表示返回的总EPC号字节数（每张会多+1）
                     * 其余 ：单张EPC号长度 + EPC号  2 0 10（每位16进制 表示2位）
                     */
                    int[] Inventory_G2_output_parameter = reader18.Inventory_G2(Inventory_G2_input_parameter);
                    for (int i = 3; i < Inventory_G2_output_parameter.length; i++) {
                        Integer[] ReaderHEXAddr = {readerBean.getReaderHEXAddr()};
                        //EPC号数据
                        Integer[] EPCData = new Integer[Inventory_G2_output_parameter[i] + 1];
                        EPCData[0] = Inventory_G2_output_parameter[i];
                        for (int EPCData_i = 1; EPCData_i < EPCData.length; EPCData_i++) {
                            i++;
                            EPCData[EPCData_i] = Inventory_G2_output_parameter[i];
                        }
                        Integer[] otherData = {3, 0, 8, 0, 0, 0, 0, 0, 0, 0, AutoOpenComPort_output_parameter[3]};
                        List ReadCard_G2_Input_List = new ArrayList(Arrays.asList(ReaderHEXAddr));
                        ReadCard_G2_Input_List.addAll(Arrays.asList(EPCData));
                        ReadCard_G2_Input_List.addAll(Arrays.asList(otherData));
                        Integer[] ReadCard_G2_Input_Integer = new Integer[ReaderHEXAddr.length + EPCData.length + otherData.length];
                        ReadCard_G2_Input_List.toArray(ReadCard_G2_Input_Integer);//转化为Integer数组
                        //读取标签数据命令输入数据
                        /**
                         * 数组0：读写器的地址
                         * 数组1：EPC长度
                         * 数组2-EPClength+2：EPC号
                         * 数组EPClength+3：要读取的存储区 3表示用户存储器
                         * 数组EPClength+4：指定要读取的字起始地址。0x00表示从第一个字(第一个16位存储体)开始以此类推
                         * 数组EPClength+5：要读取的字的个数Num。不能设置为0x00，将返回参数错误信息。Num不能超过120
                         * 数组EPClength+6-EPClength+9：访问密码 默认0000
                         * 数组EPClength+10：EPC掩模起始字节地址。默认0x00
                         * 数组EPClength+11：掩模使能标记。默认0 不使能
                         * 数组EPClength+12：掩模使能标记。默认0 不使能
                         * 数组EPClength+13：端口连接对应句柄
                         */
                        //Integer[]转int[]
                        int[] ReadCard_G2_Input_Parameter = ArrayUtils.toPrimitive(ReadCard_G2_Input_Integer);
                        //读取标签数据命令返回集
                        /**
                         * 数组0：0表示成功
                         * 其余 ：数据
                         */
                        int[] ReadCard_G2_Output_Parameter = reader18.ReadCard_G2(ReadCard_G2_Input_Parameter);
                        if (ReadCard_G2_Output_Parameter[ReadCard_G2_Output_Parameter.length - 1] !=
                                Integer.valueOf(String.valueOf(Long.parseLong(String.valueOf(readerBean.getReaderHEXAddr()), 16)))) {
                            //数据库的数据是16进制的  阅读器获得数据是10进制的  要将数据库的16进制转换为10进制
                            //说明未入库
                            int[] otherData_Write = {3, 0, 8, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, AutoOpenComPort_output_parameter[3]};
                            List WriteCard_G2_Input_List = new ArrayList(Arrays.asList(ReaderHEXAddr));
                            WriteCard_G2_Input_List.addAll(Arrays.asList(EPCData));
                            WriteCard_G2_Input_List.addAll(Arrays.asList(otherData_Write));
                            Integer[] WriteCard_G2_Input_Integer = new Integer[ReaderHEXAddr.length + EPCData.length + otherData_Write.length];
                            ReadCard_G2_Input_List.toArray(WriteCard_G2_Input_Integer);//转化为Integer数组
                            //标签写入命令参数
                            /**
                             * 数组0：阅读器地址
                             * 数组1；EPC号长度
                             * 数组2-EPClength + 2：EPC号
                             * 数组EPClength + 3：写入的存储器 3用户存储器
                             * 数组EPClength + 4：指定要读取的字起始地址。默认0x00
                             * 数组EPClength + 5：待写入的字节数Writedatalen（长度必须为偶数字节数）。Writedatalen必须大于0
                             * 数组EPClength + 6 - 数组EPClength + Writedatalen + 5：写入数据
                             * 数组EPClength + Writedatalen + 6 - EPClength + Writedatalen + 9：访问密码 0000
                             * 数组EPClength + Writedatalen + 10：EPC掩模起始字节地址 默认0
                             * 数组EPClength + Writedatalen + 11：掩模使能标记 0 默认不使能
                             * 数组EPClength + Writedatalen + 12：掩模使能标记 0 默认不使能
                             * 数组EPClength + Writedatalen + 13：端口连接对应句柄
                             */
                            int[] WriteCard_G2_Input_Parameter = ArrayUtils.toPrimitive(WriteCard_G2_Input_Integer);
                            //标签写入返回集
                            /**
                             * 数组0：0表示成功
                             */
                            int[] WriteCard_G2_Output_Parameter = reader18.WriteCard_G2(WriteCard_G2_Input_Parameter);
                            if (WriteCard_G2_Output_Parameter[0] == 0) {
                                //入库
                                String indexCode = MyTools.ArrayToString(EPCData);
                                LogisticsInfoBean logisticsInfoBean  = logisticsService.fetchGoodsByGoodsIndexCode(indexCode);
                                String nowGPS = logisticsInfoBean.getGoodsGPSInfo() + ";" + readerBean.getReaderGPS();
                                logisticsInfoBean.setGoodsGPSInfo(nowGPS);
                                String nowAddr = logisticsInfoBean.getGoodsPosition() + ";" + readerBean.getReaderName();
                                logisticsInfoBean.setGoodsPosition(nowAddr);
                                String nowDate = logisticsInfoBean.getTimeInfo() + ";" + MyTools.getDateString();
                                logisticsInfoBean.setTimeInfo(nowDate);
                                logisticsService.updateGoods(logisticsInfoBean);
                                goodsService.setNowPosition(readerBean.getReaderName(), indexCode);
                            }
                        }
                    }
                } else if (AutoOpenComPort_output_parameter[0] != 0 && readerBean.getIsOnline() != 0){//阅读器连接失败更新阅读器状态
                    readerService.updateReaderIsOnline(readerBean.getReaderSetMAC(), 0);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }


    }

}
