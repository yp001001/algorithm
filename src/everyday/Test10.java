package everyday;

import java.util.LinkedList;
import java.util.Queue;

public class Test10 {

    public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int ans = 0;
        int level = 1;
        int val = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            int v = 0;
            for (int i = 0; i < sz; i++) {
                TreeNode node = queue.poll();
                v += node.val;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            if (v > val) {
                val = v;
                ans = level;
            }
            level++;
        }
        return ans;
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
