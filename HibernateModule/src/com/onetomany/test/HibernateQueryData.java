package com.onetomany.test;

import com.onetomany.bean.News;
import com.onetomany.bean.User;
import com.onetomany.utils.HibernateUtils;
import org.hibernate.*;
import org.junit.Test;

import java.util.List;

public class HibernateQueryData {
    /**
     * 使用Query對象
     */
    public void testTxQuery(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            //2.與本地線程綁定的session
            sessionFactory = HibernateUtils.getSessionFactory();
             session = sessionFactory.openSession();
            //3.開啟事務
            tx = session.beginTransaction();

            //1.創建Query對象
            //方法裡面寫HQL語句
            Query query = session.createQuery("from News");

            //2.調用Query對象裡面的方法得到結果
            List<News> list = query.list();

            for (News news : list) {
                System.out.println(news);
            }

            //5.提交事務
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            //回滾事務
            tx.rollback();
        } finally {
        }
        //6.關閉
        session.close();
        sessionFactory.close();

    }

    /**
     * 使用Criteria對象
     */
    public void testTxCriteria(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            //2.與本地線程綁定的session
            sessionFactory = HibernateUtils.getSessionFactory();
             session = sessionFactory.openSession();
            //3.開啟事務
            tx = session.beginTransaction();

            //1.創建Criteria對象
            //方法裡面參數是實體類class
            Criteria criteria = session.createCriteria(News.class);

            //2.調用Criteria對象裡面的方法得到結果
            List<News> list = criteria.list();

            for (News news : list) {
                System.out.println(news);
            }

            //5.提交事務
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            //回滾事務
            tx.rollback();
        } finally {
        }
        //6.關閉
        session.close();
        sessionFactory.close();

    }
    /**
     * 使用SQLQuery對象
     */
    @Test
    public void testTxSQLQuery(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            //2.與本地線程綁定的session
//             session = HibernateUtils.getSessionObjection();

            sessionFactory = HibernateUtils.getSessionFactory();
             session = sessionFactory.openSession();
            //3.開啟事務
            tx = session.beginTransaction();

            //1.創建Criteria對象
            //方法裡面參數是實體類class
            SQLQuery sqlQuery = session.createSQLQuery("select * from user");
            //返回的list中每個部分是對象形式
            sqlQuery.addEntity(User.class);
            //調用sqlQuery裡面的方法
            List<User> list = sqlQuery.list();
            for (User user : list) {
                System.out.println(user);
            }

            //2.調用SQLQuery對象裡面的方法得到結果
            //返回list集合,默認裡面是數組結構
//            List<Object[]> list = sqlQuery.list();
//            for (Object[] objects : list) {
//                System.out.println(Arrays.toString(objects));
//            }

            //5.提交事務
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            //回滾事務
            tx.rollback();
        } finally {
        }
        //6.關閉
        session.close();
        sessionFactory.close();

    }

}
