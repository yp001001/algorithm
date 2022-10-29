package everyday;


import java.util.*;

public class Test67 {

    public static void main(String[] args) {
        Test67 test67 = new Test67();
        test67.minDistance("sea", "eat");
    }

    // https://leetcode.cn/problems/minimum-add-to-make-parentheses-valid/
    public int minAddToMakeValid(String s) {
        char[] str = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        int need = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == ')') {
                if (stack.isEmpty()) need++;
                else stack.pop();
            } else {
                stack.push('(');
            }
        }
        return need + stack.size();
    }


    // https://leetcode.cn/problems/swap-adjacent-in-lr-string/
    public boolean canTransform(String start, String end) {
        int i = 0, j = 0;
        int n = start.length();
        while (i < n && j < n) {
            while (i < n && start.charAt(i) == 'X') i++;
            while (j < n && end.charAt(j) == 'X') j++;
            if (i < n && j < n) {
                if (start.charAt(i) != end.charAt(j)) {
                    return false;
                }
                char c = start.charAt(i);
                if ((c == 'L' && i < j) || c == 'R' && i > j) {
                    return false;
                }
                i++;
                j++;
            }
        }
        while (i < n) {
            if (start.charAt(i) != 'X') {
                return false;
            }
            i++;
        }
        while (j < n) {
            if (end.charAt(j) != 'X') {
                return false;
            }
            j++;
        }
        return true;
    }


    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode temp = head;
        Stack<ListNode> stack = new Stack<>();
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        while (n-- > 0) {
            stack.pop();
        }
        if (stack.isEmpty()) return head.next;
        ListNode node = stack.pop();
        node.next = node.next.next;
        return head;
    }


    // https://leetcode.cn/problems/subdomain-visit-count/
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();
        for (String cpdomain : cpdomains) {
            String[] strs = cpdomain.split("\\s+");
            int count = Integer.parseInt(strs[0]);
            String[] split = strs[1].split("\\.");
            StringBuilder sb = new StringBuilder();
            for (int i = split.length - 1; i >= 0; i--) {
                if (sb.length() == 0) {
                    sb.append(split[i]);
                } else {
                    sb.insert(0, split[i] + ".");
                }
                int c = map.getOrDefault(sb.toString(), 0);
                map.put(sb.toString(), c + count);
            }
        }
        List<String> res = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            res.add(entry.getKey() + " " + entry.getValue());
        }
        return res;
    }


    // https://leetcode.cn/problems/wiggle-subsequence/
    public int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        if (n < 2) return n;
        // up[i] 表示以前 ii 个元素中的某一个为结尾的最长的「上升摆动序列」的长度。
        int[] up = new int[n];
        // down[i] 表示以前 ii 个元素中的某一个为结尾的最长的「下降摆动序列」的长度。
        int[] down = new int[n];
        up[0] = down[0] = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                up[i] = up[i - 1];
                down[i] = Math.max(down[i - 1], up[i - 1] + 1);
            } else if (nums[i] < nums[i - 1]) {
                up[i] = Math.max(up[i - 1], down[i - 1] + 1);
                down[i] = down[i - 1];
            } else {
                up[i] = up[i - 1];
                down[i] = down[i - 1];
            }
        }
        return Math.max(up[n - 1], down[n - 1]);
    }


    // https://leetcode.cn/problems/delete-operation-for-two-strings/
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) dp[i][0] = i;
        for (int i = 0; i <= m; i++) dp[0][i] = i;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + 1;
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + 2);
                }
            }
        }
        return dp[n][m];
    }


    // 盛水最多的容器
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;
        while (left < right) {
            int high = Math.min(height[left], height[right]);
            int width = right - left;
            maxArea = Math.max(maxArea, high * width);
            if (height[left] < height[right]) left++;
            else right--;
        }
        return maxArea;
    }
}
