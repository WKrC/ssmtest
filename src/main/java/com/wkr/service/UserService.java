package com.wkr.service;

import com.wkr.bean.UserBean;

import java.io.Serializable;

public interface UserService {
    Serializable saveUser(UserBean userBean);
}
