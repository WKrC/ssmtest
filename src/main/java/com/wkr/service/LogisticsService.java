package com.wkr.service;

import com.wkr.bean.LogisticsInfoBean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LogisticsService {
    /**
     * 通过物流编号查找是否有物流信息
     * @param goodsIndexCode 物流编号
     * @return
     */
    List<LogisticsInfoBean> fetchGoodsByGoodsIndexCode(String goodsIndexCode);

    /**
     * 更新物流信息
     * @param logisticsInfoBean
     */
    void updateGoods(LogisticsInfoBean logisticsInfoBean);

    /**
     * 插入物流信息
     * @param logisticsInfoBean
     */
    void insert(LogisticsInfoBean logisticsInfoBean);
}
