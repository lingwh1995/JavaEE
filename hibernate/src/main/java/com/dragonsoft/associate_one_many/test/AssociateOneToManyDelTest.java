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
 * Hibernate一对多/多对1级联删除
 */
public class AssociateOneToManyDelTest {
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
     * 级联保存添加数据
     * 一方维护关联关系且双向关联
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
     * 测试：删除Customer，Customer下有2个Order
     *      在一方配置:cascade:delete
     */
    @Test
    public void testCascadeDel1(){
        // 先查询1号客户
        Customer c1 = session.get(Customer.class, "402892816ba8ef0b016ba8ef0df20001");
        session.delete(c1);
    }

    /**
     * 测试级联删除，删除客户，级联删除客户下的联系人
     *      在多方配置:cascade:delete
     */
    @Test
    public void testCascadeDel2(){
        Order order = session.get(Order.class, "402892816ba8eff4016ba8eff6a50001");
        session.delete(order);
    }

    /**
     * 孤儿删除:解除关系并删除孤儿记录
     *      在一方配置:cascade="delete-orphan"
     *      多方不能配置cascade
     */
    @Test
    public void testCascadeDelOrphan(){
        Customer customer = session.get(Customer.class, "402892816ba90acb016ba90acd5e0000");
        Order order = session.get(Order.class, "402892816ba90acb016ba90acd6b0001");
        //将其中第一个Order删除(将其中一个Order和Customer解除关系)
        customer.getOrders().remove(order);
    }

}
