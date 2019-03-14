package com.wkr.service.Impl;

import com.wkr.bean.LogisticsInfoBean;
import com.wkr.dao.LogisticsDao;
import com.wkr.service.LogisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogisticsServiceImpl implements LogisticsService {

    @Autowired
    LogisticsDao logisticsDao;

    @Override
    public List<LogisticsInfoBean> fetchGoodsByGoodsIndexCode(String goodsIndexCode) {
        return logisticsDao.fetchGoodsByGoodsIndexCode(goodsIndexCode);
    }

    @Override
    public void updateGoods(LogisticsInfoBean logisticsInfoBean) {
        logisticsDao.updateGoods(logisticsInfoBean);
    }

    @Override
    public void insert(LogisticsInfoBean logisticsInfoBean) {
        logisticsDao.insert(logisticsInfoBean);
    }
}
