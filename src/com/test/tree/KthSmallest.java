package com.test.tree;

import java.util.Stack;

/*
寻找二叉搜索树中第k个最小元素（符合中序遍历的思想）
 */
public class KthSmallest {

    public static void main(String[] args) {
        KthSmallest smallest = new KthSmallest();
        TreeNode treeNode = smallest.new TreeNode(4);
        treeNode.left = smallest.new TreeNode(3);
        treeNode.left = smallest.new TreeNode(2);
        treeNode.right = smallest.new TreeNode(5);
        kthSmallest(treeNode, 2);
    }


    public static int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                // 先将所有的左节点保存在栈中
                root = root.left;
            }
            root = stack.pop();
            k--;
            if (k == 0) {
                break;
            }
            // 如果k不为0，从该左子节点的兄弟节点（右节点）比较
            root = root.right;
        }
        return root.val;
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

}
