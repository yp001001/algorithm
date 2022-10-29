package com.test.tree;

import javax.xml.soap.Node;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树
可以按 任意顺序 返回答案。
 */
public class GenerateTrees {

    public static void main(String[] args) {
        List<TreeNode> treeNodes = generateTrees(3);
        System.out.println(treeNodes);
    }


    public static List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<TreeNode>();
        }
        return createTree(1, n);
    }

    public static List<TreeNode> createTree(int l, int r) {
        LinkedList<TreeNode> treeNodes = new LinkedList<>();
        if (l > r) {
            treeNodes.add(null);
            return treeNodes;
        }
        for (int i = l; i <= r; i++) {
            List<TreeNode> lnodes = createTree(l, i - 1);

            List<TreeNode> rnodes = createTree(i + 1, r);

            // 左子树拿一个，右子树拿一个连接到root节点
            for (TreeNode lnode : lnodes) {
                for (TreeNode rnode : rnodes) {
                    TreeNode treeNode = new TreeNode(i);
                    treeNode.left = lnode;
                    treeNode.right = rnode;
                    treeNodes.add(treeNode);
                }
            }
        }
        return treeNodes;
    }
}


