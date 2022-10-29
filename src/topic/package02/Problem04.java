package topic.package02;

import java.util.HashMap;

public class Problem04 {

    public static class Node {
        public String info;
        public Node next;

        public Node(String str) {
            info = str;
        }
    }


    public static class MessageBox {
        private HashMap<Integer, Node> headMap;
        private HashMap<Integer, Node> tailMap;
        private int waitPoint;

        public MessageBox() {
            headMap = new HashMap<>();
            tailMap = new HashMap<>();
            waitPoint = 1;
        }

        public void receive(int num, String info) {
            if (num < 1) {
                return;
            }
            Node cur = new Node(info);
            headMap.put(num, cur);
            tailMap.put(num, cur);
            if (tailMap.containsKey(num + 1)) {
                tailMap.get(num + 1).next = cur;
                headMap.remove(num);
                tailMap.remove(num + 1);
            }
            if (headMap.containsKey(num - 1)) {
                cur.next = headMap.get(num - 1);
                tailMap.remove(num);
                headMap.remove(num - 1);
            }
            if (waitPoint == num) {
                print();
            }
        }

        private void print() {
            Node node = headMap.get(waitPoint);
            headMap.remove(waitPoint);
            while (node != null) {
                System.out.print(node.info + " ");
                node = node.next;
                waitPoint++;
            }
            tailMap.remove(waitPoint - 1);
            System.out.println();
        }
    }
}
