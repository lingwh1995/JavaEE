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
 * inverse和casecade同时使用
 */
public class AssociateOneToManySaveUserInverseAndCascadeTest {
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
     * inverse和casecade同时使用
     *      采用只保存多方的方式进行级联保存
     */
    @Test
    public void testCasecadeSave2(){
        Customer customer = new Customer("wangwu",18);
        Order order = new Order();
        order.setPrice(18.6);
        //一方维护关系
        //customer.getOrders().add(order);
        //多方维护关系
        order.setCustomer(customer);
        //多方维护关系:只保存多方就可以了
        Serializable id1 = session.save(order);
        System.out.println(id1);
    }
}
