package com.wkr.service.Impl;

import com.wkr.bean.UserBean;
import com.wkr.dao.UserDao;
import com.wkr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
@Service//标注一个业务逻辑组件类 交由spring依赖注入
public class UserServiceImpl implements UserService {
    @Autowired//属于Spring 的org.springframework.beans.factory.annotation包下,可用于为类的属性、构造器、方法进行注值
    private UserDao userDao;
    @Override
    public Serializable saveUser(UserBean userBean) {
        Serializable i = userDao.saveUser(userBean);
        return i;
    }
}
