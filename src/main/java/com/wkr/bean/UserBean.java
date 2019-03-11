package com.wkr.bean;

import com.sun.javafx.beans.IDProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity // 表明当前类是一个持久化类
// 持久化类：是指其实例需要被Hibernate持久化到数据库中的类
@Table(name = "userList", catalog = "postgres") // 映射一个表product，所对应的数据库是postgres(可忽略)，
public class UserBean {
    @Id
    @GeneratedValue(generator = "id")// generator - 表示主键生成器的名称，这个属性通常和 ORM 框架相关 , 例如：Hibernate 可以指定 uuid 等主键生成方式
    @GenericGenerator(name = "id", strategy = "increment")//自定义主键生成策略 实现自增  name - 指定的是生成器名称   strategy - 生成策略
    private int id;
    @Column(name = "name")
    private String name;// 商品名称
    @Column(name = "password")
    private String password;// 商品价格

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
