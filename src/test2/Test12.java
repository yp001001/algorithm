package test2;

import java.util.*;

public class Test12 {

    public static void main(String[] args) {
        int[] ans = new Test12().findBuilding(new int[]{5, 3, 8, 3, 2, 5});
        System.out.println(Arrays.toString(ans));
    }


    public int search(int[] nums, int target) {
        if (nums.length == 1) {
            if (target == nums[0]) return 0;
            else return -1;
        }
        // 1. 先找到旋转点
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[r]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        int n = nums.length - 1;
        if (target <= nums[n]) {   // 表示在 l - n 之间搜索
            return binarySearch(nums, l, n, target);
        } else {                  // 表示在 0 - (l - 1) 之间搜索
            return binarySearch(nums, 0, l - 1, target);
        }
    }

    int binarySearch(int[] nums, int l, int r, int target) {
        while (l <= r) {
            int mid = (r + l) / 2;
            if (nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }


    //==========================================外观数组
    public String countAndSay(int n) {
        String s = "1";
        StringBuilder sb = new StringBuilder();
        while (n > 1) {
            int l = 0, r = 0, size = s.length();
            while (r < size) {
                if (s.charAt(l) != s.charAt(r)) {
                    sb.append(r - l);
                    sb.append(s.charAt(l));
                    l = r;
                } else {
                    r++;
                }
            }
            // 将最后一部分的数据保存到sb中
            sb.append(r - l);
            sb.append(s.charAt(l));
            n--;
            s = sb.toString();
            sb = new StringBuilder();
        }
        return s;
    }


    /**
     * 字符串转整数
     **/
    public int myAtoi(String s) {
        s = s.trim();
        if (s.length() == 0) return 0;
        char c = s.charAt(0);
        if (!Character.isDigit(c) && s.charAt(0) != '+' && s.charAt(0) != '-') {
            return 0;
        }
        boolean neg = s.charAt(0) == '-';
        int i = !Character.isDigit(s.charAt(0)) ? 1 : 0;
        long ans = 0;
        for (int j = i; j < s.length(); j++) {
            c = s.charAt(j);
            if (!Character.isDigit(c)) {
                break;
            }
            ans = ans * 10 + c - '0';
            if (neg) {
                if (-ans < Integer.MIN_VALUE) {
                    return Integer.MIN_VALUE - 1;
                }
            } else {
                if (ans > Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                }
            }
        }
        return neg ? -(int) ans : (int) ans;
    }


    /**
     * 压缩算法
     *
     * @param str
     * @return
     */
    public String compress(String str) {
        StringBuilder str1 = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        int index = 0;
        while (index < str.length()) {
            if (str.charAt(index) == ']') {
                StringBuilder TempStr = new StringBuilder();
                while (true) {
                    Character temp = stack.pop();
                    if (temp == '[') {
                        break;
                    }
                    TempStr.append(temp);
                }
                TempStr.reverse();
                String[] split = TempStr.toString().split("\\|");
                for (int i = 0; i < Integer.parseInt(split[0]); i++) {
                    str1.append(split[1]);
                }
                for (int i = 0; i < str1.length(); i++) {
                    stack.add(str1.charAt(i));
                }
                index += 1;
                str1.setLength(0);
                continue;
            }
            stack.add(str.charAt(index));
            index += 1;
        }
        str1.setLength(0);
        for (Character character : stack) {
            str1.append(character);
        }
        return str1.toString();
    }


    //====================================逛街=======================

    /**
     * 使用单调栈（就是找到前后递增的最大连续长度）
     *
     * @param heights
     * @return
     */
    public int[] findBuilding(int[] heights) {
        int n = heights.length;
        int[] ans = new int[n];
        LinkedList<Integer> stack1 = new LinkedList<>(), stack2 = new LinkedList<>();
        Arrays.fill(ans, 1);
        // 往左看，也就是要得到每个数左边有多少递增的
        for (int i = 0; i < n - 1; i++) {
            while (!stack1.isEmpty() && heights[i] >= stack1.getFirst()) {
                stack1.removeFirst();
            }
            stack1.addFirst(heights[i]);
            ans[i + 1] += stack1.size();
        }
        // 往右看，也就是要得到每个数右边有多少递增的
        for (int i = n - 1; i > 0; i--) {
            while (!stack2.isEmpty() && heights[i] >= stack2.getFirst()) {
                stack2.removeFirst();
            }
            stack2.addFirst(heights[i]);
            ans[i - 1] += stack2.size();
        }
        return ans;
    }


    public int lengthOfLongestSubstring(String s) {
        int l = 0, r = 0;
        int count = 0;
        Set<Character> set = new HashSet<>();
        while (r < s.length()) {
            while (set.contains(s.charAt(r))) {
                set.remove(s.charAt(l));
                l++;
            }
            set.add(s.charAt(r));
            r++;
            count = Math.max(count, r - l + 1);
        }
        return count;
    }
}
