package zuo.dp.dp;

import java.util.HashMap;
import java.util.Map;

public class Test05 {

    // 贴纸问题
    public static int minStickers1(String[] stickers, String target) {
        int ans = process1(stickers, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static int process1(String[] stickers, String target) {
        if (target.length() == 0) return 0;
        int min = Integer.MAX_VALUE;
        for (String sticker : stickers) {
            String rest = minus(target, sticker);
            if (rest.length() != target.length()) {
                min = Math.min(min, process1(stickers, rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    public static String minus(String s1, String s2) {
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        int[] num = new int[26];
        for (char c : arr1) {
            num[c - 'a']++;
        }
        for (char c : arr2) {
            num[c - 'a']--;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (num[i] > 0) {
                for (int j = 0; j < num[i]; j++) {
                    builder.append((char) (i + 'a'));
                }
            }
        }
        return builder.toString();
    }

    public static int minStickers2(String[] stickers, String target) {
        int N = stickers.length;
        int[][] dp = new int[N][26];
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < stickers[k].length(); i++) {
                dp[k][stickers[k].charAt(i) - 'a']++;
            }
        }
        int min = process2(dp, target);
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public static int process2(int[][] stickers, String t) {
        if (t.length() == 0) return 0;
        char[] target = t.toCharArray();
        int[] tcounts = new int[26];
        char[] arr = t.toCharArray();
        for (char c : arr) {
            tcounts[c - 'a']++;
        }
        int N = stickers.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int[] sticker = stickers[i];
            // 只有第一个有，我们才进行后续操作（减枝）
            if (sticker[target[0] - 'a'] > 0) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (tcounts[j] > 0) {
                        int nums = tcounts[j] - sticker[j];
                        for (int k = 0; k < nums; k++) {
                            sb.append((char) (j + 'a'));
                        }
                    }
                }
                min = Math.min(min, process2(stickers, sb.toString()));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }


    public static int minStickers3(String[] stickers, String target) {
        int N = stickers.length;
        int[][] dp = new int[N][26];
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < stickers[k].length(); i++) {
                dp[k][stickers[k].charAt(i) - 'a']++;
            }
        }
        Map<String, Integer> map = new HashMap<>();
        int min = process3(dp, target, map);
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public static int process3(int[][] stickers, String t, Map<String, Integer> map) {
        if (t.length() == 0) return 0;
        if (map.containsKey(t)) return map.get(t);
        char[] target = t.toCharArray();
        int[] tcounts = new int[26];
        char[] arr = t.toCharArray();
        for (char c : arr) {
            tcounts[c - 'a']++;
        }
        int N = stickers.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int[] sticker = stickers[i];
            // 只有第一个有，我们才进行后续操作（减枝）
            if (sticker[target[0] - 'a'] > 0) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (tcounts[j] > 0) {
                        int nums = tcounts[j] - sticker[j];
                        for (int k = 0; k < nums; k++) {
                            sb.append((char) (j + 'a'));
                        }
                    }
                }
                min = Math.min(min, process3(stickers, sb.toString(), map));
            }
        }
        int res = min + (min == Integer.MAX_VALUE ? 0 : 1);
        map.put(t, res);
        return res;
    }

    public static void main(String[] args) {
        String[] stickers = new String[]{"notice", "possible"};
        System.out.println(minStickers1(stickers, "basicbasic"));
        System.out.println(minStickers2(stickers, "basicbasic"));
        System.out.println(minStickers3(stickers, "basicbasic"));
        System.out.println(Integer.MAX_VALUE);
    }
}
