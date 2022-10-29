package topic.package17;

import java.util.Arrays;

public class TitleToNumber {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        rotate(nums, 3);
        System.out.println(Arrays.toString(nums));
    }

    public static int titleToNumber(String columnTitle) {
        if (columnTitle == null || columnTitle.length() == 0) return 0;
        int ans = 0;
        int n = columnTitle.length() - 1;
        for (int i = 0; i < columnTitle.length(); i++) {
            ans += (columnTitle.charAt(i) - 'A' + 1) * Math.pow(26, n - i);
        }
        return ans;
    }

    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        rotate(nums, 0, n - k - 1);
        rotate(nums, n - k, n - 1);
        rotate(nums, 0, n - 1);
    }

    private static void rotate(int[] nums, int left, int right) {
        int t;
        for (int i = left, j = right; i <= j; i++, j--) {
            t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
    }

}
