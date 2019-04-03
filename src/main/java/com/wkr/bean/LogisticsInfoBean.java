package com.wkr.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "logisticsinfo", catalog = "lunwen")
public class LogisticsInfoBean {
    @Id
    @GeneratedValue(generator = "logisticsInfoIdGenerator")// generator - 表示主键生成器的名称，这个属性通常和 ORM 框架相关 , 例如：Hibernate 可以指定 uuid 等主键生成方式
    @GenericGenerator(name = "logisticsInfoIdGenerator", strategy = "increment")//自定义主键生成策略 实现自增  name - 指定的是生成器名称   strategy - 生成策略
    private Integer logisticsInfoId;
    @Column(name = "goodsIndexCode", length = 50)
    private String goodsIndexCode;
    @Column(name = "goodsPosition", length = 2000)
    private String goodsPosition;
    @Column(name = "goodsGPSInfo", length = 2000)
    private String goodsGPSInfo;
    @Column(name = "timeInfo", length = 2000)
    private String timeInfo;

    public String getTimeInfo() {
        return timeInfo;
    }

    public void setTimeInfo(String timeInfo) {
        this.timeInfo = timeInfo;
    }

    public Integer getLogisticsInfoId() {
        return logisticsInfoId;
    }

    public void setLogisticsInfoId(Integer logisticsInfoId) {
        this.logisticsInfoId = logisticsInfoId;
    }

    public String getGoodsIndexCode() {
        return goodsIndexCode;
    }

    public void setGoodsIndexCode(String goodsIndexCode) {
        this.goodsIndexCode = goodsIndexCode;
    }

    public String getGoodsPosition() {
        return goodsPosition;
    }

    public void setGoodsPosition(String goodsPosition) {
        this.goodsPosition = goodsPosition;
    }

    public String getGoodsGPSInfo() {
        return goodsGPSInfo;
    }

    public void setGoodsGPSInfo(String goodsGPSInfo) {
        this.goodsGPSInfo = goodsGPSInfo;
    }
}
