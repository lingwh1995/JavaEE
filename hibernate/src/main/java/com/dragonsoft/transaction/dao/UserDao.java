package com.dragonsoft.transaction.dao;

import com.dragonsoft.transaction.domain.User;
import com.dragonsoft.utils.HibernateUtil;
import org.hibernate.Session;

public class UserDao {
    /**
     * 注意:Service开事物，dao不能close
     * @param u1
     */
    public void save1(User u1){
        Session session = HibernateUtil.getCurrentSession();
        session.save(u1);
    }

    /**
     * 注意:Service开事物，dao不能close
     * @param u2
     */
    public void save2(User u2){
        Session session = HibernateUtil.getCurrentSession();
        session.save(u2);
    }
}
