package com.dragonsoft.annotation.test;

import com.dragonsoft.annotation.domain.TreeNode;
import com.dragonsoft.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * Hibernate一对多/多对1级联保存
 */
public class AnnotationOneToManySelfSaveTest {

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
     * 测试自连接级联保存
     */
    @Test
    public void testAssociateOneToManySelfSave(){
        TreeNode parentTreeNode = session.get(TreeNode.class, "1");
        TreeNode childTreeNode = new TreeNode();
        childTreeNode.setTreeNodeType(2);
        childTreeNode.setParentNodeId(1);
        childTreeNode.setTreeNodeName("测试子节点");
        //一方维护关系
        parentTreeNode.getChildTreeNodes().add(childTreeNode);
        //保存一方
        session.save(parentTreeNode);
    }


}
