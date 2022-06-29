package com.dragonsoft.queryinterface.test;

import com.dragonsoft.associate_one_many.domain.Customer;
import com.dragonsoft.associate_one_many.domain.Order;
import com.dragonsoft.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

public class InitDate {
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
     * 测试单向级联保存
     *      保存Order(多方)同时保存Customer(一方)
     *      在多方配置cascade:save-update
     */
    @Test
    public void initDate(){
        Customer customer = new Customer("wangwu",18);
        Order o1 = new Order();
        o1.setPrice(18.6);
        Order o2 = new Order();
        o2.setPrice(18.6);
        //多方维护关系
        o1.setCustomer(customer);
        o2.setCustomer(customer);
        //多方维护关系:只保存多方就可以了
        session.save(o1);
        session.save(o2);
    }


}
