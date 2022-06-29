package com.dragonsoft.transaction.service;

import com.dragonsoft.transaction.dao.UserDao;
import com.dragonsoft.transaction.domain.User;
import com.dragonsoft.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserService {
    public void save(User u1,User u2){
        UserDao dao = new UserDao();
        // 获取session
        Session session = HibernateUtil.getCurrentSession();
        // 开启事务
        Transaction tr = session.beginTransaction();
        try {
            dao.save1(u1);
            int a = 10/0;
            dao.save2(u2);
            // 提交事务
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // 出现问题：回滚事务
            tr.rollback();
        }finally{
            // 自己释放资源，现在，session不用关闭，线程结束了，自动关闭的！！
        }
    }
}
