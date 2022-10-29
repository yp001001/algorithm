package test2;

import java.util.*;

public class Wangyi {

    static Set<String> set = new HashSet<>();

    static {
        set.add("0");
        set.add("1");
        set.add("10");
        set.add("11");
        set.add("100");
        set.add("101");
        set.add("110");
        set.add("111");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int n = s.length();
        if (n <= 1) {
            System.out.println(1);
        } else {
            int[] dp = new int[n];
            dp[0] = 1;
            for (int i = 1; i < n; i++) {
                dp[i] = dp[i - 1];
                if (set.contains(s.substring(i - 1, i + 1))) {
                    if (i == 1) {
                        dp[i] += 1;
                    } else {
                        dp[i] += dp[i - 2];
                    }
                }
                if (i >= 2 && set.contains(s.substring(i - 2, i + 1))) {
                    if (i == 2) {
                        dp[i] += 1;
                    } else {
                        dp[i] += dp[i - 3];
                    }
                }
            }
            System.out.println(dp[n - 1]);
        }
    }


    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] nums = toNum(sc.nextLine());
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) continue;
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < nums.length; j++) {
                if (j < i && nums[j] == 1) {
                    min = Math.min(min, i - j);
                } else if (j > i && nums[j] == 1) {
                    min = Math.min(min, j - i);
                    break;
                }
            }
            ans = Math.max(ans, min);
        }
        System.out.println(ans);
    }


    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] a = toNum(sc.nextLine());
        int[] b = toNum(sc.nextLine());
        int length = Integer.parseInt(sc.nextLine());
        int[][] nums = new int[length][2];
        for (int i = 0; i < length; i++) {
            String[] split = sc.nextLine().split("\\s+");
            nums[i][0] = Integer.parseInt(split[0]);
            nums[i][1] = Integer.parseInt(split[1]);
        }
        TreeSet<Integer> ans = new TreeSet<>();
        int count = 0;
        for (int i = 0; i < length; i++) {
            int x = nums[i][0];
            int y = nums[i][1];
            while (x <= y) {
                if (ans.ceiling(x) != null) {
                    break;
                }
                x++;
            }
        }
        System.out.println(count);
    }

    static int[] toNum(String s) {
        String[] split = s.split("\\s+");
        int[] b = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            b[i] = Integer.parseInt(split[i]);
        }
        return b;
    }
}
