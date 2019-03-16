package com.wkr.action;

import com.wkr.bean.GoodsBean;
import com.wkr.bean.LogisticsInfoBean;
import com.wkr.dao.GoodsDao;
import com.wkr.service.GoodsService;
import com.wkr.service.LogisticsService;
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

    @RequestMapping(value = "/jijianControl", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> jijianControl(GoodsBean goodsBean){
        int indexCode = (int)(Math.random()*10000000 + 1000000);
        goodsBean.setGoodsIndexCode(String.valueOf(indexCode));
        goodsService.saveGoods(goodsBean);
        LogisticsInfoBean logisticsInfoBean = new LogisticsInfoBean();
        logisticsInfoBean.setGoodsIndexCode(indexCode);//运输开始没到就近的集散中心
        logisticsInfoBean.setGoodsPosition("已交由寄件员处理");
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.US);
        logisticsInfoBean.setTimeInfo(dateFormat.format(date));
        logisticsService.insert(logisticsInfoBean);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("indexCode", indexCode);
        return resultMap;
    }

    @RequestMapping(value = "/chaxunControl", method = RequestMethod.POST)
    @ResponseBody
    public LogisticsInfoBean chaxunControl(String goodsIndexCode, HttpServletRequest request) {
        List<LogisticsInfoBean> logisticsInfoBeanList = logisticsService.fetchGoodsByGoodsIndexCode(goodsIndexCode);
        if (logisticsInfoBeanList != null && logisticsInfoBeanList.size() > 0) {
            request.getSession().setAttribute("goodsIndexCode", goodsIndexCode);
            return logisticsInfoBeanList.get(0);
        }else {
            return  null;
        }
    }

    @RequestMapping(value = "/fetchLogisticsInfoController", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, List<String>> fetchLogisticsInfoController(HttpServletRequest request) {
        String goodsIndexCode = request.getSession().getAttribute("goodsIndexCode").toString();
        List<LogisticsInfoBean> logisticsInfoBeanList =  logisticsService.fetchGoodsByGoodsIndexCode(goodsIndexCode);
        LogisticsInfoBean logisticsInfoBean = logisticsInfoBeanList.get(0);
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
