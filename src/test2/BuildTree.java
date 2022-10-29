package test2;

import java.util.HashMap;
import java.util.Map;

public class BuildTree {

    Map<Integer, Integer> dict = new HashMap<Integer, Integer>();
    int[] preorder;

    /**
     * 前中序遍历构建二叉树
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree_pre_mid(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        for (int i = 0; i < inorder.length; i++) {
            dict.put(inorder[i], i);
        }
        return recur(0, 0, inorder.length - 1);
    }

    private TreeNode recur(int root, int left, int right) {
        if (left > right) return null;
        TreeNode r = new TreeNode(preorder[root]);
        // 获取根节点的位置
        int rootIndex = dict.get(preorder[root]);
        r.left = recur(root + 1, left, rootIndex - 1);
        r.right = recur(root + rootIndex - left + 1, rootIndex + 1, right);
        return r;
    }


    /**
     * 中后序遍历构建二叉树
     *
     * @param inorder
     * @param postorder
     * @return
     */
    int post_idx;
    int[] postorder;
    Map<Integer, Integer> idx_map = new HashMap<Integer, Integer>();

    public TreeNode helper(int in_left, int in_right) {
        // 如果这里没有节点构造二叉树了，就结束
        if (in_left > in_right) {
            return null;
        }

        // 选择 post_idx 位置的元素作为当前子树根节点
        int root_val = postorder[post_idx];
        TreeNode root = new TreeNode(root_val);

        // 根据 root 所在位置分成左右两棵子树
        int index = idx_map.get(root_val);

        // 下标减一
        post_idx--;
        // 构造右子树
        root.right = helper(index + 1, in_right);
        // 构造左子树
        root.left = helper(in_left, index - 1);
        return root;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        // 从后序遍历的最后一个元素开始
        post_idx = postorder.length - 1;

        // 建立（元素，下标）键值对的哈希表
        int idx = 0;
        for (Integer val : inorder) {
            idx_map.put(val, idx++);
        }

        return helper(0, inorder.length - 1);
    }
}
