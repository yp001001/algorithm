package test2;

import java.util.List;

public class Test26 {

    public static void main(String[] args) {
        Test26 test26 = new Test26();
        ListNode r1 = new ListNode(1);
        r1.next = new ListNode(3);
        r1.next.next = new ListNode(5);
        ListNode r2 = new ListNode(2);
        r2.next = new ListNode(4);
        ListNode root = test26.mergeTwoList(r1, r2);
    }


    /**
     * 单词拆分
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak_dp(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }


    /**
     * 顺时针打印矩阵
     *
     * @param matrix
     * @return
     */
    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return new int[0];
        }
        int[] res = new int[matrix.length * matrix[0].length];
        int u = 0, d = matrix.length - 1, l = 0, r = matrix[0].length - 1;
        int index = 0;
        while (true) {
            for (int i = l; i <= r; i++) {
                res[index++] = matrix[u][i];
            }
            if (++u > d) {
                break;
            }
            for (int i = u; i <= d; i++) {
                res[index++] = matrix[i][r];
            }
            if (--r < l) {
                break;
            }
            for (int i = r; i >= l; i--) {
                res[index++] = matrix[u][i];
            }
            if (--d < u) {
                break;
            }
            for (int i = d; i >= u; i--) {
                res[index] = matrix[i][l];
            }
            if (++l > r) {
                break;
            }
        }
        return res;
    }

    public int minEatingSpeed(int[] piles, int h) {
        int maxPile = 0, minPile = Integer.MAX_VALUE;
        for (int pile : piles) {
            maxPile = Math.max(maxPile, pile);
            minPile = Math.min(minPile, pile);
        }
        // 查找在minPile - maxPile 之间能够吃完香蕉的最小值
        int l = 1, r = maxPile;
        int ans = 0;
        while (l <= r) {
            int mid = (r - l) / 2 + l;
            if (isValid(piles, h, mid)) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    // 判断狒狒在每小时吃k个香蕉的情况下h小时内是否能够吃完，true表示能够吃完
    public boolean isValid(int[] piles, int h, int k) {
        int hour = 0;
        for (int i = 0; i < piles.length; i++) {
            hour += piles[i] / k;
            hour += piles[i] % k != 0 ? 1 : 0;
        }
        return hour <= h;
    }

    // 在第d天送达包裹的能力（求出最低运载能力，联想二分）
    public int shipWithinDays(int[] weights, int days) {
        int left = 0, right = 0;
        for (int weight : weights) {
            left = Math.max(left, weight);
            right += weight;
        }
        int ans = weights.length;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            // 需要的最小天数大于目标天数
            if (f(weights, mid) > days) {
                left = mid + 1;
            } else {
                right = mid - 1;
                ans = mid;
            }
        }
        return ans;
    }


    // 定义：当运载能⼒为 x 时，需要 f(x) 天运完所有货物
    // f(x) 随着 x 的增加单调递减
    int f(int[] weights, int x) {
        int day = 0;
        for (int i = 0; i < weights.length; ) {
            int cap = x;
            while (i < weights.length) {
                if (cap < weights[i]) break;
                else cap -= weights[i];
                i++;
            }
            day++;
        }
        return day;
    }


    // 合并k个升序链表
    public ListNode mergeKLists(ListNode[] lists) {
        return mergeLists(lists, 0, lists.length - 1);
    }

    ListNode mergeLists(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left];
        if (left > right) return null;
        int mid = (right - left) / 2 + left;
        return mergeTwoList(mergeLists(lists, left, mid), mergeLists(lists, mid + 1, right));
    }

    // 合并两个有序链表
    ListNode mergeTwoList(ListNode node1, ListNode node2) {
        if (node1 == null || node2 == null) {
            return node1 == null ? node2 : node1;
        }
        ListNode result = new ListNode(-1);
        ListNode temp = result;
        ListNode n1 = node1, n2 = node2;
        while (n1 != null && n2 != null) {
            if (n1.val < n2.val) {
                temp.next = n1;
                n1 = n1.next;
            } else {
                temp.next = n2;
                n2 = n2.next;
            }
            temp = temp.next;
        }
        if (n1 != null) temp.next = n1;
        if (n2 != null) temp.next = n2;
        return result.next;
    }
}
