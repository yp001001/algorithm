package topic.package05;

import java.util.*;

public class Problem01 {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode() {

        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    // 利用BST前序遍历创建二叉树
    public static TreeNode bstFromPreorder(int[] pre) {
        if (pre == null || pre.length == 0) return null;
        int[] nearBig = new int[pre.length];
        Arrays.fill(nearBig, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < pre.length; i++) {
            while (!stack.isEmpty() && pre[stack.peek()] < pre[i]) {
                nearBig[stack.pop()] = i;
            }
            stack.push(i);
        }
        return buildTree(pre, 0, pre.length - 1, nearBig);
    }

    private static TreeNode buildTree(int[] pre, int L, int R, int[] nearBig) {
        if (L > R) return null;
        // 找到右边离该位置最近的大于该数的位置
        int firstBig = nearBig[L] == -1 ? R + 1 : nearBig[L];
        TreeNode root = new TreeNode(pre[L]);
        root.left = buildTree(pre, L + 1, firstBig - 1, nearBig);
        root.right = buildTree(pre, firstBig, R, nearBig);
        return root;
    }

    public static void main(String[] args) {
        bstFromPreorder(new int[]{8, 5, 1});
    }
}
