package everyday;

// https://leetcode.cn/problems/trim-a-binary-search-tree/
public class Test49 {

    public TreeNode trimBST(TreeNode root, int low, int high) {
        return process(root, low, high);
    }

    private TreeNode process(TreeNode root, int low, int high) {
        if (root == null) return null;
        if (root.val < low) {
            return process(root.right, low, high);
        } else if (root.val > high) {
            return process(root.left, low, high);
        } else {
            root.left = process(root.left, low, high);
            root.right = process(root.right, low, high);
            return root;
        }
    }

}
