package com.dragonsoft.queryinterface.test;

import com.dragonsoft.queryinterface.domain.Customer;
import com.dragonsoft.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class HibernateQueryInterface_oid_Test {

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
    public void testQueryByOid(){
        Customer customer = session.get(Customer.class, "402892816bad993a016bad993cca0001");
        System.out.println(customer);
    }

}
