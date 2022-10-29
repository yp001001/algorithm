package test2;

import java.util.HashMap;
import java.util.Map;

public class Test21 {

    public static void main(String[] args) {
//        ListNode root = new ListNode(1);
//        root.next = new ListNode(2);
//        root.next.next = new ListNode(3);
//        root.next.next.next = new ListNode(4);
//        new Test21().reorderList(root);
//        while (root != null) {
//            System.out.println(root.val);
//            root = root.next;
//        }


        new Test21().buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});

    }


    public int sumNums(int n) {
        boolean flag = n > 0 && (n += sumNums(n - 1)) > 0;
        return n;
    }


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return dfs(root, p, q);
    }

    TreeNode dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = dfs(root.left, p, q);
        TreeNode right = dfs(root.right, p, q);
        // 左右都不为null，当前父节点就是最近公共祖先
        if (left != null && right != null) {
            return root;
        }
        if (left == p || right == p) {
            return p;
        }
        if (left == q || right == q) {
            return q;
        }
        return null;
    }

    public double myPow_1(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul_1(x, N) : 1.0 / quickMul_1(x, -N);
    }

    public double quickMul_1(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = quickMul_1(x, N / 2);
        return N % 2 == 0 ? y * y : y * y * x;
    }

    public double mypow_2(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul_2(x, N) : 1.0 / quickMul_2(x, -N);
    }

    /**
     * 使用迭代
     */
    public double quickMul_2(double x, long N) {
        double ans = 1.0;
        double x_contribute = x;
        while (N > 0) {
            if (N % 2 == 1) {
                ans *= x_contribute;
            }
            x_contribute *= x_contribute;
            N /= 2;
        }
        return ans;
    }


    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int x = cur.next.val;
                while (cur.next != null && cur.next.val == x) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }


    public void reorderList(ListNode head) {
        if (head == null) return;
        ListNode midNode = midNode(head);
        ListNode B = midNode.next;
        midNode.next = null;
        B = reverse(B);
        ListNode A = head;
        merge(A, B);
    }

    public void merge(ListNode A, ListNode B) {
        while (B != null) {
            ListNode next = B.next;
            B.next = A.next;
            A.next = B;
            B = next;
            A = A.next.next;
        }
    }

    // 反转链表
    ListNode reverse(ListNode head) {
        ListNode dummy = new ListNode();
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = dummy.next;
            dummy.next = cur;
            cur = next;
        }
        return dummy.next;
    }

    // 找到链表的中间节点，偶数为
    ListNode midNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    //====================================================

    Map<Integer, Integer> dict = new HashMap<Integer, Integer>();
    int[] preorder;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        int n = inorder.length;
        for (int i = 0; i < n; i++) {
            dict.put(inorder[i], i);
        }
        return recur(0, 0, n - 1);
    }

    private TreeNode recur(int root, int left, int right) {
        if (left > right) {
            return null;
        }
        // 获取中序遍历中root节点位置
        int rootIndex = dict.get(preorder[root]);
        // 构造根节点
        TreeNode node = new TreeNode(preorder[root]);
        node.left = recur(root + 1, left, rootIndex - 1);
        node.right = recur(root + rootIndex - left + 1, rootIndex + 1, right);
        return node;
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

    void add(int value) {
        ListNode temp = this;
        while (temp.next != null) {
            temp = temp.next;
        }
        ListNode node = new ListNode(value);
        temp.next = node;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
