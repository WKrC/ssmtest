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

    @Autowired//属于Spring 的org.springframework.beans.factory.annotation包下,可用于为类的属性、构造器、方法进行注值
    public ReaderDaoImpl(SessionFactory sessionFactory) {
        this.template = new HibernateTemplate(sessionFactory);
    }

    @Override
    public ReaderBean fetchReaderByMAC(ReaderBean readerBean) {
       // template.getSessionFactory().openSession().setFlushMode(FlushMode.AUTO);//用于破解数据库只读
        List<ReaderBean> resultList = template.findByExample(readerBean);
        if (resultList.size() > 0){
            return resultList.get(0);
        }else {
            return null;
        }
    }

    @Override
    public void saveReaderSetting(ReaderBean readerBean) {
       // template.getSessionFactory().openSession().setFlushMode(FlushMode.AUTO);//用于破解数据库只读
        template.save(readerBean);
    }

    @Override
    public List<ReaderBean> getTotalCount() {
        return (List<ReaderBean>) template.find("select ReaderName from ReaderBean");
    }

    @Override
    public void updateReaderSetting(ReaderBean readerBean) {
        //template.getSessionFactory().openSession().setFlushMode(FlushMode.AUTO);//用于破解数据库只读
        //template.getSessionFactory().getCurrentSession().clear();
        template.update(readerBean);
    }

    @Override
    public void updateReaderIsOnline(String MAC, int isOnline) {
        Session session = template.getSessionFactory().openSession();
        Query query = session.createQuery("update ReaderBean set isOnline = ? where ReaderSetMAC = ?");
        query.setParameter(0, isOnline);
        query.setParameter(1, MAC);
        query.executeUpdate();
        session.flush();
        session.close();
    }
}
