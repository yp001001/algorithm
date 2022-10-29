package list;

public class Test01 {

    public static void main(String[] args) {
        System.out.println((2 + 2) >>> 1);
    }


    //===================================合并k个有序链表================================

    public ListNode mergeKLists(ListNode[] lists) {
        return mergeKLists(lists, 0, lists.length - 1);
    }

    private ListNode mergeKLists(ListNode[] lists, int left, int right) {
        int l = left, r = right;
        if (left == right) {
            return lists[left];
        }
        if (left < right) {
            int mid = (r + l) >>> 1;
            return mergetTwoList(mergeKLists(lists, left, mid), mergeKLists(lists, mid + 1, right));
        }
        return null;
    }

    private ListNode mergetTwoList(ListNode n1, ListNode n2) {
        ListNode res = new ListNode(-1);
        ListNode temp = res;
        ListNode t1 = n1, t2 = n2;
        while (t1 != null && t2 != null) {
            if (t1.val <= t2.val) {
                temp.next = new ListNode(t1.val);
                t1 = t1.next;
            } else {
                temp.next = new ListNode(t2.val);
                t2 = t2.next;
            }
            temp = temp.next;
        }
        temp.next = t1 == null ? t2 : t1;
        return res.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
