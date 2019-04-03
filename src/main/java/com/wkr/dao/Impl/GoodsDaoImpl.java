package com.wkr.dao.Impl;

import com.wkr.bean.GoodsBean;
import com.wkr.dao.GoodsDao;
import com.wkr.service.GoodsService;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

    @Override
    public List<GoodsBean> fetchAll() {
        return (List<GoodsBean>) template.find("from GoodsBean");
    }

    @Override
    public void ConfirmReceive(String indexCode) {
        //openSession 每次使用都是打开一个新的session，使用完需要调用close方法关闭session
        //getCurrentSession 是获取当前session对象，连续使用多次时，得到的session都是同一个对象
        Session session = template.getSessionFactory().openSession();
        Query query = session.createQuery("update GoodsBean set isOver = 1 where goodsIndexCode =: IndexCode");
        query.setString("IndexCode", indexCode);
        //使用getCurrentSession 会出现异常：Transaction already active事务已经开启
        session.beginTransaction();//开启事务 猜想是spring 配置的事务作用域并没有达到 session导致
        query.executeUpdate();
        session.getTransaction().commit();//commit 隐式调用了session.flush() 不要再显式调用否则报错
        session.close();
    }
}
