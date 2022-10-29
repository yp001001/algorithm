package test2;

import java.util.*;

public class Test11 {


    public static void main(String[] args) {
        int i = new Test11().monotoneIncreasingDigits(19272);
        System.out.println(i);
    }

    public static void main2(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        new Test11().countNodes(root);
    }

    /**
     * 完全二叉树的树高可以通过遍历左子树来得到
     *
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int level = 0;
        TreeNode node = root;
        while (node.left != null) {
            level++;
            node = node.left;
        }
        // low表示叶子节点最小值，high表示叶子节点最大值
        int low = 1 << level, high = (1 << (level + 1)) - 1;
        while (low < high) {
            // 向上取整，防止死循环
            int mid = (high - low + 1) / 2 + low;
            if (exists(root, level, mid)) {
                low = mid;
            } else {
                // 这里-1，就需要向上取整
                high = mid - 1;
            }
        }
        return low;
    }

    /**
     * 何判断第 k 个节点是否存在呢？如果第 kk 个节点位于第 hh 层，则 kk 的二进制表示包含 h+1h+1 位，
     * 其中最高位是 11，其余各位从高到低表示从根节点到第 kk 个节点的路径，00 表示移动到左子节点，11 表示移动到右子节点
     * 通过位运算得到第 kk 个节点对应的路径，判断该路径对应的节点是否存在，即可判断第 kk 个节点是否存在。
     *
     * @param root
     * @param level
     * @param k
     * @return
     */
    public boolean exists(TreeNode root, int level, int k) {
        int bits = 1 << (level - 1);
        TreeNode node = root;
        while (node != null && bits > 0) {
            System.out.println(Integer.toBinaryString(bits));
            if ((bits & k) == 0) {
                node = node.left;
            } else {
                node = node.right;
            }
            bits >>= 1;
        }
        return node != null;
    }

    /**
     * 跳跃游戏II
     * 每次找能跳到的最远距离
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int pos = nums.length - 1;
        int end = 0;    // 作为前一个跳动的节点
        int step = 0;
        int maxPos = 0;
        for (int i = 1; i < pos; i++) {
            maxPos = Math.max(maxPos, i + nums[i]);
            if (i == end) {
                end = maxPos;
                step++;
            }
        }
        return step;
    }

    /**
     * 把数字翻译成字符串
     *
     * @param num
     * @return
     */
    public int translateNum(int num) {
        String str = String.valueOf(num);
        int n = str.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int x = (str.charAt(i - 2) - '0') * 10 + (str.charAt(i - 1) - '0');
            if (x >= 10 && x <= 25) {
                dp[i] = dp[i - 1] + dp[i - 2];
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[n];
    }


    /**
     * 最长不含重复字符的子字符串
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;
        int l = 0, r = 0, n = s.length(), maxLength = 0;
        Set<Character> set = new HashSet<>();
        while (r < n) {
            char c = s.charAt(r);
            if (set.contains(c)) {
                while (set.contains(c)) {
                    set.remove(s.charAt(l++));
                }
            }
            set.add(c);
            maxLength = Math.max(maxLength, r - l + 1);
            r++;
        }
        return maxLength;
    }

    /**
     * 绝对值不超过限制的最长连续子数组
     * （连续 => 滑动窗口，需要用到大小 ，考虑有序集合，例如 treeMap）
     *
     * @param nums
     * @param limit
     * @return
     */
    public int longestSubarray(int[] nums, int limit) {
        int l = 0, r = 0;
        int maxLength = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int n = nums.length;
        while (r < n) {
            map.put(nums[r], map.getOrDefault(nums[r], 0) + 1);
            while (map.lastKey() - map.firstKey() > limit) {
                map.put(nums[l], map.get(nums[l]) - 1);
                if (map.get(nums[l]) == 0) {
                    map.remove(nums[l]);
                }
                l++;
            }
            maxLength = Math.max(maxLength, r - l + 1);
            r++;
        }
        return maxLength;
    }


    /**
     * 使用单调队列
     **/
    public int longestSubarray_deque(int[] nums, int limit) {
        Deque<Integer> maxQueue = new ArrayDeque<>();
        Deque<Integer> minQueue = new ArrayDeque<>();
        int l = 0, r = 0, res = 0;
        while (r < nums.length) {
            while (!maxQueue.isEmpty() && nums[r] > maxQueue.peekLast())
                maxQueue.removeLast();
            while (!minQueue.isEmpty() && nums[r] < minQueue.peekLast())
                minQueue.removeLast();
            maxQueue.add(nums[r]);
            minQueue.add(nums[r]);
            r++;
            while (maxQueue.peek() - minQueue.peek() > limit) {
                if (maxQueue.peek() == nums[l]) maxQueue.remove();
                if (minQueue.peek() == nums[l]) minQueue.remove();
                l += 1;
            }
            res = Math.max(res, r - l);
        }
        return res;
    }

    /**
     * 单调递增的数字
     *
     * @param n
     * @return
     */
    public int monotoneIncreasingDigits(int n) {
        char[] chars = Integer.toString(n).toCharArray();
        int i = 1;
        int len = chars.length;
        while (i < len && chars[i - 1] <= chars[i]) {
            i++;
        }
        if (i < len) {
            while (i < len && chars[i - 1] > chars[i]) {
                chars[i - 1]--;
                i--;
            }
            for (int j = i + 1; j < len; j++) {
                chars[j] = '9';
            }
        }
        return Integer.parseInt(new String(chars));
    }
}
