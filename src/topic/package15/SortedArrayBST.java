package topic.package15;

import everyday.TreeNode;

public class SortedArrayBST {

    public static void main(String[] args) {
        sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
    }


    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        return process(nums, 0, nums.length - 1);
    }

    private static TreeNode process(int[] nums, int left, int right) {
        if (left > right) return null;
        int index = (right + left) / 2;
        int val = nums[index];
        TreeNode root = new TreeNode(val);
        root.left = process(nums, left, index - 1);
        root.right = process(nums, index + 1, right);
        return root;
    }

}
