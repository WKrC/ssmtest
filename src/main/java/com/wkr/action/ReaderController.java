package com.wkr.action;

import com.wkr.Tools.MyTools;
import UHF.Reader18;
import com.wkr.bean.ReaderBean;
import com.wkr.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ReaderController {

    @Autowired
    ReaderService readerService;

    /**
     * 查找阅读器
     * @return
     */
    @RequestMapping(value = "/fetchReaderControl", method = RequestMethod.POST)
    @ResponseBody
    public ReaderBean fetchReaderControl() {
        ReaderBean readerBean = new ReaderBean();
        try {
            readerBean.setReaderSetMAC(MyTools.getLocalMac());
        }catch (Exception e){
            e.getStackTrace();
        }
        ReaderBean readerResult = readerService.fetchReaderByMAC(readerBean);
        if (readerResult == null) {
            return null;
        }else {
            return readerResult;
        }

    }

    /**
     * 保存或更新阅读器设置
     * @param readerBean 阅读器实体类对象
     * @return 结果码
     */
    @RequestMapping(value = "/saveOrUpdateReaderSettingController", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveOrUpdateReaderSettingController(ReaderBean readerBean) {
        Map<String, Object> result = new HashMap<>();
        try{
            ReaderBean temp = new ReaderBean();//临时参数作为后面数据库的查找参数
            temp.setReaderSetMAC(MyTools.getLocalMac());
            ReaderBean rb = readerService.fetchReaderByMAC(temp);//包含本机MAC的阅读器对象
            System.loadLibrary("UHF_Reader18");
            Reader18 reader18 = new Reader18();
            if (rb != null) {//更新设置
                /*readerBean.setReaderSetMAC(rb.getReaderSetMAC());
                readerBean.setReaderHEXAddr(rb.getReaderHEXAddr());
                readerService.updateReaderSetting(readerBean);*/
                //错误模板 两个对象主键相同 hibernate是不允许两个主键相同的对象在同一个session里面
                //同一个session一般指同一个方法内
                /**
                 * 以下为正确模板
                 */
                if (rb.getIsOnline() == 1) {//阅读器在线
                    int[] input = {0, 5};
                    input[0] = rb.getReaderHEXAddr();
                    int[] output= reader18.AutoOpenComPort(input);
                    if (output[0] == 0) {
                        rb.setReaderAddress(readerBean.getReaderAddress());
                        rb.setReaderName(readerBean.getReaderName());
                        rb.setReaderGPS(readerBean.getReaderGPS());
                        readerService.updateReaderSetting(rb);
                        result.put("resultCode", 2);
                    }else {
                        result.put("resultCode", -2);
                    }
                }else {
                    result.put("resultCode", -4);//阅读器不在线
                }
            }else {//保存设置
                List<ReaderBean> readerBeanList =  readerService.getTotalCount();
                readerBean.setReaderHEXAddr(readerBeanList.size() + 1);
                readerBean.setReaderSetMAC(MyTools.getLocalMac());
                int[] input = {0, 5};
                int[] output= reader18.AutoOpenComPort(input);
                if (output[0] == 0) {//连接阅读器成功
                    int[] arr_writeComAdr_temp = {0, 0, 5};
                    arr_writeComAdr_temp[1] = readerBean.getReaderHEXAddr();
                    int i = reader18.WriteComAdr(arr_writeComAdr_temp);
                    if (i != 0){//读写器写入地址失败
                        result.put("resultCode", -3);
                    }else {//写入读写器地址成功
                        readerBean.setIsOnline(1);
                        readerService.saveReaderSetting(readerBean);
                        result.put("resultCode", 1);
                        input[0] = readerBean.getReaderHEXAddr();//更新阅读器地址
                        reader18.AutoOpenComPort(input);//更新完地址后重新连接
                        //获取标签信息
                        int[] arr3 = {input[0], 2, 0, 1, 0x03, 0, 4, 0, 0, 0, 0, 0, 0, 0, 5};
                        int[] getTagData = reader18.ReadCard_G2(arr3);
                        System.out.println(Arrays.toString(getTagData));
                    }
                }else {//读写器连接失败
                    result.put("resultCode", -2);
                }

            }
        }catch (Exception e) {
            result.put("resultCode", -1);
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/ReaderIsOnline",  method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> ReaderIsOnline(){
        Map<String, Object> result = new HashMap<>();
        try {
            ReaderBean temp = new ReaderBean();
            temp.setReaderSetMAC(MyTools.getLocalMac());
            ReaderBean readerBean = readerService.fetchReaderByMAC(temp);
            if (readerBean == null) {
                result.put("resultCode", -2);//阅读器未设置
            }else {
                if (readerBean.getIsOnline() == 1) {
                    result.put("resultCode", 1);//阅读器在线
                } else {
                    result.put("resultCode", 0);//阅读器不在线
                }
            }
        } catch (Exception e) {
            result.put("resultCode", -1);
        }
        return result;
    }
}
