package com.wkr.dao.Impl;

import com.wkr.bean.LogisticsInfoBean;
import com.wkr.dao.LogisticsDao;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional//事务管理
@Repository//标注一个DAO组件类  交由spring依赖注入dao层实现类
@Service//标注一个业务逻辑组件类
public class LogisticsDaoImpl implements LogisticsDao {

    //获得hibernate对于一些SQL语句的封装
    @Autowired
    private HibernateTemplate template;


    @Override
    public LogisticsInfoBean fetchGoodsByGoodsIndexCode(String goodsIndexCode) {
        //template.getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);//用于破解数据库只读
        LogisticsInfoBean logisticsInfoBean = new LogisticsInfoBean();
        logisticsInfoBean.setGoodsIndexCode(goodsIndexCode);
        List<LogisticsInfoBean> logisticsInfoBeanList = template.findByExample(logisticsInfoBean);
        if (logisticsInfoBeanList.size() > 0) {
            return logisticsInfoBeanList.get(0);
        }
        return null;
    }

    @Override
    public void updateGoods(LogisticsInfoBean logisticsInfoBean) {
        template.update(logisticsInfoBean);
    }

    @Override
    public void insert(LogisticsInfoBean logisticsInfoBean) {
        template.save(logisticsInfoBean);//调用
    }
}
