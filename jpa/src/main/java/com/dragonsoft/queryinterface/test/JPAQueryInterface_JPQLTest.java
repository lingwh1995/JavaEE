package com.dragonsoft.queryinterface.test;

import com.dragonsoft.queryinterface.domain.User;
import com.dragonsoft.utils.JPAUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public class JPAQueryInterface_JPQLTest {
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
     * 查询所有
     */
    @Test
    public void testFindAll(){
        String jpql = "from User";
        Query query = entityManager.createQuery(jpql);
        List<User> userList = query.getResultList();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    /**
     * 查询所有并排序+nulls first/last
     */
    @Test
    public void testFindAllAndSort(){
        String jpql = "from User u order by u.age asc nulls first";
        Query query = entityManager.createQuery(jpql);
        List<User> userList = query.getResultList();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    /**
     * count查询
     */
    @Test
    public void testCount(){
        String jpql = "select  count(*) from User";
        Query query = entityManager.createQuery(jpql);
        Number count = (Number)query.getSingleResult();
        System.out.println("数量:"+count);
    }

    /**
     * 分页查询
     */
    @Test
    public void testPage(){
        String jpql = "from User";
        Query query = entityManager.createQuery(jpql);
        // 分页查询，调用方法，查询第一页的数据 1-3条
        query.setFirstResult(0);
        query.setMaxResults(3);
        List<User> userList = query.getResultList();
        for (User user : userList) {
            System.out.println(user);
        }
    }


    /**
     * 条件查询
     */
    @Test
    public void testCondition1(){
        String jpql = "from User u where u.age = ?";
        Query query = entityManager.createQuery(jpql);
        query.setParameter(1,28);
        List<User> userList = query.getResultList();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    /**
     * 条件查询
     */
    @Test
    public void testCondition2(){
        String jpql = "from User u where u.age = :age";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("age",28);
        List<User> userList = query.getResultList();
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
