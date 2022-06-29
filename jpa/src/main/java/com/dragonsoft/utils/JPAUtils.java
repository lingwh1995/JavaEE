package com.dragonsoft.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtils {
    private static final EntityManagerFactory ENTITYMANAGERFACTORY;
    private static final  EntityManager ENTITYMANAGER;
    private static final String PERSISTENCEUNIT  = "user";

    static{
        ENTITYMANAGERFACTORY = Persistence.createEntityManagerFactory(PERSISTENCEUNIT);
        ENTITYMANAGER = ENTITYMANAGERFACTORY.createEntityManager();
    }
    public static EntityManager getEntityManager(){
        return ENTITYMANAGER;
    }

}
