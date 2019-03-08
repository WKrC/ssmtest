package com.wkr.service.Impl;

import com.wkr.bean.UserBean;
import com.wkr.dao.UserDao;
import com.wkr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
@Transactional//当作用于类上时，该类的所有 public 方法将都具有该类型的事务属性
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
