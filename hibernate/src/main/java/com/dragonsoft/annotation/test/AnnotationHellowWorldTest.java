package com.dragonsoft.annotation.test;

import com.dragonsoft.annotation.domain.User;
import com.dragonsoft.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AnnotationHellowWorldTest {
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

    @Test
    public void testAnnotationSave(){
        User user = new User();
        user.setAge(2);
        session.save(user);
    }

    /**
     * 测试注解形式的二级缓存
     */
    @Test
    public void testAnnocationCache(){
        User u1 = session.get(User.class, "402892816b8f6551016b8f6555b00000");
        System.out.println(u1);
        //清空一级缓存
        session.clear();
        User u2 = session.get(User.class, "402892816b8f6551016b8f6555b00000");
        System.out.println(u2);
    }
}
