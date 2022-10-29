package topic.package16;

// https://leetcode.cn/problems/maximum-product-subarray/
public class MaxProduct {

    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{2, 3, -2, -1, 3, -2, 4, 5}));
    }

    public static int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] maxDp = new int[n];
        int[] minDp = new int[n];
        maxDp[0] = nums[0];
        minDp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int a = nums[i];
            int b = nums[i] * maxDp[i - 1];
            int c = nums[i] * minDp[i - 1];
            maxDp[i] = Math.max(a, Math.max(b, c));
            minDp[i] = Math.min(a, Math.min(b, c));
            max = Math.max(max, maxDp[i]);
        }
        return max;
    }

    public static int max(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int max = arr[0], min = arr[0], answer = arr[0];
        for (int i = 1; i < arr.length; i++) {
            int a = arr[i];
            int b = arr[i] * max;
            int c = arr[i] * min;
            max = Math.max(a, Math.max(b, c));
            min = Math.min(a, Math.min(b, c));
            answer = Math.max(max, min);
        }
        return answer;
    }

}
