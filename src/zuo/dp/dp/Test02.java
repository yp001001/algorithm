package zuo.dp.dp;

import java.util.Arrays;

// 石子游戏问题
public class Test02 {

    public static int win1(int[] nums) {
        int pre = f1(nums, 0, nums.length - 1);
        int last = g1(nums, 0, nums.length - 1);
        return Math.max(pre, last);
    }

    public static int f1(int[] nums, int l, int r) {
        if (l == r) return nums[l];
        int p1 = nums[l] + g1(nums, l + 1, r);
        int p2 = nums[r] + g1(nums, l, r - 1);
        return Math.max(p1, p2);
    }

    public static int g1(int[] nums, int l, int r) {
        if (l == r) return 0;
        int p1 = f1(nums, l + 1, r);
        int p2 = f1(nums, l, r - 1);
        return Math.min(p1, p2);
    }


    public static int win2(int[] nums) {
        int n = nums.length;
        int[][] fmap = new int[n][n];
        int[][] gmap = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(fmap[i], -1);
            Arrays.fill(gmap[i], -1);
        }
        int pre = f2(nums, 0, nums.length - 1, fmap, gmap);
        int last = g2(nums, 0, nums.length - 1, fmap, gmap);
        return Math.max(pre, last);
    }

    public static int f2(int[] nums, int l, int r, int[][] fmap, int[][] gmap) {
        if (l == r) return nums[l];
        if (fmap[l][r] != -1) return fmap[l][r];
        int p1 = nums[l] + g2(nums, l + 1, r, fmap, gmap);
        int p2 = nums[r] + g2(nums, l, r - 1, fmap, gmap);
        int res = Math.max(p1, p2);
        fmap[l][r] = res;
        return res;
    }


    public static int g2(int[] nums, int l, int r, int[][] fmap, int[][] gmap) {
        if (l == r) return 0;
        if (gmap[l][r] != -1) return gmap[l][r];
        int p1 = f2(nums, l + 1, r, fmap, gmap);
        int p2 = f2(nums, l, r - 1, fmap, gmap);
        int res = Math.min(p1, p2);
        gmap[l][r] = res;
        return res;
    }


    public static int win3(int[] nums) {
        int n = nums.length;
        int[][] fmap = new int[n][n];
        int[][] gmap = new int[n][n];
        for (int i = 0; i < n; i++) {
            fmap[i][i] = nums[i];
        }
        for (int i = 1; i < n; i++) {
            int l = 0, r = i;
            while (r < n) {
                gmap[l][r] = Math.min(fmap[l + 1][r], fmap[l][r - 1]);
                fmap[l][r] = Math.max(nums[l] + gmap[l + 1][r], nums[r] + gmap[l][r - 1]);
                l++;
                r++;
            }
        }
        return Math.max(fmap[0][n - 1], gmap[0][n - 1]);
    }


    public static void main(String[] args) {
        int[] nums = new int[]{5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7};
        int winScore1 = win1(nums);
        int winScore2 = win2(nums);
        int winScore3 = win3(nums);
        System.out.println(winScore1);
        System.out.println(winScore2);
        System.out.println(winScore3);
    }
}
