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
}
