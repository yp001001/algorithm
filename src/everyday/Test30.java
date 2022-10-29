package everyday;

import java.util.ArrayList;
import java.util.List;

public class Test30 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        Test30 test30 = new Test30();
        System.out.println(test30.maxHeight(root));
        System.out.println(test30.calDepth(root));
    }

    public int calDepth(TreeNode root) {
        int h = 0;
        if (root.left != null) {
            h = Math.max(h, calDepth(root.left) + 1);
        }
        if (root.right != null) {
            h = Math.max(h, calDepth(root.right) + 1);
        }
        return h;
    }


    public List<List<String>> printTree(TreeNode root) {
        if (root == null) return null;
        int height = maxHeight(root) - 1;
        int m = height + 1;
        int n = (1 << (height + 1)) - 1;
        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<String> list = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                list.add(" ");
            }
            res.add(list);
        }
        dfs(res, root, 0, (n - 1) / 2, height);
        return res;
    }

    private void dfs(List<List<String>> res, TreeNode root, int row, int col, int height) {
        if (root == null) return;
        dfs(res, root.left, row + 1, col - (1 << (height - row - 1)), height);
        dfs(res, root.right, row + 1, col + (1 << height - row + 1), height);
        res.get(row).set(col, String.valueOf(root.val));
    }

    // 得到树的最大高度
    public static int maxHeight(TreeNode root) {
        if (root == null) return 0;
        int left, right, max;
        left = maxHeight(root.left);
        right = maxHeight(root.right);
        max = Math.max(left, right) + 1;
        return max;
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
