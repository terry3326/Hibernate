package com.onetomany.test;

import com.onetomany.bean.Customer;
import com.onetomany.bean.LinkMan;
import com.onetomany.utils.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class HibernateHQL {
    //聚集函數查詢
    @Test
    public void testSelect7() {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            //1.創建query對象
            Query query = session.createQuery("select count(*) from Customer");
            //2.調用方法得到結果
            //query對象裡面有方法,直接返回對象形式(long類型)
            Object obj = query.uniqueResult();
            //強轉成int類型會報"類型轉換異常",
            //int count = (int) obj;

            //先把Object變成long,再變成int類型
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
    //投影查詢
    @Test
    public void testSelect6() {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            //1.創建query對象
            Query query = session.createQuery("select custName from Customer");
            //2.調用方法得到結果
            List<Object> list = query.list();

            for (Object obj : list) {
                System.out.println(obj);
            }

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
    public void testSelect5() {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            //1.創建query對象
            Query query = session.createQuery("from Customer");
            //2.設置分頁數據
            //2.1設置開始位置
            query.setFirstResult(0);
            //2.2設置每頁紀錄數
            query.setMaxResults(3);
            //3.調用方法得到結果
            List<Customer> list = query.list();

            for (Customer customer : list) {
                System.out.println(customer.getCid()+"::"+customer.getCustName());
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
    public void testSelect4() {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            //1.創建query對象
            //select * from customer where  custName like ?
//            Query query = session.createQuery("from Customer order by cid asc");
            Query query = session.createQuery("from Customer order by cid desc");
            //2.調用方法得到結果
            List<Customer> list = query.list();

            for (Customer customer : list) {
                System.out.println(customer.getCid()+"::"+customer.getCustName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
    //模糊查詢
    @Test
    public void testSelect3() {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            //1.創建query對象
            //select * from customer where  custName like ?
            Query query = session.createQuery("from Customer c where c.custName like ?");
            //2.設置?值 %米%
            query.setParameter(0, "%米%");
            //3.調用方法得到結果
            List<Customer> list = query.list();

            for (Customer customer : list) {
                System.out.println(customer.getCid()+"::"+customer.getCustName());
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

            //1.創建query對象
            //select * from customer where cid = ? and custName = ?
            Query query = session.createQuery("from Customer c where c.cid=? and c.custName=?");
            //2.設置條件值
            //向?裡面設置值
            //setParameter方法：第一個參數:?的位置,?位置從0開始 ;第二個參數:具體參數值
            //設置第一個?值
            query.setParameter(0, 2);
            //設置第二個?值
            query.setParameter(1, "百度");
            //3.調用方法得到結果
            List<Customer> list = query.list();

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
            //1.創建一個Query對象
            Query query = session.createQuery("from Customer");
            //2.調用方法得到結果
            List<Customer> list = query.list();
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
