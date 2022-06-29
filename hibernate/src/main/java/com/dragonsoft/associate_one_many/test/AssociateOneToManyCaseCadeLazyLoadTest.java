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
import java.util.Set;

/**
 * Hibernate一对多/多对1级联 操作时:关联级别的延迟加载，默认是开启的
 */
public class AssociateOneToManyCaseCadeLazyLoadTest {
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
     * 关联级别的延迟加载：默认是开启的
     *  如果要关闭：要在set标签上写lazy="false"
     */
    @Test
    public void testCasecadeLazyLoad(){
        Customer customer = session.get(Customer.class, "402892816bae2399016bae239c2a0001");
        System.out.println("================================================");
        System.out.println(customer.getOrders().size());
    }


}
