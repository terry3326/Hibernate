package com.onetomany.test;

import com.onetomany.bean.Customer;
import com.onetomany.utils.HibernateUtils;
import org.hibernate.*;
import org.junit.Test;

import java.util.List;

public class HibernateManyTable {
    //HQL左外連接查詢
    @Test
    public void testSelect2() {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            //1.創建一個Query對象
            Query query = session.createQuery("from Customer c left join  c.setLinkMan");
//            Query query = session.createQuery("from Customer c left join fetch c.setLinkMan");//迫切左外連接
            //2.調用方法得到結果
            List list = query.list();


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
    //HQL內連接查詢
    @Test
    public void testSelect1() {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            //1.創建一個Query對象
//            Query query = session.createQuery("from Customer c inner join c.setLinkMan");
            Query query = session.createQuery("from Customer c inner join fetch c.setLinkMan");//迫切內連接
            //2.調用方法得到結果
            List list = query.list();
            for (Object o : list) {
                System.out.println(o);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
