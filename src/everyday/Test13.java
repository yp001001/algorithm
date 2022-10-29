package everyday;

import java.util.ArrayList;
import java.util.TreeMap;

public class Test13 {

    public static void main(String[] args) {
        System.out.println(numsOfString(10, 3));
        System.out.println(numsOfStrings2(10, 3));
    }

    public static int numsOfString(int n, int k) {
        int mod = (int) 1e6;
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 2; i <= n; i++) {
            dp[i][1] = 26;
        }
        for (int i = 4; i <= n; i++) {
            for (int j = 2; j <= k; j++) {
                dp[i][j] = dp[i - 1][j] + 25 * dp[i - 2][j - 1] % mod;
            }
        }
        return dp[n][k];
    }

    public static int numsOfStrings2(int n, int k) {
        // write code here
        //dp数组 代表 长度为n 且可以分为k段的字符串个数 为dp
        int mod = (int) 1e6;
        int[][] dp = new int[n + 1][k + 1];
        dp[0][0] = 1;
//        dp[1][0] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i][1] = 26;
        }
        for (int i = 4; i <= n; i++) {
            for (int j = 2; j <= k; j++) {
                dp[i][j] = dp[i - 1][j] + 25 * dp[i - 2][j - 1];
                dp[i][j] %= mod;
            }
        }
        return dp[n][k];
    }


    public static int minMax(ArrayList<Integer> a, int k, int x) {
        // write code here
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        for (int i : a) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for (int i = 0; i < k; i++) {
            int key = map.floorKey(Integer.MAX_VALUE);
            map.put(key - x, map.getOrDefault(key - x, 0) + 1);
            if (map.get(key) == 1) map.remove(key);
            else map.put(key, map.get(key) - 1);
        }
        return map.floorKey(Integer.MAX_VALUE);
    }

}
