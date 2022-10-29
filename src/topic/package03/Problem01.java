package topic.package03;

import java.util.*;

public class Problem01 {

    public static void createParentMap(Node cur, Map<Node, Node> dict) {
        if (cur == null) return;
        if (cur.left != null) {
            dict.put(cur.left, cur);
        }
        createParentMap(cur.left, dict);
        if (cur.right != null) {
            dict.put(cur.right, cur);
//            createParentMap(cur.right, dict);
        }
    }


    // K > 0
    public static List<Node> distanceKNodes(Node root, Node target, int K) {
        Map<Node, Node> parent = new HashMap<>();
        parent.put(root, null);
        Set<Node> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        List<Node> ans = new ArrayList<>();
        queue.offer(target);
        visited.add(target);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (level == K) {
                ans.addAll(queue);
                break;
            }
            while (size > 0) {
                Node node = queue.poll();
                if (node != null && !visited.contains(node.left)) {
                    queue.offer(node.left);
                    visited.add(node.left);
                }
                if (node != null && !visited.contains(node.right)) {
                    queue.offer(node.right);
                    visited.add(node.right);
                }
                if (parent.get(node) != null || !visited.contains(parent.get(node))) {
                    queue.add(parent.get(node));
                    visited.add(parent.get(node));
                }
                size--;
            }
            level++;
            if (level > K) break;
        }
        return ans;
    }


    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }


    public static void main(String[] args) {
        Node n0 = new Node(0);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);

        n3.left = n5;
        n3.right = n1;
        n5.left = n6;
        n5.right = n2;
        n1.left = n0;
        n1.right = n8;
        n2.left = n7;
        n2.right = n4;

        Node root = n3;
        Node target = n5;
        int K = 2;

        List<Node> ans = distanceKNodes(root, target, K);
        for (Node o1 : ans) {
            System.out.println(o1.value);
        }
    }
}
