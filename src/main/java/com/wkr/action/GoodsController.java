package com.wkr.action;

import UHF.Reader18;
import com.wkr.Tools.MyTools;
import com.wkr.bean.GoodsBean;
import com.wkr.bean.LogisticsInfoBean;
import com.wkr.bean.ReaderBean;
import com.wkr.dao.GoodsDao;
import com.wkr.service.GoodsService;
import com.wkr.service.LogisticsService;
import com.wkr.service.ReaderService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    LogisticsService logisticsService;

    @Autowired
    ReaderService readerService;

    @RequestMapping(value = "/jijianControl", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> jijianControl(GoodsBean goodsBean){
        System.loadLibrary("UHF_Reader18");
        Map<String, Object> resultMap = new HashMap<>();
        Reader18 reader18 = new Reader18();
        ReaderBean temp = new ReaderBean();
        try {
            temp.setReaderSetMAC(MyTools.getLocalMac());
            ReaderBean readerBean = readerService.fetchReaderByMAC(temp);
            if (readerBean != null) {
                int[] AutoOpenComPort_input_parameter = {readerBean.getReaderHEXAddr(), 5};
                int[] AutoOpenComPort_output_parameter= reader18.AutoOpenComPort(AutoOpenComPort_input_parameter);
                if (AutoOpenComPort_output_parameter[0] == 0) {//阅读器在线
                    String indexCode = MyTools.getIndexCode();
                    goodsBean.setGoodsIndexCode(indexCode);
                    goodsService.saveGoods(goodsBean);
                    LogisticsInfoBean logisticsInfoBean = new LogisticsInfoBean();
                    logisticsInfoBean.setGoodsIndexCode(indexCode);
                    logisticsInfoBean.setGoodsPosition("已交由寄件员处理");//运输开始没到就近的集散中心
                    logisticsInfoBean.setTimeInfo(MyTools.getDateString());
                    logisticsService.insert(logisticsInfoBean);
                    //写入标签EPC号
                    int[] Reader_Data = {readerBean.getReaderHEXAddr(), 0, 0, 0, 0, 8};
                    int[] EPCData = MyTools.StringToArray(indexCode);
                    int[] lastData = {AutoOpenComPort_output_parameter[3]};
                    List WriteEPC_G2_Input_List = new ArrayList(Arrays.asList(Reader_Data));
                    WriteEPC_G2_Input_List.addAll(Arrays.asList(EPCData));
                    WriteEPC_G2_Input_List.addAll(Arrays.asList(lastData));
                    Integer[] WriteEPC_G2_Input_Integer = new Integer[Reader_Data.length + EPCData.length + lastData.length];
                    WriteEPC_G2_Input_List.toArray(WriteEPC_G2_Input_Integer);
                    int[] WriteEPC_G2_Input_Parameter = ArrayUtils.toPrimitive(WriteEPC_G2_Input_Integer);
                    int[] WriteEPC_G2_Outputput_Parameter = reader18.WriteEPC_G2(WriteEPC_G2_Input_Parameter);
                    if (WriteEPC_G2_Outputput_Parameter[0] == 0) {
                        resultMap.put("indexCode", indexCode);//返回EPC号即物流号
                    } else {
                        resultMap.put("indexCode", -3);//设置EPC号失败
                    }
                } else {
                    resultMap.put("indexCode", -2);//阅读器不在线
                }
            } else {
                resultMap.put("indexCode", 0);//阅读器未设置
            }
        } catch (Exception e) {
            resultMap.put("indexCode", -1);
        }
        return resultMap;
    }

    @RequestMapping(value = "/chaxunControl", method = RequestMethod.POST)
    @ResponseBody
    public LogisticsInfoBean chaxunControl(String goodsIndexCode, HttpServletRequest request) {
        LogisticsInfoBean logisticsInfoBean = logisticsService.fetchGoodsByGoodsIndexCode(goodsIndexCode);
        if (logisticsInfoBean != null) {
            request.getSession().setAttribute("goodsIndexCode", goodsIndexCode);
            return logisticsInfoBean;
        }else {
            return  null;
        }
    }

    @RequestMapping(value = "/fetchLogisticsInfoController", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, List<String>> fetchLogisticsInfoController(HttpServletRequest request) {
        String goodsIndexCode = request.getSession().getAttribute("goodsIndexCode").toString();
        LogisticsInfoBean logisticsInfoBean =  logisticsService.fetchGoodsByGoodsIndexCode(goodsIndexCode);
        Map<String, List<String>> map = new HashMap<>();
        List<String> GPSList = new ArrayList<>();
        List<String> PositionList = new ArrayList<>();
        String GPSInfo = logisticsInfoBean.getGoodsGPSInfo();
        if (GPSInfo != "" && GPSInfo != null){
            String[] GPSInfos = GPSInfo.split(";");
            for (String info : GPSInfos) {
                GPSList.add(info);
            }
        }
        String PositionInfo = logisticsInfoBean.getGoodsPosition();
        String timeInfo = logisticsInfoBean.getTimeInfo();
        String[] PositionInfos = PositionInfo.split(";");
        String[] timeInfos = timeInfo.split(";");
        int  i = 0;
        for (String info : PositionInfos) {
            info =  "[" + timeInfos[i] + "]    " + info;
            PositionList.add(info);
            i++;
        }
        map.put("GPSList", GPSList);
        map.put("PositionList", PositionList);
        return map;
    }
}
