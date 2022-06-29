package com.dragonsoft.associate_one_many_self.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 自连接:一对多双向关联的特殊情况
 */
public class TreeNode {
    private String treeNodeId;  //权限id
    private String  treeNodeName; //权限名
    private Integer treeNodeType;
    private Integer parentNodeId;  //它的父权限Id

    //一个儿子一个父亲
    private TreeNode parentTreeNode;
    //一个父亲多个儿子
    private Set<TreeNode> childTreeNodes=new HashSet<TreeNode>();

    public TreeNode() {

    }

    public String getTreeNodeId() {
        return treeNodeId;
    }

    public void setTreeNodeId(String treeNodeId) {
        this.treeNodeId = treeNodeId;
    }

    public String getTreeNodeName() {
        return treeNodeName;
    }

    public void setTreeNodeName(String treeNodeName) {
        this.treeNodeName = treeNodeName;
    }

    public Integer getTreeNodeType() {
        return treeNodeType;
    }

    public void setTreeNodeType(Integer treeNodeType) {
        this.treeNodeType = treeNodeType;
    }

    public Integer getParentNodeId() {
        return parentNodeId;
    }

    public void setParentNodeId(Integer parentNodeId) {
        this.parentNodeId = parentNodeId;
    }

    public TreeNode getParentTreeNode() {
        return parentTreeNode;
    }

    public void setParentTreeNode(TreeNode parentTreeNode) {
        this.parentTreeNode = parentTreeNode;
    }

    public Set<TreeNode> getChildTreeNodes() {
        return childTreeNodes;
    }

    public void setChildTreeNodes(Set<TreeNode> childTreeNodes) {
        this.childTreeNodes = childTreeNodes;
    }

    /**
     * parentTreeNode和childNodes只能打印一个，否则会造成循环引用
     * @return
     */
    @Override
    public String toString() {
        return "TreeNode{" +
                "treeNodeId='" + treeNodeId + '\'' +
                ", treeNodeName='" + treeNodeName + '\'' +
                ", treeNodeType=" + treeNodeType +
                ", parentNodeId=" + parentNodeId +
                ", parentTreeNode=" + parentTreeNode +
                '}';
    }
}
