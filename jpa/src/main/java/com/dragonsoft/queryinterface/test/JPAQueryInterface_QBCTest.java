package com.dragonsoft.queryinterface.test;

import com.dragonsoft.queryinterface.domain.User;
import com.dragonsoft.utils.JPAUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

public class JPAQueryInterface_QBCTest {
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
     * QBC查询
     */
    @Test
    public void testFindAll(){
        //创建条件对象，并设置条件
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteria.from(User.class);
        Predicate predicate = criteriaBuilder.equal(userRoot.get("age"), 2);
        criteria.where(predicate);
        //查询时使用条件
        TypedQuery<User> typedQuery = entityManager.createQuery(criteria);
        List<User> userList = typedQuery.getResultList();
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
