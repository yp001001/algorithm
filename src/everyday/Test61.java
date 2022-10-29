package everyday;

public class Test61 {


//["MyLinkedList","addAtHead","get","addAtHead","addAtHead","deleteAtIndex","addAtHead","get","get","get","addAtHead","deleteAtIndex"]
//        [[],[4],[1],[1],[5],[3],[7],[3],[3],[3],[1],[4]]

    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtHead(4);
        System.out.println(linkedList.get(1));
        linkedList.addAtHead(1);
        linkedList.addAtHead(5);
        linkedList.deleteAtIndex(3);
        linkedList.addAtHead(7);
        System.out.println(linkedList.get(3));
        System.out.println(linkedList.get(3));
        System.out.println(linkedList.get(3));
    }


    static class MyLinkedList {
        private Node head;
        private Node tail;
        int size;

        public MyLinkedList() {
            size = 0;
        }

        public int get(int index) {
            if (index >= size) return -1;
            Node temp = head;
            while (index > 0) {
                index--;
                temp = temp.next;
            }
            return temp.val;
        }

        public void addAtHead(int val) {
            if (head == null) {
                head = tail = new Node(val);
            } else {
                Node node = new Node(val);
                node.next = head;
                head = node;
            }
            size++;
        }

        public void addAtTail(int val) {
            if (tail == null) {
                head = tail = new Node(val);
            } else {
                Node node = new Node(val);
                tail.next = node;
                tail = node;
            }
            size++;
        }

        public void addAtIndex(int index, int val) {
            if (size < index) return;
            if (index <= 0) {
                addAtHead(val);
            } else if (index == size) {
                addAtTail(val);
            } else if (index < size) {
                Node prevNode = prevNode(index);
                Node node = new Node(val);
                node.next = prevNode.next;
                prevNode.next = node;
                size++;
            }
        }

        public void deleteAtIndex(int index) {
            if (size <= index) return;
            if (index == 0) {
                head = head.next;
            } else if (index == size - 1) {
                Node node = prevNode(index);
                tail = node;
                tail.next = null;
            } else {
                Node node = prevNode(index);
                node.next = node.next.next;
            }
            size--;
        }


        public Node prevNode(int index) {
            Node temp = head;
            while (temp.next != null && index-- > 1) {
                temp = temp.next;
            }
            return temp;
        }

        class Node {
            int val;
            Node next;

            Node(int val) {
                this.val = val;
            }

            Node() {
            }
        }
    }


}
