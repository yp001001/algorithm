package everyday;


// https://leetcode.cn/problems/longest-univalue-path/
public class Test42 {

    int longestPath;

    public int longestUnivaluePath(TreeNode root) {
        longestPath = 0;
        process(root);
        return longestPath;
    }

    private int process(TreeNode root) {
        if (root == null) return 0;
        int left = process(root.left);
        int right = process(root.right);
        int left1 = 0, right1 = 0;
        if (root.left != null && root.left.val == root.val) {
            left1 = left + 1;
        }
        if (root.right != null && root.right.val == root.val) {
            right1 = right + 1;
        }
        longestPath = Math.max(longestPath, left1 + right1);
        return Math.max(left1, right1);
    }


}
