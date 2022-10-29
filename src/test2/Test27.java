package test2;

import java.util.*;

public class Test27 {

    public static void main(String[] args) {
        Test27 test27 = new Test27();
        List<String> list = test27.letterCombinations("23");
        System.out.println(list);
    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int index = 0;
        for (int i = 0; i < pushed.length; i++) {
            stack.push(pushed[i]);
            while (!stack.isEmpty() && stack.peek() == popped[index]) {
                index++;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    public int myAtoi(String s) {
        s = s.trim();
        if (s.length() == 0) return 0;
        char c = s.charAt(0);
        if (!Character.isDigit(c) && c != '+' && c != '-') {
            return 0;
        }
        // 判断正负
        boolean neg = c == '-';
        long ans = Character.isDigit(c) ? Long.parseLong(c + "") : 0;
        for (int i = 1; i < s.length(); i++) {
            c = s.charAt(i);
            if (!Character.isDigit(c)) break;
            ans = ans * 10 + c - '0';
            if (!neg && ans > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (neg && -ans < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
        }
        return neg ? (int) -ans : (int) ans;
    }


    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        // 走到left - 1步
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        // 从pre再走right-left+1步
        ListNode rightNode = pre;
        for (int i = 0; i < right - left + 1; i++) {
            rightNode = rightNode.next;
        }
        // 切断子链表
        ListNode leftNode = pre.next;
        ListNode curr = rightNode.next;
        pre.next = null;
        rightNode.next = null;
        reverse(leftNode);
        pre.next = rightNode;
        leftNode.next = curr;
        return dummyNode.next;
    }

    void reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
    }


    // 无重复字符的最长子串
    public int lengthOfLongestSubstring(String s) {
        int l = 0, r = 0;
        int maxLength = 0, n = s.length();
        Set<Character> set = new HashSet<>();
        while (r < n) {
            char c = s.charAt(r);
            while (set.contains(c)) {
                set.remove(s.charAt(l++));
            }
            maxLength = Math.max(r - l + 1, maxLength);
            set.add(c);
            r++;
        }
        return maxLength;
    }


    // 最长回文子串
    public String longestPalindrome(String s) {
        int n = s.length();
        // dp[i][j] 表示在i-j之间是否是回文子串
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        int start = 0, maxLength = 1;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                start = i;
                maxLength = 2;
            }
        }
        // 前面的需要用到后面的，所以从后开始遍历
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                dp[i][j] = dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
                if (dp[i][j] && j - i + 1 > maxLength) {
                    maxLength = j - i + 1;
                    start = i;
                }
            }
        }
        return s.substring(start, start + maxLength);
    }


    // 最长回文子串
    public String longestPalindrome_2(String s) {
        char[] array = s.toCharArray();
        int n = array.length;
        int maxLength = 0;
        int start = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if ((j - i + 1) > maxLength && isValid(array, i, j)) {
                    maxLength = j - i + 1;
                    start = i;
                }
            }
        }
        return s.substring(start, start + maxLength);
    }

    boolean isValid(char[] array, int left, int right) {
        for (int i = left, j = right; i < j; i++, j--) {
            if (array[i] != array[j]) return false;
        }
        return true;
    }


    // 电话号码的字母组合
    List<String> res = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (digits == null || "".equals(digits)) return new ArrayList<>();
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        StringBuilder sb = new StringBuilder();
        dfs(digits, map, sb, 0);
        return res;
    }

    private void dfs(String digits, Map<Character, String> map, StringBuilder sb, int path) {
        if (path > digits.length()) return;
        if (sb.length() == digits.length()) {
            res.add(sb.toString());
            return;
        }
        String s = map.get(digits.charAt(path));
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            dfs(digits, map, sb, path + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }


    // 删除链表的倒数第N个节点
    public ListNode removeNthFromEnd_stack(ListNode head, int n) {
        Stack<ListNode> stack = new Stack<>();
        ListNode dummy = new ListNode(-1, head);
        ListNode temp = dummy;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        ListNode prev = null;
        while (n > 1) {
            prev = stack.pop();
            n--;
        }
        stack.pop();
        ListNode pop = stack.pop();
        pop.next = prev;
        return dummy.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

    // 括号生成
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(res, sb, n, n);
        return res;
    }

    private void dfs(List<String> res, StringBuilder sb, int left, int right) {
        if (left == 0 && right == 0) {
            res.add(sb.toString());
            return;
        }
        // 左括号剩余数等于右括号，只能添加右括号
        if (left == right) {
            sb.append("(");
            dfs(res, sb, left - 1, right);
            sb.deleteCharAt(sb.length() - 1);
        } else {
            if (left > 0) {
                sb.append("(");
                dfs(res, sb, left - 1, right);
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(")");
            dfs(res, sb, left, right - 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }


    public boolean isUnivalTree(TreeNode root) {
        int val = root.val;
        return dfs(root, val);
    }

    private boolean dfs(TreeNode root, int val) {
        if (root == null) return true;
        if (root.val != val || !dfs(root.left, val)) {
            return false;
        }
        if (root.val != val || !dfs(root.right, val)) {
            return false;
        }
        return true;
    }


}