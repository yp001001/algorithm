package topic.package12;

import java.util.HashMap;
import java.util.Map;

public class BuildTree {

    Map<Integer, Integer> map = new HashMap<>();
    int post_index;
    static int[] postorder;

    public TreeNode build(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        this.post_index = inorder.length - 1;
        int i = 0;
        for (int val : inorder) {
            map.put(val, i++);
        }
        return helper(0, inorder.length - 1);
    }

    private TreeNode helper(int left, int right) {
        if (left > right) return null;
        TreeNode root = new TreeNode(postorder[post_index]);
        int index = map.get(post_index);
        post_index--;
        root.left = helper(left, index - 1);
        root.right = helper(index + 1, right);
        return root;
    }


}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}
