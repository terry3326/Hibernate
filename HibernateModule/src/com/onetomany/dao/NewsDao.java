package com.onetomany.dao;

import com.onetomany.bean.News;
import com.onetomany.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;


public class NewsDao {
	/**
	 * 查詢操作
	 */
	@Test
	public void testGet() {
		//1.調用工具類得到sessionFactory
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		//2.獲取session
		Session session = sessionFactory.openSession();
		//3.開啟事務
		Transaction tx = session.beginTransaction();

		//4.根據id查詢
		//調用session裡的get方法
		//第一個參數:實體類的class
		//第二個參數值
		 News news = (News) session.get(News.class, 1);
		System.out.println(news);
		//5.提交事務
		tx.commit();
		//6.關閉
		session.close();
		sessionFactory.close();
	}

	/**
	 * 修改操作
	 */
	public void testUpdate() {
		//1.調用工具類得到sessionFactory
				SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
				//2.獲取session
				Session session = sessionFactory.openSession();
				//3.開啟事務
				Transaction tx = session.beginTransaction();

				//4.修改操作  修改id = 2紀錄username值
				//4.1 根據id查詢
				News news = (News) session.get(News.class, 1);
				//4.2向返回的news對象裡面設置修改之後的值
				news.setAuthor("東方不敗");
				//4.3調用session的方法修改
				//執行的過程:到News對象裡面找到id值,根據id進行修改
				session.update(news);

				//4.修改(不建議)
				//		News news = new News(); //造一個新的對象
				//		news.setId(3);
				//		news.setAuthor("terry");
				//		session.update(news);//這樣會把原來id為3的對象修改成新的對象的值(只有id值跟Author值,其他的都為null)
				
				//5.提交事務
				tx.commit();
				//6.關閉
				session.close();
				sessionFactory.close();
	}

	/**
	 * 刪除操作
	 */
	public void testDelete() {
		//1.調用工具類得到sessionFactory
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		//2.獲取session
		Session session = sessionFactory.openSession();
		//3.開啟事務
		Transaction tx = session.beginTransaction();
		
		//4.刪除操作
		//第一種根據id查詢對象
		News news = (News) session.get(News.class, 1);
		session.delete(news);

		//第二種
		News news1 = new News();
		news1.setId(3);
		session.delete(news1);

		//5.提交事務
		tx.commit();
		//6.關閉
		session.close();
		sessionFactory.close();
	}

	/**
	 * 儲存操作
	 */
	public void testSave() {
		//1.調用工具類得到sessionFactory
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		//2.獲取session
		Session session = sessionFactory.openSession();
		//3.開啟事務
		Transaction tx = session.beginTransaction();

		//4.添加操作
		News news = new News();
		news.setId(3);
		news.setAuthor("terry");
		session.save(news);



		//5.提交事務
		tx.commit();
		//6.關閉
		session.close();
		sessionFactory.close();
	}

	/**
	 *
	 */
	@Test
	public void testSaveOrUpdate() {
		//1.調用工具類得到sessionFactory
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		//2.獲取session
		Session session = sessionFactory.openSession();
		//3.開啟事務
		Transaction tx = session.beginTransaction();

		//4.瞬時態
//		News news = new News();
//		news.setAuthor("terry");
//		news.setTitle("20200908");

		//4.託管態
//		News news = new News();
//		news.setId(2);
//		news.setAuthor("GG");
//		news.setTitle("20200908");

		//4.持久態
		News news = (News) session.get(News.class, 2);
		news.setTitle("Hibernate");

		//實體類對象是瞬時態,做添加
		//實體類對象是託管態,做修改
		//實體類對象是持久態,做修改

		session.saveOrUpdate(news);



		//5.提交事務
		tx.commit();
		//6.關閉
		session.close();
		sessionFactory.close();
	}

	/**
	 * 各種狀態說明
	 */
	public void status(){
		//1.調用工具類得到sessionFactory
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		//2.獲取session
		Session session = sessionFactory.openSession();
		//3.開啟事務
		Transaction tx = session.beginTransaction();

		/*
		 * 瞬時態:對象裡面沒有id值,對象與session沒有關聯,通常用於save操作
		 */
		News n = new News();
		n.setAuthor("GG");
		n.setDesc("gg");
		session.save(n);

		/*
		 * 持久態：對象裡面有id值,對象與session關聯
		 */
		News news = (News) session.get(News.class, 1);

		/*
		 * 託管態:對象有id值,對象與session沒有關聯
		 */
		News n2 = new News();
		n2.setId(3);


		//5.提交事務
		tx.commit();
		//6.關閉
		session.close();
		sessionFactory.close();
	}

}
