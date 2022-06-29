package com.dragonsoft.annotation.test;

import com.dragonsoft.annotation.domain.Customer;
import com.dragonsoft.annotation.domain.Order;
import com.dragonsoft.annotation.domain.User;
import com.dragonsoft.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 测试Hibernate注解形式一对多
 */
public class AnnotationOneToManyTest {
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
     * 测试由一方维护关联关系
     */
    @Test
    public void testAnnotationOneToMany1(){
        Customer customer = new Customer();
        customer.setAge(59);
        customer.setName("张三");
        Order order = new Order();
        order.setPrice(18.9);
        //一方维护关联关系
        customer.getOrders().add(order);
        session.save(customer);
    }

    /**
     * 测试由多方维护关联关系
     */
    @Test
    public void testAnnotationOneToMany2(){
        Customer customer = new Customer();
        customer.setAge(59);
        customer.setName("张三");
        Order order = new Order();
        order.setPrice(18.9);
        //多方维护关联关系
        order.setCustomer(customer);
        session.save(order);
    }
}
