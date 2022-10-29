package test2;

import java.util.*;

public class Test14 {

    public static void main(String[] args) {
        String[] split = "a good   example".split("");
        System.out.println(split);
        for (int i = 0; i < split.length; i++) {

            System.out.println(split[i]);
        }
    }

    /**
     * Z字符变换
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        int n = s.length(), r = numRows;
        if (n < r || r == 1) {
            return s;
        }
        // 每个周期的大小
        int t = r + r - 2;
        // 得到每个周期的列数（最后不满一个周期算作一个周期，所以 + t）
        int c = (n - 1 + t) / t * (r - 1);
        char[][] ans = new char[r][c];
        for (int i = 0, x = 0, y = 0; i < n; i++) {
            ans[x][y] = s.charAt(i);
            // 下降
            if (i % t < r - 1) {
                x++;
            } else {
                x--;
                y++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (ans[i][j] != 0) {
                    sb.append(ans[i][j]);
                }
            }
        }
        return sb.toString();
    }

    public int reverse(int x) {
        StringBuilder res = new StringBuilder();
        boolean neg = x < 0;
        x = Math.abs(x);
        while (x > 0) {
            int i = x % 10;
            x /= 10;
            res.append(i);
        }
        try {
            return neg ? -Integer.parseInt(res.toString()) : Integer.parseInt(res.toString());
        } catch (Exception e) {
            return 0;
        }
    }


    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            // 找左边的最小值
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        // i+1开始的数据都是递减的，我们交换
        reverse(nums, i + 1);
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    /**
     * 四数之和
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < n; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                int m = n - 1;
                for (int k = j + 1; k < n; k++) {
                    if (k > j + 1 && nums[k] == nums[k - 1]) continue;
                    while (m > k && nums[i] + nums[j] + nums[k] + nums[m] > target) {
                        m--;
                    }
                    if (m > k && nums[i] + nums[j] + nums[k] + nums[m] == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[k], nums[m]));
                    }
                }
            }
        }
        return res;
    }

    /**
     * 最接近的三数之和
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int best = 10000000;
        // 枚举a
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1, k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                // 如果和为 target 直接返回答案
                if (sum == target) {
                    return target;
                }
                // 根据差值的绝对值来更新答案
                if (Math.abs(sum - target) < Math.abs(best - target)) {
                    best = sum;
                }
                if (sum > target) {
                    int k0 = k - 1;
                    // 移动到下一个不相等的元素
                    while (j < k0 && nums[k0] == nums[k]) {
                        --k0;
                    }
                    k = k0;
                } else {
                    // 如果和小于 target,移动b对应的指针
                    int j0 = j + 1;
                    // 移动到下一个不相等的元素
                    while (j0 < k && nums[j0] == nums[j0 - 1]) {
                        ++j0;
                    }
                    j = j0;
                }
            }
        }
        return best;
    }


    public int[] exchange(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            while (l < r && (nums[l] & 1) == 1) l++;
            while (l < r && (nums[r] & 1) == 0) r--;
            swap(nums, l, r);
        }
        return nums;
    }

    public String reverseWords(String s) {
        int left = 0, right = s.length() - 1;
        while (left <= right && s.charAt(left) == ' ') {
            ++left;
        }
        while (left <= right && s.charAt(right) == ' ') {
            --right;
        }
        Deque<String> d = new ArrayDeque<>();
        StringBuilder word = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);
            if (word.length() != 0 && (c == ' ')) {
                // 将单词push到队列的头部
                d.offerFirst(word.toString());
                word.setLength(0);
            } else if (c != ' ') {
                word.append(c);
            }
            ++left;
        }
        d.offerFirst(word.toString());
        return String.join(" ", d);
    }
}
