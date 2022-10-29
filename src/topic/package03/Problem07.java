package topic.package03;

import java.util.Arrays;

// 划船问题
public class Problem07 {
    public static int numRescueBoats(int[] arr, int limit) {
        Arrays.sort(arr);
        int ans = 0;
        int l = 0;
        int r = arr.length - 1;
        int sum = 0;
        while (l <= r) {
            sum = l == r ? arr[l] : arr[l] + arr[r];
            if (sum > limit) {
                r--;
            } else {
                l++;
                r--;
            }
            ans++;
        }
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(numRescueBoats(new int[]{3, 2, 3, 2, 2}, 6));
    }
}
