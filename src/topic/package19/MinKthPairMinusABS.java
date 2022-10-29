package topic.package19;

import java.util.Arrays;

/*
    比如 {5，3，1，4}
    全部数字对是：(5,3),(5,4),(5,1),(3,1),(3,4),(1,4)
    数字对的差值的绝对值：2，4，1，2，1，3
    给定一个数组arr和一个正数k，求arr中所有数字对差值的绝对值，第k小的是多少
 */
public class MinKthPairMinusABS {


    // 二分 + 不回退
    public static int kthABS2(int[] arr, int k) {
        int n = arr.length;
        if (n < 2 || k < 1 || k > ((n * (n - 1)) >> 1)) {
            return -1;
        }
        Arrays.sort(arr);
        int left = 0;
        int right = arr[n - 1] - arr[0];
        int mid;
        int rightest = -1;
        while (left <= right) {
            mid = (left + right) / 2;
            if (valid(arr, mid, k)) {
                rightest = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return rightest + 1;
    }

    public static boolean valid(int[] arr, int limit, int k) {
        int x = 0;
        for (int l = 0, r = 1; l < arr.length; r = Math.max(r, ++l)) {
            while (r < arr.length && arr[r] - arr[l] <= limit) {
                r++;
            }
            x += r - l + 1;
        }
        return x < k;
    }

}
