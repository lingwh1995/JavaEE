package com.dragonsoft.associate_one_many_self.test;

import com.dragonsoft.associate_one_many_self.domain.TreeNode;
import com.dragonsoft.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * Hibernate一对多/多对1级联保存
 */
public class AssociateOneToManySelfSaveTest {

    private Session session;
    private Transaction transaction;

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
     * 测试自连接级联查询
     */
    @Test
    public void testAssociateOneToManySelfGet(){
        TreeNode treeNode = session.get(TreeNode.class, "1");
        System.out.println(treeNode.getChildTreeNodes());
    }
    /**
     * 测试自连接级联查询
     */
    @Test
    public void testAssociateOneToManySelfSave(){
        TreeNode treeNode = session.get(TreeNode.class, "1");
        System.out.println(treeNode.getChildTreeNodes());
    }


}
