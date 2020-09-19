package com.manytomany.test;


import com.manytomany.bean.Role;
import com.manytomany.bean.User;
import com.manytomany.utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

public class HibernateManyToMany {


    @Test  //演示維護第三張表
    public void testtable2(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            //1.調用工具類得到sessionFactory
            sessionFactory = HibernateUtils.getSessionFactory();
            //2.獲取session
            session = sessionFactory.openSession();
            //3.開啟事務
            tx = session.beginTransaction();

            //讓某個用戶沒有某個角色
            User user = (User) session.get(User.class, 2);
            Role role = (Role) session.get(Role.class, 3);
            //2.從用戶裡面把角色去掉
            user.getSetRole().remove(role);

            //5.提交事務
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
        } finally {
            //6.關閉
            session.close();
            sessionFactory.close();
        }

    }

    @Test  //演示維護第三張表
    public void testtable1(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            //1.調用工具類得到sessionFactory
            sessionFactory = HibernateUtils.getSessionFactory();
            //2.獲取session
            session = sessionFactory.openSession();
            //3.開啟事務
            tx = session.beginTransaction();

            //讓某個用戶有某個角色
            //讓lucy有經紀人角色
            //1.查詢lucy和經紀人
            User lucy = (User) session.get(User.class, 1);
            Role role = (Role) session.get(Role.class, 1);
            //2.把角色放到用戶的set集合裡面
            lucy.getSetRole().add(role);

            //5.提交事務
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
        } finally {
            //6.關閉
            session.close();
            sessionFactory.close();
        }

    }
    @Test
    public void testDelete(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            //1.調用工具類得到sessionFactory
            sessionFactory = HibernateUtils.getSessionFactory();
            //2.獲取session
            session = sessionFactory.openSession();
            //3.開啟事務
            tx = session.beginTransaction();

            User user = (User) session.get(User.class, 1);
            session.delete(user);

            //5.提交事務
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
        } finally {
            //6.關閉
            session.close();
            sessionFactory.close();
        }

    }
    @Test
    public void testSave(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            //1.調用工具類得到sessionFactory
            sessionFactory = HibernateUtils.getSessionFactory();
            //2.獲取session
            session = sessionFactory.openSession();
            //3.開啟事務
            tx = session.beginTransaction();

            //添加兩個用戶,為每個用戶添加兩個角色
            User user1 = new User();
            user1.setUser_name("lucy");
            user1.setUser_password("123");

            User user2 = new User();
            user2.setUser_name("mary");
            user2.setUser_password("456");

            Role r1 = new Role();
            r1.setRole_name("總經理");
            r1.setRole_memo("總經理");

            Role r2 = new Role();
            r2.setRole_name("秘書");
            r2.setRole_memo("秘書");

            Role r3 = new Role();
            r3.setRole_name("保安");
            r3.setRole_memo("保安");

            //建立關係,把角色放到用戶裡面
            //user1 -- r1/r2
            user1.getSetRole().add(r1);
            user1.getSetRole().add(r2);
            //user2 -- r2/r3
            user2.getSetRole().add(r2);
            user2.getSetRole().add(r3);

            //3.保存用戶
            session.save(user1);
            session.save(user2);

            //5.提交事務
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
        } finally {
            //6.關閉
            session.close();
            sessionFactory.close();
        }

    }

}
