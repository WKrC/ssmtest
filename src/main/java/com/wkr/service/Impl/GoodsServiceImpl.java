package com.wkr.service.Impl;

import com.wkr.bean.GoodsBean;
import com.wkr.dao.GoodsDao;
import com.wkr.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsDao goodsDao;

    @Override
    public void saveGoods(GoodsBean goodsBean) {
        goodsDao.saveGoods(goodsBean);
    }

    @Override
    public List<GoodsBean> fetchGoods(String goodsIndexCode) {
        return goodsDao.fetchGoods(goodsIndexCode);
    }

    @Override
    public List<GoodsBean> fetchAll(String position) {
        return goodsDao.fetchAll(position);
    }

    @Override
    public void ConfirmReceive(String indexCode) {
        goodsDao.ConfirmReceive(indexCode);
    }

    @Override
    public void setNowPosition(String ReaderName, String indexCode) {
        goodsDao.setNowPosition(ReaderName, indexCode);
    }
}
