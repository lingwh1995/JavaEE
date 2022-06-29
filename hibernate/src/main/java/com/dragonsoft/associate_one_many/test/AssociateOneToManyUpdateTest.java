package com.dragonsoft.associate_one_many.test;

import com.dragonsoft.associate_one_many.domain.Customer;
import com.dragonsoft.associate_one_many.domain.Order;
import com.dragonsoft.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AssociateOneToManyUpdateTest {
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
     * 放弃外键的维护
     *      在set标签配置：inverse="true"
     *      会导致级联保存子表没有外键值
     *      解决方案:
     *          在一方配置放弃外键维护，在多方配置级联保存,但不是影响级联删除
     *          级联保存时方向改为:保存多方的同时保存一方
     */
    @Test
    public void run11(){
        Customer customer = session.get(Customer.class, "402892816ba90acb016ba90acd5e0000");
        Order order = session.get(Order.class, "402892816ba90acb016ba90acd6c0002");

        // 做双向的关联
        customer.getOrders().add(order);
        order.setCustomer(customer);
    }
}
