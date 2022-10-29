package topic.package05;

// 返回左子树与右子树数相同的所有节点
public class Problem02 {


    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    // 时间复杂度O(N * logN)


    public static boolean same(Node h1, Node h2) {
        // 判断两树中有一个分为null
        if (h1 == null ^ h2 == null) {
            return false;
        }
        if (h1 == null && h2 == null) {
            return true;
        }
        return h1.value == h2.value && same(h1.left, h2.left) && same(h1.right, h2.right);
    }

    public static int sameNumber1(Node head) {
        if (head == null) return 0;
        return sameNumber1(head.left) + sameNumber1(head.right) + (same(head.left, head.right) ? 1 : 0);
    }


    public static Node randomBinaryTree(int restLevel, int maxValue) {
        if (restLevel == 0) {
            return null;
        }
        Node head = Math.random() < 0.2 ? null : new Node((int) (Math.random() * maxValue));
        if (head != null) {
            head.left = randomBinaryTree(restLevel - 1, maxValue);
            head.right = randomBinaryTree(restLevel - 1, maxValue);
        }
        return head;
    }
}
