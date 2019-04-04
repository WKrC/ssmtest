package com.wkr.dao;

import com.wkr.bean.GoodsBean;

import java.util.List;

public interface GoodsDao {
    void saveGoods(GoodsBean goodsBean);
    List<GoodsBean> fetchGoods(String goodsIndexCode);
    List<GoodsBean> fetchAll(String position);
    void ConfirmReceive(String indexCode);
    void setNowPosition(String ReaderName, String indexCode);
}
