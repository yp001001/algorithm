package test2;

import java.util.*;

public class Test16 {
    public static void main(String[] args) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(new TreeNode(1));
        queue.offer(new TreeNode(2));
        TreeNode node = queue.poll();
        System.out.println(node.val);
    }

    boolean[][] neg;
    int count = 0;

    public int movingCount(int m, int n, int k) {
        neg = new boolean[m][n];
        dfs(m, n, k, 0, 0);
        return count;
    }

    /**
     * 用过了就不能再使用了，所以不能置为false
     */
    void dfs(int m, int n, int k, int i, int j) {
        if (i < 0 || j < 0 || i >= m || j >= n || neg[i][j]) {
            return;
        }
        int sum = sum(i) + sum(j);
        if (sum > k) return;
        int res = 1;
        neg[i][j] = true;
        count++;
        dfs(m, n, k, i - 1, j);
        dfs(m, n, k, i + 1, j);
        dfs(m, n, k, i, j - 1);
        dfs(m, n, k, i, j + 1);
    }

    int sum(int a) {
        int sum = 0;
        while (a > 0) {
            int x = a % 10;
            a /= 10;
            sum += x;
        }
        return sum;
    }


    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = m + n - 1, l2 = n - 1, l1 = m - 1;
        while (index >= 0 && l1 >= 0 && l2 >= 0) {
            if (nums1[l1] > nums2[l2]) {
                nums1[index] = nums1[l1];
                l1--;
            } else {
                nums1[index] = nums2[l2];
                l2--;
            }
            index--;
        }
        while (l2 >= 0) {
            nums1[index--] = nums1[l2--];
        }
    }


    /**
     * 下一个更大元素就是使用单调队列，该题单调队列配合dict
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Deque<Integer> deque = new ArrayDeque<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = nums2.length - 1; i >= 0; i--) {
            int num = nums2[i];
            while (!deque.isEmpty() && deque.peekFirst() <= num) {
                deque.removeFirst();
            }
            // 将数据保存到dict中
            if (deque.isEmpty()) {
                map.put(num, -1);
            } else {
                map.put(num, deque.peekFirst());
            }
            deque.addFirst(num);
        }
        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.get(nums1[i]);
        }
        return ans;
    }


    /**
     * 旋转图像（未完成）
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int[][] ans = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans[j][n - i - 1] = matrix[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = ans[i][j];
            }
        }
    }

//=============================================================================

    Node prev, head;

    public Node treeToDoublyList(Node root) {
        dfs(root);
        // 头尾节点相互指引
        prev.right = head;
        head.left = prev;
        return head;
    }

    // 中序遍历
    void dfs(Node root) {
        if (root == null) return;
        dfs(root.left);
        if (prev == null) head = root;
        else {
            prev.right = root;
            root.left = prev;
        }
        prev = root;
        dfs(root.right);
    }

    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    public int minDeletionSize(String[] strs) {
        int count = 0;
        for (int i = 0; i < strs[0].length(); i++) {
            for (int j = 0; j < strs.length; j++) {
                if (strs[j].charAt(i) < strs[j].charAt(j - 1)) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }
}
