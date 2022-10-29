package test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Test23 {


    /**
     * 二叉树每层的最大值
     *
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                max = Math.max(max, node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            res.add(max);
        }
        return res;
    }

    /**
     * 二叉树最底层最左边的值
     *
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int maxLengthLeft = root.val;
        boolean flag;
        while (!queue.isEmpty()) {
            flag = true;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    if (flag) {
                        flag = false;
                        maxLengthLeft = node.left.val;
                    }
                    queue.add(node.left);
                }

                if (node.right != null) {
                    if (flag) {
                        flag = false;
                        maxLengthLeft = node.right.val;
                    }
                    queue.add(node.right);
                }
            }
        }
        return maxLengthLeft;
    }

    /**
     * 使用递归解决
     *
     * @param root
     * @return
     */
    int max = -1;   //  记录层级
    int res = 0;    //  记录数

    public int findBottomLeftValue_2(TreeNode root) {
        getMaxLengthLeft(root, 0);
        return res;
    }

    private void getMaxLengthLeft(TreeNode root, int level) {
        if (root == null) return;
        getMaxLengthLeft(root.left, level + 1);
        getMaxLengthLeft(root.right, level + 1);
        if (max < level) {
            res = root.val;
            max = level;
        }
    }


    /**
     * 从根节点到叶节点的路径数字之和
     *
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int num) {
        if (root == null) return 0;
        int sum = num * 10 + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        } else {
            return dfs(root.left, sum) + dfs(root.right, sum);
        }
    }

    public static void main(String[] args) {
        Test23 test23 = new Test23();
        int res = test23.countNumbersWithUniqueDigits(2);
        System.out.println(res);
    }

    /**
     * 统计各位数字都不同的数字个数
     * （排列组合）
     *
     * @param n
     * @return
     */
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) return 1;
        if (n == 1) return 10;
        int res = 10, cur = 9;
        for (int i = 0; i < n - 1; i++) {
            cur *= (9 - i);
            res += cur;
        }
        return res;
    }

    /**
     * 判断数字是否有重复
     *
     * @param n
     * @return
     */
    private boolean isValid(int n) {
        int[] ans = new int[10];
        while (n > 0) {
            if (ans[n % 10]++ > 0) return false;
            n = n / 10;
        }
        return true;
    }


    static class TreeNode {
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




