package dp;

import java.util.*;

// https://leetcode.cn/problems/maximum-profit-in-job-scheduling/
public class Test02 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        long[] nums = new long[n];
        for (int i = 0; i < n; i++) {
            nums[i] = getPreStr(sc.nextLine());
        }
        Arrays.sort(nums);
        String now = String.valueOf(nums[0]);
        int nowIndex = 0;
        List<String> res = new ArrayList<>();
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1] + 1) {
                if (nowIndex != i - 1) {
                    res.add(now + " to " + nums[i - 1]);
                } else {
                    res.add(now);
                }
                now = String.valueOf(nums[i]);
                nowIndex = i;
            }
        }
        if (nowIndex < nums.length) {
            res.add(now + " to " + nums[nums.length - 1]);
        }
        for (String re : res) {
            System.out.println(re);
        }
    }


    public static long getPreStr(String s) {
        String[] sp = s.split("-");
        String str = "";
        for (String s1 : sp) {
            str += s1;
        }
        long l = Long.parseLong(str);
        return l;

    }


    // 超出内存
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int finalTime = 0;
        for (int time : endTime) {
            finalTime = Math.max(finalTime, time);
        }
        // 表示在endTime时间下获得的最大收益
        int[] dp = new int[finalTime + 1];
        dp[0] = 0;
        // key->endTime ： value->Index
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < endTime.length; i++) {
            List<Integer> list = map.getOrDefault(endTime[i], new ArrayList<>());
            list.add(i);
            map.put(endTime[i], list);
        }
        for (int i = 1; i <= finalTime; i++) {
            List<Integer> list = map.get(i);
            dp[i] = dp[i - 1];
            if (list != null) {
                for (int index : list) {
                    dp[i] = Math.max(dp[i], dp[startTime[index]] + profit[index]);
                }
            }
        }
        return dp[finalTime];
    }


}
