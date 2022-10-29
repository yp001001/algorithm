package everyday;

import java.util.Arrays;

// https://leetcode.cn/problems/defuse-the-bomb/
public class Test62 {

    public static void main(String[] args) {
        int[] nums = decrypt(new int[]{2, 4, 9, 3}, -2);
        System.out.println(Arrays.toString(nums));
    }

    public static int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] nums = new int[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            nums[i] = code[i % n];
        }
        int[] sum = new int[2 * n];
        sum[0] = nums[0];
        for (int i = 1; i < 2 * n; i++) {
            sum[i] = nums[i] + sum[i - 1];
        }
        int[] res = new int[n];
        int index;
        if (k < 0) {
            index = 2 * n - 2;
            for (int i = n - 1; i >= 0; i--) {
                res[i] = sum[index] - sum[index-- + k];
            }
        } else if (k == 0) {
            return res;
        } else {
            for (int i = 0; i < n; i++) {
                res[i] = sum[k + i] - sum[i];
            }
        }
        return res;
    }

}
