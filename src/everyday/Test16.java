package everyday;


import java.util.LinkedList;
import java.util.Queue;

public class Test16 {


    public TreeNode addOneRow_dfs(TreeNode root, int val, int depth) {
        if (root == null) return null;
        if (depth == 1) {
            return new TreeNode(val, root, null);
        }
        if (depth == 2) {
            root.left = new TreeNode(val, root.left, null);
            root.right = new TreeNode(val, null, root.right);
        } else {
            root.left = addOneRow_dfs(root.left, val, depth - 1);
            root.right = addOneRow_dfs(root.right, val, depth - 1);
        }
        return root;
    }


    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            TreeNode node = new TreeNode();
            node.left = root;
            return node;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        int h = 1;
        queue.add(root);
        while (h != depth - 1) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            h++;
        }
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode left = new TreeNode(val);
            TreeNode right = new TreeNode(val);
            left.left = node.left;
            right.right = node.right;
            node.left = left;
            node.right = right;
        }
        return root;
    }

}


