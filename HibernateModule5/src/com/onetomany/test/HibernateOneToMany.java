package com.onetomany.test;

import com.onetomany.bean.Customer;
import com.onetomany.bean.LinkMan;
import com.onetomany.utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

public class HibernateOneToMany {
    /**
     * 演示一對多級聯修改
     */
    @Test
    public void testUpdateDemo(){
        //1.調用工具類得到sessionFactory
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        //2.獲取session
        Session session = sessionFactory.openSession();
        //3.開啟事務
        Transaction tx = session.beginTransaction();

        //1.根據id 查詢客戶對象
        Customer baidu = (Customer) session.get(Customer.class, 2);
        LinkMan lucy = (LinkMan) session.get(LinkMan.class, 1);
        //2. 設置持久態對象值
        //持久態會自動更新數據庫
        //把聯繫人放到客戶裡面
        baidu.getSetLinkMan().add(lucy);
        //把客戶放到聯繫人裡面
        lucy.setCustomer(baidu);


        //5.提交事務
        tx.commit();
        //6.關閉
        session.close();
        sessionFactory.close();
    }
    /**
     * 演示一對多級聯刪除
     * 在Customer.hbm.xml 的cascade屬性值添加delete屬性值
     */
    @Test
    public void testDeleteDemo(){
        //1.調用工具類得到sessionFactory
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        //2.獲取session
        Session session = sessionFactory.openSession();
        //3.開啟事務
        Transaction tx = session.beginTransaction();

        //1.根據id 查詢聯繫人lucy,根據id查詢百度的客戶
        Customer customer = (Customer) session.get(Customer.class, 1);
        //2. 調用方法刪除
        session.delete(customer);


        //5.提交事務
        tx.commit();
        //6.關閉
        session.close();
        sessionFactory.close();
    }
    /**
     * 演示一對多級聯保存
     * 簡化寫法:在Customer.hbm.xml 中設定cascade屬性值為save-update
     */
    @Test
    public void testAddDemo2(){
        //1.調用工具類得到sessionFactory
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        //2.獲取session
        Session session = sessionFactory.openSession();
        //3.開啟事務
        Transaction tx = session.beginTransaction();

        //添加一個客戶,為這個客戶添加一個聯繫人
        //1.創建客戶和聯繫人對象
//        Customer customer = new Customer();
//        customer.setCustLevel("普通客戶");
//        customer.setCustName("NTC");
//        customer.setCustSource("網路");
//        customer.setCustPhone("110");
//        customer.setCustMobile("999");
        Customer customer = (Customer) session.get(Customer.class, 1);
        LinkMan lucy = (LinkMan) session.get(LinkMan.class, 1);



//        LinkMan linkMan = new LinkMan();
//        linkMan.setLkm_name("allen");
//        linkMan.setLkm_gender("男");
//        linkMan.setLkm_phone("911");


        //2. 把聯繫人放到客戶對象的set集合裡面
        customer.getSetLinkMan().add(lucy);

        //3 保存客戶
        session.update(customer);

        //5.提交事務
        tx.commit();

        //6.關閉
        session.close();
        sessionFactory.close();
    }
    /**
     * 演示一對多級聯保存
     * 複雜寫法
     */
    @Test
    public void testAddDemo1(){
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

            //添加一個客戶,為這個客戶添加一個聯繫人
            //1.創建客戶和聯繫人對象
            Customer customer = new Customer();
            customer.setCustLevel("vip");
            customer.setCustName("傳智播客");
            customer.setCustSource("網路");
            customer.setCustPhone("110");
            customer.setCustMobile("999");

            LinkMan linkMan = new LinkMan();
            linkMan.setLkm_name("lucy");
            linkMan.setLkm_gender("男");
            linkMan.setLkm_phone("911");

            //2.在客戶表示所有聯繫人,在聯繫人表示客戶
            //建立客戶對象和聯繫人對象關係
            //2.1 把聯繫人放到客戶對象的set集合裡面
            customer.getSetLinkMan().add(linkMan);
            //2.2 把客戶對象放到聯繫人裡面
            linkMan.setCustomer(customer);

            //3 保存到數據庫
            session.save(customer);
            session.save(linkMan);

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
