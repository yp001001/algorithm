package everyday;

// https://leetcode.cn/problems/binary-tree-maximum-path-sum/submissions/
public class Test38 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(maxPathSum(root));
    }

    static int max = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        maxGain(root);
        return max;
    }


    public static int maxGain(TreeNode root) {
        if (root == null) return 0;
        // 递归计算左右子节点的总和
        int leftNum = Math.max(maxGain(root.left), 0);
        int rightNum = Math.max(maxGain(root.right), 0);

        // 获取最大值
        max = Math.max(root.val + leftNum + rightNum, max);
        return root.val + Math.max(leftNum, rightNum);
    }

}
