package topic.package37;

public class FlattenBinaryTreeToLinkedList {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    public static TreeNode convert(TreeNode head) {
        if (head == null) return null;
        return process(head).head;
    }

    public static Info process(TreeNode x) {
        if (x == null) return null;
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        if (leftInfo != null) {
            leftInfo.tail.right = x;
            x.left = leftInfo.tail;
        }
        if (rightInfo != null) {
            rightInfo.head.left = x;
            x.right = rightInfo.head;
        }
        TreeNode head = leftInfo == null ? x : leftInfo.head;
        TreeNode tail = rightInfo == null ? x : rightInfo.tail;
        return new Info(head, tail);
    }

    public static class Info {
        public TreeNode head;
        public TreeNode tail;

        public Info(TreeNode h, TreeNode t) {
            head = h;
            tail = t;
        }
    }

}
