package com.onetomany.test;

import com.onetomany.bean.Customer;
import com.onetomany.bean.LinkMan;
import com.onetomany.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.Set;

public class HibernateDemo {

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
            Set<LinkMan> linkMan = customer.getSetLinkMan();
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
