package com.dragonsoft.queryinterface.test;

import com.dragonsoft.queryinterface.domain.Customer;
import com.dragonsoft.queryinterface.domain.User;
import com.dragonsoft.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HibernateQueryInterface_hql_Test {

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
     * HQL的检索方式
     * Hibernate Query Language	-- Hibernate的查询语言
     */
    @Test
    public void testHqlConditionGt(){
        // 查询的方式 HQL from User where 属性 条件
        // SQL：select * from t_user where 字段 条件
        Query query = session.createQuery("from User where age > ?");
        // 设置值
        query.setInteger(0, 18);
        // 查询
        List<User> list = query.list();
        for (User user : list) {
            System.out.println(user);
        }
    }


    @Test
    public void testHqlConditionLike(){
        Query query = session.createQuery("from User where name like ?");
        // 设置值
        query.setString(0, "%王%");
        // 查询
        List<User> list = query.list();
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void testHqlNameCondition(){
        Query query = session.createQuery("from User where age > :aaa");
        query.setInteger("aaa", 18);
        // 查询
        List<User> list = query.list();
        for (User user : list) {
            System.out.println(user);
        }
    }

    /**
     * hql sum查询
     */
    @Test
    public void testHqlSum(){
        // 查询的所有的Customer的数量
        List<Number> userList = session.createQuery("select sum(u.age) from User u").list();
        // 通过下标值取值
        Long count = userList.get(0).longValue();
        System.out.println("数量："+count);
    }

    /**
     * hql count查询：获取总记录数
     */
    @Test
    public void testHqlCount(){
        // 查询的所有的Customer的数量
        //List<Number> userList = session.createQuery("select count(u.age) from User u").list();
        List<Number> userList = session.createQuery("select count(1) from User u").list();
        // 通过下标值取值
        Long count = userList.get(0).longValue();
        System.out.println("数量："+count);
    }

    /**
     * hql count查询：获取总记录数
     */
    @Test
    public void testHqlCount1(){
        // 查询的所有的Customer的数量
        List<User> userList = session.createQuery("from User ").list();
        // 通过下标值取值
        int count = userList.size();
        System.out.println("数量："+count);
    }

    /**
     * hql count查询：获取总记录数
     */
    @Test
    public void testHqlCount2(){
        Query query = session.createQuery("select sum(u.age) from User u");
        Number count = (Number)query.uniqueResult();
        System.out.println(count);
        System.out.println("数量："+count);
    }

    /**
     * 不使用投影查询,只查询某几个字段
     */
    @Test
    public void testHqlFakeShadow(){
        // 查询联系人
        Query query = session.createQuery("select c.id,c.age from Customer c");
        List<Object[]> list = query.list();
        for (Object[] o : list) {
            System.out.println(Arrays.toString(o));
        }
    }

    /**
     * 投影查询：只查询几个字段，不是所有的字段
     * 第一步：需要在JavaBean类提供对应的构造方法
     * 第二步：HQL语句的发生变化
     */
    @Test
    public void testHqlReallyShadow(){
        // 查询联系人
        Query query = session.createQuery("select new Customer(c.name,c.age) from Customer c");
        List<Customer> list = query.list();
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
    /**
     * 投影查询：只查询几个字段，不是所有的字段
     * 第一步：需要在JavaBean类提供对应的构造方法
     * 第二步：HQL语句的发生变化
     */
    @Test
    public void testHqlReallyShadow2(){
        // 查询联系人
        Query query = session.createQuery("select new Customer(c.id,c.name,c.age) from Customer c");
        List<Customer> list = query.list();
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }


    /**
     * HQL分页查询的两个方法
     * 	* setFirstResult(a)		-- 从哪条记录开始，如果查询是从第一条开启，值是0
     * setMaxResults(b)		-- 每页查询的记录条数
     */
    @Test
    public void testHqlPage(){
        // 查询联系人
        Query query = session.createQuery("from User");

        // 分页查询，调用方法，查询第一页的数据 1-3条
		query.setFirstResult(0);
		query.setMaxResults(3);

        // 查询第二页的数据 query.setFirstResult(3);	(当前页-1)*pageSize=3
//        query.setFirstResult(3);
//        query.setMaxResults(3);

        List<User> users = query.list();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 排序查询
     * SQL：order by 字段 asc/desc;
     * HQL：关键字是一样的，都是有order by 属性
     */
    @Test
    public void testHqlOrder(){
        // 查询联系人
        List<User> users = session.createQuery("from User u order by u.age desc").list();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 排序查询+多表查询+解决bean冲突:一条记录发一个sql,会导致发很多条sql所以慎用
     * SQL：order by 字段 asc/desc;
     * HQL：关键字是一样的，都是有order by 属性
     */
    @Test
    public void testHqlManyTableList1(){
        Query query = session.createQuery("from com.dragonsoft.queryinterface.domain.Customer");
        List<Customer> customers = query.list();
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    /**
     * hql单表查询:所有记录只发一个sql
     */
    @Test
    public void testHqlOneTableList(){
        Query query = session.createQuery("from User");
        List<User> list = query.list();
        for (User customer : list) {
            System.out.println(customer);
        }
    }

    /**
     * 查询的客户，客户和联系人有关联啊
     * select * from t_customer c,t_order o where c.id = o.id;
     */
    @Test
    public void testHqlJoinSelect(){
        // 内连接的查询
        Query query = session.createQuery("from Customer c inner join c.orders");
        // 默认的返回值是数组
        List<Object[]> list = query.list();
        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
        }
    }

    /**
     * 数据默认返回的数组，把数据封装到对象中
     * 提供关键字：fetch 迫切连接，使用fetch关键字，把数据封装到对象中
     */
    @Test
    public void testHqlJoinSelectFetch(){
        // 内连接的查询
        Query query = session.createQuery("from com.dragonsoft.queryinterface.domain.Customer c inner join fetch c.orders");
        // 默认的返回值是数组
        List<Customer> list = query.list();
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }


    /**
     * 数据的重复的问题
     */
    @Test
    public void testHqlJoinSelectFetchWithnoRepeat(){
        // 内连接的查询
        Query query = session.createQuery("from com.dragonsoft.queryinterface.domain.Customer c inner join fetch c.orders");
        // 默认的返回值是数组
        List<Customer> list = query.list();
        // 手动解决，编程中都使用这种方式解决重复的问题
        Set<Customer> set = new HashSet<Customer>(list);
        for (Customer customer : set) {
            System.out.println(customer);
        }
    }

}
