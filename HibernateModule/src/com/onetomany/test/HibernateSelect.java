package com.onetomany.test;

import com.onetomany.bean.News;
import com.onetomany.utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

public class HibernateSelect {
    /**
     * 事務規範代碼
     * 獲取與本地線程綁定的session的時候,關閉session報錯,不需要手動關閉了,
     * 線程結束後會自動關閉session
     */
    public void testTx(){
        Session session = null;
        Transaction tx = null;
        try {
            //2.與本地線程綁定的session
            session = HibernateUtils.getSessionObjection();
            //3.開啟事務
             tx = session.beginTransaction();

            //添加
            News news = new News();
            news.setTitle("j2ee");
            news.setAuthor("terry");
            session.save(news);

            //5.提交事務
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            //回滾事務
            tx.rollback();
        } finally {
        }
        //6.關閉
//        session.close();
//        sessionFactory.close();

    }



    /**
     * 一級緩存特性
     * 持久態會自動更新數據庫:不用在調用update方法
     */
    public void testDemo() {
        //1.調用工具類得到sessionFactory
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        //2.獲取session
        Session session = sessionFactory.openSession();
        //3.開啟事務
        Transaction tx = session.beginTransaction();

        //4.1根據id= 7查詢
        News news = (News) session.get(News.class, 7);
        //4.2設置返回對象值
        news.setTitle("hibernate2");
        //4.3調用方法實現
//        session.update(news);


        //5.提交事務
        tx.commit();
        //6.關閉
        session.close();
        //如果是web項目,sessionFactory不用關閉
        sessionFactory.close();
    }
    @Test
    public void testCasch() {
        //1.調用工具類得到sessionFactory
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        //2.獲取session
        Session session = sessionFactory.openSession();
        //3.開啟事務
        Transaction tx = session.beginTransaction();

        //4.1根據id= 2查詢
        //執行第一個get方法是否查詢資料庫：是否發送sql語句
        News news1 = (News) session.get(News.class, 1);
        System.out.println(news1);
        //4.2根據id= 2查詢
        //執行第二個get方法是否查詢資料庫：是否發送sql語句
        News news2 = (News) session.get(News.class, 1);
        System.out.println(news2);

        //5.提交事務
        tx.commit();
        //6.關閉
        session.close();
        sessionFactory.close();
    }
}
