package com.wkr.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "goods", catalog = "postgres")
public class GoodsBean {
    @Id
    @GeneratedValue(generator = "goodsIdGenerator")// generator - 表示主键生成器的名称，这个属性通常和 ORM 框架相关 , 例如：Hibernate 可以指定 uuid 等主键生成方式
    @GenericGenerator(name = "goodsIdGenerator", strategy = "increment")//自定义主键生成策略 实现自增  name - 指定的是生成器名称   strategy - 生成策略
    private int goodsId;
    @Column(name = "goodsIndexCode")
    private String goodsIndexCode;
    @Column(name = "sender")
    private String sender;
    @Column(name = "sender_phone")
    private String sender_phone;
    @Column(name = "sender_addr")
    private String sender_addr;
    @Column(name = "consignee")
    private String consignee;
    @Column(name = "consignee_phone")
    private String consignee_phone;
    @Column(name = "consignee_addr")
    private String consignee_addr;
    @Column(name = "remaker")
    private String remaker;

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsIndexCode() {
        return goodsIndexCode;
    }

    public void setGoodsIndexCode(String goodsIndexCode) {
        this.goodsIndexCode = goodsIndexCode;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSender_phone() {
        return sender_phone;
    }

    public void setSender_phone(String sender_phone) {
        this.sender_phone = sender_phone;
    }

    public String getSender_addr() {
        return sender_addr;
    }

    public void setSender_addr(String sender_addr) {
        this.sender_addr = sender_addr;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getConsignee_phone() {
        return consignee_phone;
    }

    public void setConsignee_phone(String consignee_phone) {
        this.consignee_phone = consignee_phone;
    }

    public String getConsignee_addr() {
        return consignee_addr;
    }

    public void setConsignee_addr(String consignee_addr) {
        this.consignee_addr = consignee_addr;
    }

    public String getRemaker() {
        return remaker;
    }

    public void setRemaker(String remaker) {
        this.remaker = remaker;
    }
}
