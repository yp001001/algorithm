package zuo.dp.problem;

import java.util.*;

public class Test07 {

    //=====================================统计子岛屿================================

    // 与前面题目类似，判断哪些岛屿不是子岛，修改为0
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int n = grid1.length, m = grid1[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid1[i][j] == 0 && grid2[i][j] == 1) {
                    // 这个岛屿肯定不是子岛
                    backtrack(grid2, i, j);
                }
            }
        }
        // 现在剩下的都是子岛，计算岛屿数量
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid2[i][j] == 1) {
                    res++;
                    backtrack(grid2, i, j);
                }
            }
        }
        return res;
    }

    private void backtrack(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length
                || j < 0 || j >= grid[0].length
                || grid[i][j] == 0) {
            return;
        }
        grid[i][j] = 0;
        backtrack(grid, i + 1, j);
        backtrack(grid, i - 1, j);
        backtrack(grid, i, j + 1);
        backtrack(grid, i, j - 1);
    }


    //=====================================二叉树的锯齿形层序遍历================================

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean flag = true;
        while (!q.isEmpty()) {
            int sz = q.size();
            LinkedList<Integer> level = new LinkedList<>();
            for (int i = 0; i < sz; i++) {
                TreeNode cur = q.poll();
                if (flag) level.addLast(cur.val);
                else level.addFirst(cur.val);
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
            flag = !flag;
            res.add(level);
        }
        return res;
    }

    //=====================================二叉树的层序遍历II================================

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int sz = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < sz; i++) {
                TreeNode n = queue.poll();
                list.add(n.val);
                if (n.left != null) queue.add(n.left);
                if (n.right != null) queue.add(n.right);
            }
            res.addFirst(list);
        }
        return res;
    }


    //=====================================二叉树的最小深度================================

    public int minDepth_dfs(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        int minDepth = Integer.MAX_VALUE;
        if (root.left != null) {
            minDepth = Math.min(minDepth, minDepth_dfs(root.left));
        }
        if (root.right != null) {
            minDepth = Math.min(minDepth, minDepth_dfs(root.right));
        }
        return minDepth + 1;
    }

    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int minDepth = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                TreeNode n = queue.poll();
                if (n.left == null && n.right == null) return minDepth;
                if (n.left != null) queue.offer(n.left);
                if (n.right != null) queue.offer(n.right);
            }
            minDepth++;
        }
        return minDepth;
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
