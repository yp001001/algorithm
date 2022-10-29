package everyday;

import java.util.LinkedList;
import java.util.Queue;

public class Test21 {

    public static void main(String[] args) {
        Node root = new Node("*");
        root.left = new Node("+");
        root.right = new Node("*");
        root.left.left = new Node("a");
        root.left.right = new Node("b");
        root.right.left = new Node("c");
        root.right.right = new Node("-");
        root.right.right.right = new Node("d");
        String process = getMidPax(root);
        System.out.println(process);
    }

    static Queue<String> queue = new LinkedList<>();

    // 叶子节点不需要添加括号
    public static String getMidPax(Node root) {
        if (root == null) return "";
        if (root.left == null && root.right == null) return root.val;
        String ret = "(";
        ret += getMidPax(root.left);
        ret += root.val;
        ret += getMidPax(root.right);
        ret += ")";
        return ret;
    }


    static class Node {
        String val;
        Node left;
        Node right;

        public Node(String val) {
            this.val = val;
        }
    }
}


