package topic.package16;

import everyday.TreeNode;

public class ConstructMaximumBinaryTree {

    // https://leetcode.cn/problems/maximum-binary-tree/
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        return process(nums, 0, nums.length - 1);
    }

    private TreeNode process(int[] nums, int left, int right) {
        if (left > right) return null;
        int maxIndex = searchMax(nums, left, right);
        TreeNode root = new TreeNode(nums[maxIndex]);
        root.left = process(nums, left, maxIndex - 1);
        root.right = process(nums, maxIndex + 1, right);
        return root;
    }

    private int searchMax(int[] nums, int left, int right) {
        int max = Integer.MIN_VALUE;
        int index = -1;
        for (int i = left; i <= right; i++) {
            if (max < nums[i]) {
                max = nums[i];
                index = i;
            }
        }
        return index;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(1);
        ConstructMaximumBinaryTree constructMaximumBinaryTree = new ConstructMaximumBinaryTree();
        constructMaximumBinaryTree.insertIntoMaxTree(root, 3);
    }


    // https://leetcode.cn/problems/maximum-binary-tree-ii/
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val > root.val) return new TreeNode(val, root, null);
        root.right = insertIntoMaxTree(root.right, val);
        return root;
    }
}
