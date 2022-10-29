package test;

import java.util.Arrays;
import java.util.Random;

/**
 * 按权重生成随机数
 */
class Solution {
    int sum = 0;
    int[] nums;

    public Solution(int[] w) {
        nums = new int[w.length];
        nums[0] = w[0];
        for (int i = 1; i < w.length; i++) {
            nums[i] = nums[i - 1] + w[i];
        }
        sum = Arrays.stream(w).sum();
    }

    public int pickIndex() {
        // 下标
        int index = (int) (Math.random() * sum) + 1;
        return binarySearch(index);
    }

    /**
     * 查找 >= index的最小数
     *
     * @param index
     * @return
     */
    private int binarySearch(int index) {
        int l = 0;
        int r = nums.length - 1;
        int ans = 0;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > index) {
                ans = mid;
                r = mid - 1;
            } else if (nums[mid] < index) {
                l = mid + 1;
            } else {
                return mid;
            }
        }
        return ans;
    }
}

