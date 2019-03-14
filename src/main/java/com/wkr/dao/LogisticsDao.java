package com.wkr.dao;

import com.wkr.bean.LogisticsInfoBean;

import java.util.List;

public interface LogisticsDao {
    List<LogisticsInfoBean> fetchGoodsByGoodsIndexCode(String goodsIndexCode);
    void updateGoods(LogisticsInfoBean logisticsInfoBean);
    void insert(LogisticsInfoBean logisticsInfoBean);
}
