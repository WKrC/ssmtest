package com.wkr.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "logisticsinfo", catalog = "lunwen")
public class LogisticsInfoBean {
    @Id
    @GeneratedValue(generator = "logisticsInfoIdGenerator")// generator - 表示主键生成器的名称，这个属性通常和 ORM 框架相关 , 例如：Hibernate 可以指定 uuid 等主键生成方式
    @GenericGenerator(name = "logisticsInfoIdGenerator", strategy = "increment")//自定义主键生成策略 实现自增  name - 指定的是生成器名称   strategy - 生成策略
    private int logisticsInfoId;
    @Column(name = "goodsIndexCode")
    private int goodsIndexCode;
    @Column(name = "goodsPosition", length = 2000)
    private String goodsPosition;
}
