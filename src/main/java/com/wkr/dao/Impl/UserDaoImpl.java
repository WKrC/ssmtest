package com.wkr.dao.Impl;

import com.wkr.bean.UserBean;
import com.wkr.dao.UserDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository//标注一个DAO组件类  交由spring依赖注入dao层实现类
public class UserDaoImpl implements UserDao {
    //获得hibernate对于一些SQL语句的封装
    private HibernateTemplate template;

    @Autowired//属于Spring 的org.springframework.beans.factory.annotation包下,可用于为类的属性、构造器、方法进行注值
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.template = new HibernateTemplate(sessionFactory);
    }

    public Serializable saveUser(UserBean userBean) {
        Serializable i= template.save(userBean);//调用
        return i;
    }
}
