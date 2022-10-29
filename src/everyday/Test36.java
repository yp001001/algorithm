package everyday;


import java.util.LinkedList;
import java.util.Queue;

public class Test36 {
    public int[] shuffle(int[] nums, int n) {
        int[] ans = new int[n << 1];
        int index = 0;
        for (int i = 0; i < n; i++) {
            ans[index++] = nums[i];
            ans[index++] = nums[n + i];
        }
        return ans;
    }

    public Node connect(Node root) {
        if (root == null) return null;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int sz = queue.size();
            Node leftNode = queue.poll();
            if (leftNode.left != null) {
                queue.offer(leftNode.left);
            }
            if (leftNode.right != null) {
                queue.offer(leftNode.right);
            }
            for (int i = 0; i < sz - 1; i++) {
                Node node = queue.poll();
                leftNode.next = node;
                leftNode = node;
                if (leftNode.left != null) {
                    queue.offer(leftNode.left);
                }
                if (leftNode.right != null) {
                    queue.offer(leftNode.right);
                }
            }
        }
        return root;
    }
}

class Node {
    int val;
    Node left;
    Node right;
    Node next;
}
