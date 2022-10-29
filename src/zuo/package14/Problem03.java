package zuo.package14;

import java.util.HashMap;
import java.util.Map;

/**
 * 以它为基准，以它为基准
 **/
public class Problem03 {


    // 和大小为k的最长子数组
    public static int maxLength(int[] arr, int k) {
        if (arr == null || arr.length == 0) return 0;
        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = arr[i] + sum[i - 1];
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int ans = 0;
        for (int i = 0; i < sum.length; i++) {
            if (map.containsKey(sum[i] - k))
                ans = Math.max(i - map.get(sum[i] - k), ans);
            if (!map.containsKey(sum[i]))
                map.put(sum[i], i);
        }
        return ans;
    }


    // 0 和 1 个数相同的子数组
    public static int findMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] sum = new int[nums.length];
        sum[0] = nums[0] == 1 ? 1 : -1;
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + (nums[i] == 1 ? 1 : -1);
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int maxLength = 0;
        for (int i = 0; i < sum.length; i++) {
            if (map.containsKey(sum[i]))
                maxLength = Math.max(maxLength, i - map.get(sum[i]));
            if (!map.containsKey(sum[i]))
                map.put(sum[i], i);
        }
        return maxLength;
    }


    // 小于等于k的子数组最大长度
    public static int maxLengthAwesome(int[] arr, int k) {
        if (arr == null || arr.length == 0) return 0;
        int[] minSums = new int[arr.length];
        int[] minSumends = new int[arr.length];
        minSums[arr.length - 1] = arr[arr.length - 1];
        minSumends[arr.length - 1] = arr.length - 1;
        // 从后往前得到往后扩的最小值与尾部下标
        for (int i = arr.length - 2; i >= 0; i++) {
            if (minSums[i + 1] < 0) {
                minSums[i] = arr[i] + minSums[i + 1];
                minSumends[i] = minSumends[i + 1];
            } else {
                minSums[i] = arr[i];
                minSumends[i] = i;
            }
        }
        int end = 0;    // 迟迟扩不进来的下标
        int sum = 0;
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            while (end < arr.length && sum + minSums[i] <= k) {
                end += minSumends[i];
                sum += minSums[i];
            }
            ans = Math.max(ans, end - i);
            if (end > i) {      // 还有窗口
                sum -= arr[i];
            } else {
                end = i + 1;
            }
        }
        return ans;
    }

}
