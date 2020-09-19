package com.onetomany.test;

import com.onetomany.bean.Customer;
import com.onetomany.utils.HibernateUtils;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.junit.Test;

import java.util.List;

public class HibernateQBC {

    //離線查詢
    @Test
    public void testSelect6() {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            //1.創建對象
//            Criteria criteria = session.createCriteria(Customer.class);
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
            //2.最終執行時候才需要到session
            Criteria criteria = detachedCriteria.getExecutableCriteria(session);
            List<Customer> list = criteria.list();
            for (Customer customer : list) {
                System.out.println(customer);
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
    //統計查詢
    @Test
    public void testSelect5() {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            //1.創建一個Criteria對象
            Criteria criteria = session.createCriteria(Customer.class);
            //2.設置操作
            criteria.setProjection(Projections.rowCount());
            //3.調用方法得到結果
            Object obj = criteria.uniqueResult();
            Long lobj = (Long) obj;
            int count = lobj.intValue();
            System.out.println(count);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
    //分頁查詢
    @Test
    public void testSelect4() {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            //1.創建一個Criteria對象
            Criteria criteria = session.createCriteria(Customer.class);
            //2.設置分頁數據
            //2.1設置開始位置
            criteria.setFirstResult(0);
            //2.2每頁顯示記錄數
            criteria.setMaxResults(3);


            //3.調用方法得到結果
            List<Customer> list = criteria.list();
            for (Customer customer : list) {
                System.out.println(customer);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
    //排序查詢
    @Test
    public void testSelect3() {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            //1.創建一個Criteria對象
            Criteria criteria = session.createCriteria(Customer.class);
            //2.設置對哪個屬性進行排序,設置排序規則
            criteria.addOrder(Order.desc("cid"));

            //3.調用方法得到結果
            List<Customer> list = criteria.list();
            for (Customer customer : list) {
                System.out.println(customer);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
    //條件查詢
    @Test
    public void testSelect2() {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            //1.創建一個Criteria對象
            Criteria criteria = session.createCriteria(Customer.class);
            //2.使用criteria對象裡面的方法設置條件值
            //首先使用add方法,表示設置條件值
            //在add方法裡面使用類的方法實現條件設置
            //類似等於cid=?
//            criteria.add(Restrictions.eq("cid", 1));
//            criteria.add(Restrictions.eq("custName", "百度"));

            //模糊查詢
            criteria.add(Restrictions.like("custName", "%百%"));

            //3.調用方法得到結果
            List<Customer> list = criteria.list();
            for (Customer customer : list) {
                System.out.println(customer);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
    //查詢所有
    @Test
    public void testSelect1() {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            //1.創建一個Criteria對象
            Criteria criteria = session.createCriteria(Customer.class);
            //2.調用方法得到結果
            List<Customer> list = criteria.list();
            for (Customer customer : list) {
                System.out.println(customer);
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
