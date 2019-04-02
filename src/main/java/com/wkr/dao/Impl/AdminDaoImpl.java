package com.wkr.dao.Impl;

import com.wkr.bean.AdminBean;
import com.wkr.dao.AdminDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional//事务管理
@Repository//标注一个DAO组件类  交由spring依赖注入dao层实现类
@Service//标注一个业务逻辑组件类
public class AdminDaoImpl implements AdminDao {

    @Autowired
    HibernateTemplate template;

    @Override
    public AdminBean fetchAdminInfo(AdminBean adminBean) {
        List<AdminBean> resultList = template.findByExample(adminBean);
        if (resultList.size() > 0){
            return resultList.get(0);
        }else {
            return null;
        }
    }
}
