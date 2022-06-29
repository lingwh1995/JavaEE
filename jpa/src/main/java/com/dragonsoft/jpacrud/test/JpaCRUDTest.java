package com.dragonsoft.jpacrud.test;

import com.dragonsoft.jpacrud.domain.User;
import com.dragonsoft.utils.JPAUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class JpaCRUDTest {

    /**
     * JPA使用步骤:
     *      1.加载配置文件，创建工厂(实体管理器工厂)对象
     *          注意:EntityManagerFactory是线程安全的，和Hinernate中SessionFactory的一样，是重量级的
     *      2.通过实体管理器工厂获取实体管理器
     *              EntityManager:实体类管理器
     *                  EntityManager em = new EntityManager();
     *                  em.persist():保存
     *                  em.merge():更新
     *                  em.remove():删除
     *                  em.find()/getReference():根据id查询
     *                  Transaction tr = em.getTransaction():获取事物操作对象
     *                     tr.begin():开启事物
     *                     tr.commit():提交事物
     *                     tr.rollback():回滚事物
     *
     *      3.获取事物对象，开启事物
     *      4.完成增删改查操作
     *      5.提交/回滚事物
     *      6.释放资源
     */

    private static EntityManager entityManager;
    private static EntityTransaction transaction;

    @Before
    public void before(){
        entityManager = JPAUtils.getEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();
    }

    @After
    public void after(){
        transaction.commit();
        entityManager.close();
    }

    /**
     * 测试查保存方法
     */
    @Test
    public void testJpaPersit(){
        //完成增删改查操作
        User user = new User();
        user.setName("asfasfasdfasd");
        entityManager.persist(user);
    }

    /**
     * 测试查更新方法1
     *      merger()
     */
    @Test
    public void testJpaMerge1(){
        User user = entityManager.find(User.class, "402892816b84d3fd016b84d3ff2b0000");
        user.setName("xxxxx");
        user = entityManager.merge(user);
        System.out.println(user);
    }

    /**
     * 测试查更新方法2
     *      不调用merge()方法,持久化的对象有自动更新对象的能力
     */
    @Test
    public void testJpaMerge2(){
        User user = entityManager.find(User.class, "402892816b84d3fd016b84d3ff2b0000");
        user.setName("xxxxx");
        System.out.println(user);
    }

    /**
     * 测试查删除方法
     */
    @Test
    public void testJpaRemove(){
        User user = entityManager.find(User.class, "402892816b84d3fd016b84d3ff2b0000");
        entityManager.remove(user);
    }

    /**
     * 测试查询方法
     *      非延迟加载查询
     */
    @Test
    public void testJpaFind(){
        User user = entityManager.find(User.class, "402892816b84d3fd016b84d3ff2b0000");
        System.out.println(user);
    }


    /**
     * 通过find()方法获取，是直接加载的方式。在执行完get方法的时候，会直接去数据库中查询数据，获取到的结果是直接对象。
     * 当通过find()方法获取数据库中不存在的数据的时候，最后数据库返回的是 null 值。
     * 通过getReference()方法获取，是通过延迟加载的方式。在执行完getReference()方法的时候，不会立马执行查询语句，暂时返回的是一个代理对象。
     * 当必须要用到这个直接对象中的数据的时候，才会去查询数据库，然后将这个直接对象添加到代理对象中通过代理对象进行包装。
     * 注意:
     * 当通过getReference()方法获取数据库中不存在的数据的时候，程序会先返回一个代理对象。当需要用到直接对象的数据的时候，会查询为null，
     * 当将这个null添加到代理对象的时候，JPA底层会抛出异常，不允许将一个null值添加到代理对象中。
     */


    /**
     * 测试查询方法
     *      延迟加载查询
     */
    @Test
    public void testJpaGetReference(){
        User user = entityManager.getReference(User.class, "402892816b84d3fd016b84d3ff2b0000");
        //当延迟加载时，不使用查询的对象，不会请求DB，所以不会发送SQL语句
        //System.out.println(user);
    }

}
