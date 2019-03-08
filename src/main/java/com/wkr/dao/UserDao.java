package com.wkr.dao;

import com.wkr.bean.UserBean;

import java.io.Serializable;

public interface UserDao {
    Serializable saveUser(UserBean userBean);
}
