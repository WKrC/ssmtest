package com.wkr.service;

import com.wkr.bean.GoodsBean;

import java.util.List;

public interface GoodsService {
    void saveGoods(GoodsBean goodsBean);
    List<GoodsBean> fetchGoods(String goodsIndexCode);
}
