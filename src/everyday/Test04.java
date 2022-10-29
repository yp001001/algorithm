package everyday;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Test04 {

    public static void main(String[] args) {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(1);
        deque.offer(2);
        deque.offer(3);
        System.out.println(deque.peek());
    }


    static class CBTInserter {

        TreeNode root;
        Deque<TreeNode> stack;

        public CBTInserter(TreeNode root) {
            this.root = root;
            stack = new ArrayDeque<>();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                    // 只要父节点的左子节点或右子节点有一个为null，就将其加入栈中
                    if (!(node.left != null && node.right != null)) {
                        stack.offer(node);
                    }
                }
            }
        }


        public int insert(int val) {
            TreeNode node = stack.peek();
            TreeNode child = new TreeNode(val);
            if (node.left == null) {
                node.left = child;
            } else {
                node.right = child;
                stack.pop();
            }
            stack.offer(child);
            return node.val;
        }

        public TreeNode get_root() {
            return this.root;
        }
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
