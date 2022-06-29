package com.dragonsoft.cache.test;

import com.dragonsoft.cache.domain.User;
import com.dragonsoft.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.io.Serializable;
import java.util.List;

public class HibernateCacheTest {
    /**
     * 持久态的对象有自动更新数据库的能力
     * session的一级缓存！！
     */
    @Test
    public void run1(){
        Session session = HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();

        // 获取到持久态的对象
        User user = session.get(User.class,"1");
        // user是持久态，有自动更新数据库的能力
        System.out.println(user.getName());

        // 重新设置新的名称
        user.setName("隔离老王");

        // 正常编写代码
        // session.update(user);

        tr.commit();
        session.close();
    }

    /**
     * 证明：一级缓存是存在的
     */
    @Test
    public void run2(){
        Session session = HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();

        // 创建对象
        User user = new User();
        user.setName("哈哈");
        user.setAge(20);

        // 保存用户，user一级存入到session的缓存中
        Serializable id = session.save(user);
        System.out.println(id);

        // 获取对象，不会看到SQL语句
        User user2 = session.get(User.class, id);
        System.out.println(user2.getName());

        tr.commit();
        session.close();
    }

    @Test
    public void run3(){
        Session session = HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();

        // 最简单的证明，查询两次
        User user1 = session.get(User.class, "1");
        System.out.println(user1.getName());

        User user2 = session.get(User.class, "1");
        System.out.println(user2.getName());

        tr.commit();
        session.close();
    }
    /**
     * 快照机制
     */
    @Test
    public void run4(){
        Session session = HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();
        // 获取到持久态的对象
        User user = session.get(User.class,"1");
        // 重新设置新的名称
        user.setName("隔离老王1");
        tr.commit();
        session.close();
    }


    /**
     * Session.clear()	-- 清空缓存。
     */
    @Test
    public void run5(){
        Session session = HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();

        // 最简单的证明，查询两次
        User user1 = session.get(User.class, "1");
        System.out.println(user1.getName());

        // 清空缓存
        session.clear();

        User user2 = session.get(User.class, "1");
        System.out.println(user2.getName());

        tr.commit();
        session.close();
    }

    /**
     * Session.evict()	-- 清除的指定的对象
     */
    @Test
    public void run6(){
        Session session = HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();

        // 最简单的证明，查询两次
        User user1 = session.get(User.class, "1");
        System.out.println(user1.getName());

        // 清除user1对象
        session.evict(user1);

        User user2 = session.get(User.class, "1");
        System.out.println(user2.getName());

        tr.commit();
        session.close();
    }

    /**
     * 快照机制
     */
    @Test
    public void run7(){
        Session session = HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();
        // 获取到持久态的对象
        User user = session.get(User.class,"1");
        // 重新设置新的名称
        user.setName("隔离老王");

        // 自动刷新缓存
        session.flush();

        tr.commit();
        session.close();
    }

    /**
     * 测试二级缓存
     *      二级缓存的类缓存的是类的详情/内容，不是类的引用，缓存区域是实体缓存区域
     *      二级缓存的集合缓存的是集合中包含的类的id/不缓存类的内容，缓存区域是集合缓存区域
     */
    @Test
    public void testSecondLevelCache(){
        Session session = HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();

        // 获取到持久态的对象
        String hql  = "from User";
        List<User> u1 = session.createQuery(hql).list();
        System.out.println(u1.get(0));
        //从一级缓存中清除指定的U1对象
        session.evict(u1.get(0));

        //查询同一个对象，查询之前清空一级缓存
        User u2 = session.get(User.class,"1");
        System.out.println(u2);
        System.out.println(u1 ==  u2);

        //查询同一个对象，查询之前清空一级缓存
        User u3 = session.get(User.class,"1");
        System.out.println(u3);
        System.out.println(u1 ==  u2);
        System.out.println(u2 ==  u3);
        tr.commit();
        session.close();
    }

    /**
     * 测试Query缓存:
     *      Query查询缓存不存放在一二级缓存中，放在自己独有缓存中
     * uniqueResult()：数据库中只有一条数据的时候用这个方法
     */
    @Test
    public void testQueryCache2(){
        Session session = HibernateUtil.getSession();
        Transaction tr = session.beginTransaction();
        //第一次Query查询
        String hql = "from User";
        User u1 = (User) session.createQuery(hql).uniqueResult();
        //第二次Query查询
        User list2 = (User) session.createQuery(hql).uniqueResult();
        tr.commit();
        session.close();
    }

}
