package com.onetomany.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author a8001
 * 在 hibernate操作中,建議一個項目一般創建一個sessionFactory對象
 */
public class HibernateUtils {
	static 	Configuration configuration = null;
	static 	SessionFactory sessionFactory = null;
	// 靜態代碼塊實現
	static {
		
		//加載配置文件
		configuration = new Configuration().configure();
		// 4.0 之前这样创建
		  sessionFactory = configuration.buildSessionFactory();

		// 2). 创建一个 ServiceRegistry 对象: hibernate 4.x 新添加的对象
		// hibernate 的任何配置和服务都需要在该对象中注册后才能有效.
//		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
//				.buildServiceRegistry();
//		 sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}

	/**
	 * 提供返回本地線程綁定的Session方法
	 */
	public static Session getSessionObjection(){
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 提供方法返回SessionFactory
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
		
	}

}
