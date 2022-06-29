package com.dragonsoft.queryinterface.test;

import com.dragonsoft.queryinterface.domain.User;
import com.dragonsoft.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HibernateQueryInterface_qbc_Test {

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
     * 1. 唯一标识OID的检索方式
     *      session.get(对象.class,OID)
     * 2. 对象的导航的方式
     Customer customer = session.get(Customer.class,OID);
     Set<Order> orders = customer.getOrder();
     * 3. HQL的检索方式
     * Hibernate Query Language	-- Hibernate的查询语言

     * 4. QBC的检索方式
     * Query By Criteria	-- 条件查询

     * 5. SQL检索方式（了解）
     * 本地的SQL检索
     */

    /**
     *  QBC的检索方式
     * Query By Criteria	-- 条件查询
     */

    /**
     * QBC的基本入门查询
     */
    @Test
    public void testQBCHelloWorld(){
        // 创建QBC查询接口
        Criteria criteria = session.createCriteria(User.class);
        List<User> users = criteria.list();
        for (User user : users) {
            System.out.println(user);
        }
    }


    /**
     * QBC的基本入门查询
     * 排序查询，调用的方法
     */
    @Test
    public void testQBCOrder(){
        // 创建QBC查询接口
        Criteria criteria = session.createCriteria(User.class);
        // 调用排序的方法，addOrder()
        //criteria.addOrder(Order.desc("age"));
        criteria.addOrder(Order.asc("age"));
        List<User> users = criteria.list();
        for (User user : users) {
            System.out.println(user);
        }
    }


    /**
     * QBC分页的方法和HQL分页的方式一样的
     */
    @Test
    public void testQBCPageAndOrder(){
        // 创建QBC查询接口
        Criteria criteria = session.createCriteria(User.class);
        // 调用排序的方法，addOrder()
        criteria.addOrder(Order.desc("age"));

        // 设置分页的方法
        criteria.setFirstResult(0);
        criteria.setMaxResults(3);

        List<User> users = criteria.list();
        for (User user : users) {
            System.out.println(user);
        }
    }


    /**
     * QBC的条件查询
     */
    @Test
    public void testQBCCondition(){
        // 创建QBC查询接口
        Criteria criteria = session.createCriteria(User.class);

        // 使用方法添加条件  and
        // criteria.add(Restrictions.eq("age", 20));
        // criteria.add(Restrictions.ge("age", 20));
        // criteria.add(Restrictions.gt("age", 20));
         criteria.add(Restrictions.between("age", 0, 25));

        List<User> users = criteria.list();
        for (User user : users) {
            System.out.println(user);
        }
    }


    /**
     * in查询
     */
    @Test
    public void testQBCBetween(){
        // 创建QBC查询接口
        Criteria criteria = session.createCriteria(User.class);
        // SQL：select * from User where age in (20,28);
        List<Integer> params = new ArrayList<Integer>();
        params.add(20);
        params.add(28);

        // 使用in 方法查询
        criteria.add(Restrictions.in("age", params));

        List<User> users = criteria.list();
        for (User user : users) {
            System.out.println(user);
        }
    }


    /**
     * 演示QBC的or方法
     */
    @Test
    public void testQBCOr(){
        // 创建QBC查询接口
        Criteria criteria = session.createCriteria(User.class);
        // SQL：select * from t_user where age = 20 or age > 28;
        criteria.add(Restrictions.or(Restrictions.eq("age", 20), Restrictions.gt("age", 28)));

        List<User> users = criteria.list();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 判断值是否为空
     */
    @Test
    public void testQBCIsNullAndIsNotNull(){
        // 创建QBC查询接口
        Criteria criteria = session.createCriteria(User.class);
        // 找所有的lkm_email是空的值
        //criteria.add(Restrictions.isNull("age"));
        criteria.add(Restrictions.isNotNull("age"));
        List<User> users = criteria.list();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 聚合函数的查询
     */
    @Test
    public void testQBCCount(){
        // 创建QBC查询接口
        Criteria criteria = session.createCriteria(User.class);
        // 设置聚合函数的方式
        List<Number> list = criteria.setProjection(Projections.count("id")).list();
        Long count = list.get(0).longValue();
        System.out.println(count);
    }

    /**
     * 强调问题： select count(*) from 表，又想查select * from 表单，存在问题
     *      解决方法:criteria.setProjection(null);j
     */
    @Test
    public void run9(){
        // 创建QBC查询接口
        Criteria criteria = session.createCriteria(User.class);
        // 设置聚合函数的方式  select count(id) from 表;  5
        criteria.setProjection(Projections.count("id"));
        List<Number> list = criteria.list();
        Long count = list.get(0).longValue();
        System.out.println(count);

        criteria.setProjection(null);

        List<User> users = criteria.list();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 演示离线条件对象
     *      离线条件查询对象:脱离session对象创建criteria对象，可以在web层创建对象
     */
    @Test
    public void testQBCDetachedCriteria(){
        // 创建离线条件查询的对象
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
        // 添加查询的条件了
        criteria.add(Restrictions.eq("age", 20));

        // 查询了
        List<User> users = criteria.getExecutableCriteria(session).list();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
