package com.dragonsoft.jpahellowrold.test;

import com.dragonsoft.jpahellowrold.domain.User;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaHelloWorldTest {

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
    @Test
    public void testJpaSaveHelloWorld(){
        //1.加载配置文件，创建工厂(实体管理器工厂)对象
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("user");
        //2.通过实体管理器工厂获取实体管理器
        EntityManager em = factory.createEntityManager();
        //3.获取事物对象，开启事物
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        //4.完成增删改查操作
        User user = new User();
        user.setName("asfasfasdfasd");
        //5.提交/回滚事物
        em.persist(user);
        tr.commit();
        //6.释放资源
        em.close();
    }

}
