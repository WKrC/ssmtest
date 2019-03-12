package com.wkr.dao.Impl;

import com.wkr.bean.GoodsBean;
import com.wkr.dao.GoodsDao;
import com.wkr.service.GoodsService;
import org.hibernate.FlushMode;
import org.hibernate.SessionFactory;
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
public class GoodsDaoImpl implements GoodsDao {

    //获得hibernate对于一些SQL语句的封装
    private HibernateTemplate template;

    @Autowired//属于Spring 的org.springframework.beans.factory.annotation包下,可用于为类的属性、构造器、方法进行注值
    public GoodsDaoImpl(SessionFactory sessionFactory) {
        this.template = new HibernateTemplate(sessionFactory);
    }

    @Override
    public void saveGoods(GoodsBean goodsBean) {
        template.getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);//用于破解数据库只读
        template.save(goodsBean);//调用
    }

    @Override
    public List<GoodsBean> fetchGoods(String goodsIndexCode) {
        template.getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);//用于破解数据库只读
        GoodsBean goodsBean = new GoodsBean();
        goodsBean.setGoodsIndexCode(goodsIndexCode);
        List<GoodsBean> goodsList = template.findByExample(goodsBean);
        return goodsList;
    }
}
