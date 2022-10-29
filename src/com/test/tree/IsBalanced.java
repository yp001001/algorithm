package com.test.tree;

import java.util.HashMap;

/*
判断是否是平衡二叉树
 */
public class IsBalanced {

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 必须保证子树也是平衡二叉树
        return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(height(root.left), height(root.right)) + 1;
    }
}
