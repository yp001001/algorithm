package zuo.problem.test;


import java.util.*;

public class Day01 {

    // 前序遍历的非递归方式
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        Deque<TreeNode> deque = new ArrayDeque<>();
        while (!deque.isEmpty() || root != null) {
            // 先将左边的保存
            while (root != null) {
                ans.add(root.val);
                deque.push(root);
                root = root.left;
            }
            // 此时轮到右边
            root = deque.pop();
            root = root.right;
        }
        return ans;
    }


    // 非递归中序遍历
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        Deque<TreeNode> deque = new ArrayDeque<>();
        while (!deque.isEmpty() || root != null) {
            // 先将左边的保存
            while (root != null) {
                deque.push(root);
                root = root.left;
            }
            // 此时轮到右边
            root = deque.pop();
            ans.add(root.val);
            root = root.right;
        }
        return ans;
    }


    // 非递归后序遍历
    public static List<Integer> postTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        Deque<TreeNode> stack1 = new ArrayDeque<>();
        Deque<TreeNode> stack2 = new ArrayDeque<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            stack2.push(node);
            if (node.left != null) {
                stack1.push(node.left);
            }
            if (node.right != null) {
                stack1.push(node.right);
            }
        }
        while (!stack2.isEmpty()) {
            ans.add(stack2.pop().val);
        }
        return ans;
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
