package com.wkr.service.Impl;

import com.wkr.bean.AdminBean;
import com.wkr.dao.AdminDao;
import com.wkr.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminDao adminDao;

    @Override
    public AdminBean fetchAdminInfo(AdminBean adminBean) {
        return adminDao.fetchAdminInfo(adminBean);
    }
}
