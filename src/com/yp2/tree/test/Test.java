package com.yp2.tree.test;

import com.test.tree.KthSmallest;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class Test {
    public static void main(String[] args) {

    }


    /**
     * 判断是否是奇偶树
     */
    public boolean isEvenOddTree(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 保存前一个树
            int prev = level % 2 == 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                int value = node.val;
                if (level % 2 == value % 2) {
                    return false;
                }
                if ((level % 2 == 0 && value <= prev) || (level % 2 == 1 && value >= prev)) {
                    return false;
                }
                prev = value;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            level++;
        }
        return true;
    }


    int count = 1, ans = 0;

    public int kthLargest(TreeNode root, int k) {
        helper(root, k);
        return ans;
    }

    public void helper(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        // 右根先
        helper(root.right, k);
        // 递归回去的时候count没有改变，导致不正确
        if (++count == k) {
            ans = root.val;
            return;
        }
        helper(root.left, k);
    }

    public static void mid(TreeNode root) {
        if (root == null) {
            return;
        }
        mid(root.left);
        System.out.println(root.val);
        mid(root.right);
    }

}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
