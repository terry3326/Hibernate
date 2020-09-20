package com.onetomany.test;

import com.onetomany.bean.Customer;
import com.onetomany.bean.LinkMan;
import com.onetomany.utils.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class HibernateDemo {

    //演示批量抓取
    @Test
    public void testSelect3(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            //查詢所有客戶
            Criteria criteria = session.createCriteria(Customer.class);
            List<Customer> list = criteria.list();
            //得到每個客戶裡面所有的聯繫人
            for (Customer customer : list) {
                System.out.println(customer.getCid()+"::"+customer.getCustName());
                Set<LinkMan> setLinkMan = customer.getSetLinkMan();
                for (LinkMan linkMan : setLinkMan) {
                    System.out.println(linkMan.getLkm_id()+"::"+linkMan.getLkm_name());
                }
            }
            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }
    }
    //演示檢索的策略
    @Test
    public void testSelect2(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            //根據cid=1客戶
            //執行get方法後是否發送sql語句
            //調用get方法馬上發送sql語句查詢數據庫
//            Customer customer = (Customer) session.get(Customer.class, 1);
//            System.out.println(customer.getCustName());
            /*
                1.調用load方法之後,不會馬上發送sql語句
                  (1)返回對象裡面只有id值
                2.得到對象裡面不是id的其他值時候才會發送語句
             */
            Customer customer = (Customer) session.load(Customer.class, 1);
            System.out.println(customer.getCid());
            System.out.println(customer.getSetLinkMan());


            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }
    }
    //演示對象導航查詢
    @Test
    public void testSelect1(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            //根據cid=1客戶,再查詢這個客戶裡面的所有聯繫人
            Customer customer = (Customer) session.get(Customer.class, 1);
            //再查詢這個客戶裡面的所有聯繫人
            //直接得到客戶裡面聯繫人的set集合

            //得到set集合,是否發送語句
            Set<LinkMan> linkMan = customer.getSetLinkMan();
            //發送語句
            System.out.println(linkMan.size());
            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }
    }
}
