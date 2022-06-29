package com.dragonsoft.helloworld.test;

import com.dragonsoft.helloworld.domain.User;
import com.dragonsoft.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.io.Serializable;

public class HibernateHelloWorldTest {
    /**
     * 测试保存客户
     */
    @Test
    public void testSave(){
        // 先加载配置文件
        Configuration config = new Configuration();
        // 默认加载src目录下的配置文件
        config.configure();
        // 创建SessionFactory对象
        SessionFactory factory = config.buildSessionFactory();
        // 创建session对象
        Session session = factory.openSession();
        // 开启事务
        Transaction tr = session.beginTransaction();
        // 编写保存代码
            //瞬时态
        User user = new User();
        user.setName("测试名称");
        user.setAge(18);
        user.setGender("男");
        // 保存客户
            //user是持久态对象,调用save方法后，缓存中也会放入一个user对象
        Serializable id = session.save(user);
        //如果不提交事物，则证明
        System.out.println(id);
        // 提交事务
        //tr.commit();
        // 释放资源
            //关闭session之后,user的状态变为托管态，有oid,没有被session管理
        session.close();
        factory.close();
    }
    /**
     * 测试保存客户
     */
    @Test
    public void testSave1(){
        Session session = null;
        Transaction transaction = null;
        try {
            // 编写保存代码
            User user = new User();
            user.setName("测试名称");
            user.setAge(28);
            user.setGender("男");
            // 保存客户
            session.save(user);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            // 释放资源
            session.close();
        }
    }

    /**
     * 测试添加或者修改
     */
    @Test
    public void testSaveOrUpdate(){
        // 原来：加载配置文件，获取Factory对象，获取session
        Session session = HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();

		/*// 演示错误
		User user = new User();
		// user.setId(1);	千万不能自己设置
		user.setUserName("测试");

		// 保存或者修改
		session.saveOrUpdate(user);*/

        // 先查询再改
        User user = session.get(User.class, "402892816b84d45d016b84d45f4f0000");
        user.setName("校长");
        session.saveOrUpdate(user);

        // 提交事务
        tr.commit();
        // 释放资源
        session.close();
    }

    /**
     * 测试修改
     */
    @Test
    public void testUpdate(){
        // 原来：加载配置文件，获取Factory对象，获取session
        Session session = HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();

		/*// 演示错误
		User user = new User();
		// user.setId(1);	千万不能自己设置
		user.setUserName("测试");

		// 保存或者修改
		session.saveOrUpdate(user);*/

        // 先查询再改
        User user = session.get(User.class, "402892816b84d45d016b84d45f4f0000");
        user.setName("校长1");
        session.update(user);

        // 提交事务
        tr.commit();
        // 释放资源
        session.close();
    }
    /**
     * 测试删除的方法
     * 注意：删除或者修改，先查询再删除或者修改
     */
    @Test
    public void testDelete(){
        // 原来：加载配置文件，获取Factory对象，获取session
        Session session = HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();
        // 测试查询的方法 2个参数：arg0查询JavaBean的class对象 arg1主键的值
        User user = session.get(User.class, "402892816b84d45d016b84d45f4f0000");

        // 删除客户
        session.delete(user);

        // 提交事务
        tr.commit();
        // 释放资源
        session.close();
    }

    /**
     * 测试get()方法，获取查询，通过主键来查询一条记录
     */
    @Test
    public void testGet(){
        // 原来：加载配置文件，获取Factory对象，获取session
        Session session = HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();
        // 测试查询的方法 2个参数：arg0查询JavaBean的class对象 arg1主键的值
        User user = session.get(User.class, "402892816b84d491016b84d4937d0000");
        System.out.println(user);
        // 提交事务
        tr.commit();
        // 释放资源
        session.close();
    }

    /**
     * 测试load()方法，获取查询，通过主键来查询一条记录
     */
    @Test
    public void testLoad(){
        // 原来：加载配置文件，获取Factory对象，获取session
        Session session = HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();
        // 测试查询的方法 2个参数：arg0查询JavaBean的class对象 arg1主键的值
        User user = session.load(User.class, "402892816b84d491016b84d4937d0000");
        System.out.println(user);
        // 提交事务
        tr.commit();
        // 释放资源
        session.close();
    }

}
