package com.wkr.dao;

import com.wkr.bean.GoodsBean;
import com.wkr.bean.UserBean;

import java.io.Serializable;
import java.util.List;

public interface UserDao {
    Serializable saveUser(UserBean userBean);
}
