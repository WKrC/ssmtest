package com.wkr.dao.Impl;

import com.wkr.bean.ReaderBean;
import com.wkr.dao.ReaderDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional//事务管理
@Repository//标注一个DAO组件类  交由spring依赖注入dao层实现类
@Service//标注一个业务逻辑组件类
public class ReaderDaoImpl implements ReaderDao {
    //获得hibernate对于一些SQL语句的封装
    private HibernateTemplate template;

    static {

    }

    @Autowired//属于Spring 的org.springframework.beans.factory.annotation包下,可用于为类的属性、构造器、方法进行注值
    public ReaderDaoImpl(SessionFactory sessionFactory) {
        this.template = new HibernateTemplate(sessionFactory);
    }

    @Override
    public ReaderBean fetchReaderByMAC(ReaderBean readerBean) {
        List<ReaderBean> resultList = template.findByExample(readerBean);
        if (resultList.size() > 0){
            return resultList.get(0);
        }else {
            return null;
        }
    }

    @Override
    public void saveReaderSetting(ReaderBean readerBean) {
        template.save(readerBean);
    }

    @Override
    public List<ReaderBean> getTotalCount() {
        return (List<ReaderBean>) template.find("select ReaderName from ReaderBean");
    }

    @Override
    public void updateReaderSetting(ReaderBean readerBean) {
        template.update(readerBean);
    }

    @Override
    public void updateReaderIsOnline(String MAC, Integer isOnline) {
        //openSession 每次使用都是打开一个新的session，使用完需要调用close方法关闭session
        //getCurrentSession 是获取当前session对象，连续使用多次时，得到的session都是同一个对象
        Session session = template.getSessionFactory().openSession();
        Query query = session.createQuery("update ReaderBean set isOnline =: isOnline where ReaderSetMAC =: MAC");
        query.setInteger("isOnline", isOnline);
        query.setString("MAC", MAC);
        //使用getCurrentSession 异常：Transaction already active事务已经开启
        session.beginTransaction();//开启事务 猜想是spring 配置的事务作用域并没有达到 session导致
        query.executeUpdate();
        session.getTransaction().commit();//commit 隐式调用了session.flush() 不要再显式调用否则报错
        session.close();
    }
}
