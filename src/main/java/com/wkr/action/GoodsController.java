package com.wkr.action;

import com.wkr.bean.GoodsBean;
import com.wkr.dao.GoodsDao;
import com.wkr.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @RequestMapping(value = "/jijianControl", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> jijianControl(GoodsBean goodsBean){
        int indexCode = (int)(Math.random()*10000000 + 1000000);
        goodsBean.setGoodsIndexCode(String.valueOf(indexCode));
        goodsService.saveGoods(goodsBean);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("indexCode", indexCode);
        return resultMap;
    }

    @RequestMapping(value = "/chaxunControl", method = RequestMethod.POST)
    @ResponseBody
    public List<GoodsBean> chaxunControl(String goodsIndexCode){
        List<GoodsBean> goodsBeanList = goodsService.fetchGoods(goodsIndexCode);
        return goodsBeanList;
    }
}
