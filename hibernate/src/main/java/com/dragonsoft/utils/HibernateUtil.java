package com.dragonsoft.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final Configuration CFG;
    private static final SessionFactory FACTORY;
    static{
        // 给常量赋值
        // 加载配置文件
        CFG = new Configuration().configure();
        // 生成factory对象
        FACTORY = CFG.buildSessionFactory();
    }

    /**
     * 获取Session对象
     * @return
     */
    public static Session getSession(){
        return FACTORY.openSession();
    }

    /**
     * 从ThreadLocal类中获取到session的对象：
     *      使用前开启绑定本地的session
     *      <property name="hibernate.current_session_context_class">thread</property>
     * @return
     */
    public static Session getCurrentSession(){
        return FACTORY.getCurrentSession();
    }

    public static void closeSession(Session session){
        if(session!=null){
            session.getTransaction().commit();
            session.close();
        }
    }


}
