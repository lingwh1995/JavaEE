package com.dragonsoft.associate_one_many.test;

import com.dragonsoft.associate_one_many.domain.Customer;
import com.dragonsoft.associate_one_many.domain.Order;
import com.dragonsoft.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

/**
 * Hibernate一对多/多对1级联保存
 */
public class AssociateOneToManySaveTest {

    private Session session;
    private Transaction transaction;

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
     * 测试级联查询
     */
    @Test
    public void testCasecadeQuery(){
        Customer customer = session.get(Customer.class, "1");
        System.out.println(customer);
    }

    /**
     * 测试单向级联保存
     *      保存Customer(一方)同时保存Order(多方)
     */
    @Test
    public void testCasecadeSave1(){
        Order o1 = new Order();
        o1.setPrice(18.6);
        Order o2 = new Order();
        o2.setPrice(29.6);
        Customer customer = new Customer( "lisi", 28);
        customer.getOrders().add(o1);
        customer.getOrders().add(o2);
        Serializable id = session.save(customer);
        System.out.println(id);
    }

    /**
     * 测试单向级联保存
     *      保存Order(多方)同时保存Customer(一方)
     *      在一方配置cascade:save-update
     * 当set上配置inverse=true时，保存效果为Order对应的表不会把外键保存进去
     */
    @Test
    public void testCasecadeSave2(){
        Customer customer = new Customer("wangwu",18);
        Order order = new Order();
        order.setPrice(18.6);
        //一方维护关系
        customer.getOrders().add(order);
        //一方维护关系:同时保存一方和多方
        session.save(customer);
        session.save(order);
    }

    /**
     * 测试单向级联保存
     *      保存Order(多方)同时保存Customer(一方)
     *      在多方配置cascade:save-update
     */
    @Test
    public void testCasecadeSave3(){
        Customer customer = new Customer("wangwu",18);
        Order order = new Order();
        order.setPrice(18.6);
        //一方维护关系
        customer.getOrders().add(order);
        //多方维护关系
        order.setCustomer(customer);
        //一方和多方同时维护关系的时候:保存一方或者多方都可以实现级联保存
            //注意:当set上配置inverse=true时，保存效果为多方无法保存
        //Serializable id1 = session.save(order);
        session.save(customer);
    }

    /**
     * 测试单向级联保存
     *      保存Order(多方)同时保存Customer(一方)
     *      在多方配置cascade:save-update
     */
    @Test
    public void testCasecadeSave4(){
        Customer customer = new Customer("wangwu",18);
        Order order = new Order();
        order.setPrice(18.6);
        //一方维护关系：下面这行不用写，写了和不写是一样的，关系已经交由多方维护了
        //customer.getOrders().add(order);
        //多方维护关系
        order.setCustomer(customer);
        //多方维护关系:只保存多方就可以了
        Serializable id1 = session.save(order);
        System.out.println(id1);
    }
}
