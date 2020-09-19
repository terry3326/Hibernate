package com.onetomany.test;

import com.onetomany.bean.News;
import com.onetomany.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import java.sql.Date;


public class HibernateTest {


	@Test
	public void test() {

		System.out.println("test...");



		//1. 第一步 加載 hibernate 核心配置文件：基本配置信息和 对象关系映射信息
		//到src下面找到名稱是hibernate.cfg.xml
		//在hibernate裡面封裝對象
//			Configuration configuration = new Configuration().configure();

		//第二步創建sessionFactory對象
		//讀取hibernate核心配置文件內容,創建sessionFactory
		//在過程中,根據映射關係,在配置數據庫裡把表創建

		//4.0 之前这样创建
		//SessionFactory sessionFactory = configuration.buildSessionFactory();

		//2). 创建一个 ServiceRegistry 对象: hibernate 4.x 新添加的对象
		//hibernate 的任何配置和服务都需要在该对象中注册后才能有效.
//		ServiceRegistry serviceRegistry =
//				new ServiceRegistryBuilder().applySettings(configuration.getProperties())
//				                            .buildServiceRegistry();

		//3).
//		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		//第三步使用SessionFactory创建一个 Session 对象
		//類似於連接
		Session session = sessionFactory.openSession();

		//3第四步开启事务
		Transaction transaction = session.beginTransaction();

		//第五步 寫具體邏輯CRUD操作
		//添加功能
		News news = new News("Java12345", "ATGUIGU", new Date(new java.util.Date().getTime()));
		//調用session 方法保存
		session.save(news);

		//第六步 提交事务
		transaction.commit();

		//第七步關閉資源
		//关闭 Session
		session.close();
		//关闭 SessionFactory 对象
		sessionFactory.close();
	}

}
