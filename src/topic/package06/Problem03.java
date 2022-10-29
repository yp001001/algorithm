package topic.package06;

import java.util.HashMap;
import java.util.Map;

/**
 * 先序中序遍历得到后序遍历
 **/
public class Problem03 {

    Map<Integer, Integer> dict = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            dict.put(inorder[i], i);
        }
        return process(preorder, 0, 0, preorder.length - 1);
    }

    private TreeNode process(int[] preorder, int root, int left, int right) {
        if (left > right) return null;
        // 找到root节点
        Integer rootIndex = dict.get(preorder[root]);
        TreeNode node = new TreeNode(preorder[root]);
        node.left = process(preorder, root + 1, left, rootIndex - 1);
        // 如何确定先序遍历的根节点，先确定左子树长度
        node.right = process(preorder, root + rootIndex - left, rootIndex + 1, right);
        return node;
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
 
