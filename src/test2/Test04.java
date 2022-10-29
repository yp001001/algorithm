package test2;

public class Test04 {

    public static void main(String[] args) {
        int maxLen = getMaxLen(new int[]{5, -20, -20, -39, -5, 0, 0, 0, 36, -32, 0, -7, -10, -7, 21, 20, -12, -34, 26, 2});
//        int maxLen = getMaxLen(new int[]{70, -18, 75, -72, -69, -84, 64, -65, 0, -82, 62, 54, -63, -85, 53, -60, -59, 29, 32, 59, -54, -29, -45});
        System.out.println(maxLen);
    }


    /**
     * 乘积最大子数组
     *
     * @param nums
     * @return
     */
    public int maxProduct_2(int[] nums) {
        // 方法二：使用dp + 滚动数组
        int maxF = nums[0];
        int minF = nums[0];
        int ans = maxF;
        for (int i = 1; i < nums.length; i++) {
            int mx = maxF;
            int mn = minF;
            //; 确实需要比较 maxF 与 nums[i] 和 minF 与 nums[i]
            maxF = Math.max(mx * nums[i], Math.max(nums[i], mn * nums[i]));
            minF = Math.min(mx * nums[i], Math.min(nums[i], mn * nums[i]));
            ans = Math.max(ans, maxF);
        }
        return ans;
    }

    public int maxProduct(int[] nums) {
        // 方法一：（两边遍历）
        int max = Integer.MIN_VALUE;
        int sum = 1;
        for (int num : nums) {
            if (num == 0) {
                sum = 1;
                max = Math.max(max, num);
            } else {
                sum *= num;
                max = Math.max(max, sum);
            }
        }
        sum = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] == 0) {
                sum = 1;
                max = Math.max(max, nums[i]);
            } else {
                sum *= nums[i];
                max = Math.max(max, sum);
            }
        }
        return max;
    }


    /**
     * 乘积为正数的最大子数组
     *
     * @param nums
     * @return
     */

    public static int getMaxLen(int[] nums) {
        int len = nums.length;
        int[] p = new int[len]; // 记录正数(0-i)最大值
        int[] n = new int[len]; // 记录负数(0-i)最大值
        if (nums[0] == 0) {
            p[0] = 0;
            n[0] = 0;
        } else {
            p[0] = nums[0] > 0 ? 1 : 0;
            n[0] = nums[0] < 0 ? 1 : 0;
        }
        for (int i = 1; i < len; i++) {
            if (nums[i] == 0) {
                p[i] = 0;
                n[i] = 0;
            } else if (nums[i] > 0) {
                p[i] = p[i - 1] + 1;
                n[i] = n[i - 1] == 0 ? 0 : n[i - 1] + 1;
            } else {
                p[i] = n[i - 1] == 0 ? 0 : n[i - 1] + 1;
                n[i] = p[i - 1] + 1;
            }
        }

        int max = 0;
        for (int i = 0; i < len; i++) {
            max = Math.max(max, p[i]);
        }
        return max;
    }
}
