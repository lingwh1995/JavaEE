package com.dragonsoft.getload.test;

import com.dragonsoft.getload.domain.User;
import com.dragonsoft.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HibernateGetLoadTest {
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
     * 通过get方法获取，是直接加载的方式。在执行完get方法的时候，会直接去数据库中查询数据，获取到的结果是直接对象。
     * 当通过get方法获取数据库中不存在的数据的时候，最后数据库返回的是 null 值。
     * 通过load方法获取，是通过延迟加载的方式。在执行完load方法的时候，不会立马执行查询语句，暂时返回的是一个代理对象。
     * 当必须要用到这个直接对象中的数据的时候，才会去查询数据库，然后将这个直接对象添加到代理对象中通过代理对象进行包装。
     * 注意:
     * 当通过load方法获取数据库中不存在的数据的时候，程序会先返回一个代理对象。当需要用到直接对象的数据的时候，会查询为null，
     * 当将这个null添加到代理对象的时候，hibernate底层会抛出异常，不允许将一个null值添加到代理对象中。
     */


    /**
     * 测试get():正常查询
     */
    @Test
    public void testGet(){
        User user = session.get(User.class, "1");
        System.out.println(user);
    }

    /**
     * 测试get():延迟加载查询
     */
    @Test
    public void testLoad(){
        User user = session.load(User.class, "1");
        //只有当使用User对象的时候才会发送sql进行查询
        System.out.println(user);
    }

    /**
     * 测试get():当数据库中不存在改带该对象的时候，会抛出异常
     */
    @Test
    public void testLoadThrowException(){
        User user = session.load(User.class, "18888888888888888");
        //只有当使用User对象的时候才会发送sql进行查询
        System.out.println(user);
    }
}
