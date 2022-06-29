package com.dragonsoft.queryinterface.test;

import com.dragonsoft.queryinterface.domain.User;
import com.dragonsoft.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class HibernateQueryInterface_sqlquery_Test {

    private static Session session;
    private static Transaction transaction;

    @Before
    public void before(){
        session = HibernateUtil.getSession();
        transaction = session.beginTransaction();
    }

    @After
    public void after(){
        transaction.commit();
        session.close();
    }

    /**
     * 1. 唯一标识OID的检索方式
     *      session.get(对象.class,OID)
     * 2. 对象的导航的方式
     Customer customer = session.get(Customer.class,OID);
     Set<Order> orders = customer.getOrder();
     * 3. HQL的检索方式
     * Hibernate Query Language	-- Hibernate的查询语言

     * 4. QBC的检索方式
     * Query By Criteria	-- 条件查询

     * 5. SQL检索方式（了解）
     * 本地的SQL检索
     */

    /**
     * 测试唯一标识OID的检索方式
     */
    @Test
    public void testSqlQueryReturnObect(){
        // 创建的是SQL的查询的接口
        SQLQuery query = session.createSQLQuery("select * from T_USER");
        // 查询数据
        List<Object[]> list = query.list();
        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
        }
    }
    /**
     * 把数据封装到对象中
     */
    @Test
    public void testSqlQueryReturnUser(){
        // 创建的是SQL的查询的接口
        SQLQuery query = session.createSQLQuery("select * from T_USER");
        // 通过方法设置
        query.addEntity(User.class);
        List<User> users = query.list();
        for (User user : users) {
            System.out.println(user);
        }
    }

}
