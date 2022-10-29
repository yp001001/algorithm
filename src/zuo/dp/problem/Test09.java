package zuo.dp.problem;

import java.util.*;

public class Test09 {

    public static void main(String[] args) {
        Test09 test09 = new Test09();
        Node root = new Node(1);
        root.next = new Node(3);
        root.next.next = new Node(3);
        root.next.next.next = root;
        test09.insert(root, 4);
    }


    //=========================================最大子序列和======================================

    public int maxSubArray(int[] nums) {
        int sum = 0, maxVal = Integer.MIN_VALUE;
        for (int num : nums) {
            sum = sum < 0 ? num : sum + num;
            maxVal = Math.max(maxVal, sum);
        }
        return maxVal;
    }


    //=========================================打家劫舍II======================================

    // 从第一个开始，从第二个开始
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        if (nums.length == 3) return Math.max(nums[0], Math.max(nums[1], nums[2]));
        int m1 = steal(nums, 0, nums.length - 2);
        int m2 = steal(nums, 1, nums.length - 1);
        return Math.max(m1, m2);
    }

    public int steal(int[] nums, int left, int right) {
        int[] dp = new int[right - left + 1];
        dp[0] = nums[left];
        dp[1] = Math.max(nums[left], nums[left + 1]);
        int index = 2;
        for (int i = left + 2; i <= right; i++) {
            dp[index++] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[right];
    }


    //=========================================下一个更大元素II======================================

    public int[] nextGreaterElements(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>();
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        for (int i = 2 * n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i % n]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                result[i % n] = stack.peek();
            }
            stack.push(nums[i % n]);
        }
        return result;
    }


    //=========================================最长递增子序列======================================

    // 暴力解决不了
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        // 从0到i之间的最长序列
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        int max = 0;
        for (int i : dp) {
            max = Math.max(max, i);
        }
        return max;
    }


    //=========================================打家劫舍III======================================

    Map<TreeNode, Integer> memo = new HashMap<>();

    public int rob(TreeNode root) {
        if (root == null) return 0;
        // 利用备忘录消除重叠子问题
        if (memo.containsKey(root)) return memo.get(root);
        // 偷窃该点
        int do_it = root.val
                + (root.left == null ? 0 : rob(root.left.left) + rob(root.left.right))
                + (root.right == null ? 0 : rob(root.right.left) + rob(root.right.right));
        // 不偷窃该点
        int not_do = rob(root.left) + rob(root.right);
        int res = Math.max(do_it, not_do);
        memo.put(root, res);
        return res;
    }

    //=========================================俄罗斯套娃信封问题======================================

    public int maxEnvelopes(int[][] envelopes) {
        // 使用宽度升序，高度降序，然后寻找高度降序的最长递增子序列
        int n = envelopes.length;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] == b[0] ? b[1] - a[1] : a[0] - b[0];
            }
        });
        // 获取高度
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i = 0; i < envelopes.length; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopes[i][1] > envelopes[j][1]) dp[i] = Math.max(dp[i], dp[j] + 1);
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }

    /* 返回 nums 中 LIS 的⻓度 */
    public int lengthOfHeight(int[] nums) {
        int piles = 0, n = nums.length;
        int[] top = new int[n];
        for (int i = 0; i < n; i++) {
            // 要处理的扑克牌
            int poker = nums[i];
            int left = 0, right = piles;
            // ⼆分查找插⼊位置
            while (left < right) {
                int mid = (left + right) / 2;
                if (top[mid] >= poker)
                    right = mid;
                else
                    left = mid + 1;
            }
            if (left == piles) piles++;
            // 把这张牌放到牌堆顶
            top[left] = poker;
        }
        // 牌堆数就是 LIS ⻓度
        return piles;
    }


    //==================================================O(1)空间复杂度反转字符串============================

    // A ^ B ^ B = A ^ 0 = A;
    public void reverseString(char[] s) {
        int l = 0, r = s.length - 1;
        while (l < r) {
            s[l] ^= s[r];
            s[r] ^= s[l];
            s[l] ^= s[r];
            l++;
            r--;
        }
        System.out.println(new String(s));
    }


    //==================================================零钱兑换============================

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i >= coin) dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] >= amount ? -1 : dp[amount];
    }


    //==================================================正则表达式匹配============================

    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        // 表示s的前n个字符是否匹配p的前m个字符
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;

        for (int i = 0; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (p.charAt(j - 1) == '*') {
                    // 1. 不匹配该字符
                    dp[i][j] = dp[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        // 2. 匹配该字符，将匹配的字符扔掉，然后继续进行匹配
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[n][m];
    }

    boolean matches(String s, String p, int i, int j) {
        if (i == 0) return false;
        if (p.charAt(j - 1) == '.') return true;
        return s.charAt(i - 1) == p.charAt(j - 1);
    }


    //==================================================石子游戏============================

    public boolean stoneGame(int[] piles) {
        int length = piles.length;
        // 表示在[i,j]中先手玩家与后手玩家石头差值
        // dp[i][j] = plies[i] + (-dp[i + 1][j])  拿开头
        // dp[i][j] = piles[j] + (-dp[i][j - 1);
        if (length == 0) return false;
        int[][] dp = new int[length][length];
        for (int i = 0; i < length; i++) {
            dp[i][i] = piles[i];
        }
        for (int i = length - 1; i >= 0; i++) {
            for (int j = i + 1; j < length; j++) {
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[i] - dp[i][j - 1]);
            }
        }
        return dp[0][length - 1] >= 0;
    }


    //==================================================排序的循环链表============================

    public Node insert(Node head, int insertVal) {
        Node insertNode = new Node(insertVal);
        if (head == null) {
            insertNode.next = insertNode;
            return insertNode;
        }
        // 这时候分情况写代码
        // 1. 找到最大的节点
        Node max = head;
        Node temp = head.next;
        while (temp != null && temp != max) {
            // 必须是判断 max.val > temp.val ！！！
            max = max.val > temp.val ? max : temp;
            if (max.next == head) break;
            temp = temp.next;
        }
        if (insertVal >= max.val || insertVal < max.next.val) {
            insertNode.next = max.next;
            max.next = insertNode;
            return head;
        }
        temp = head;
        while (true) {
            if (temp.val <= insertVal && temp.next.val >= insertVal) {
                insertNode.next = temp.next;
                temp.next = insertNode;
                return head;
            }
            temp = temp.next;
        }
    }

}


class Node {
    public int val;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
}
