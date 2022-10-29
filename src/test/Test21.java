package test;


import java.util.*;

public class Test21 {

    public static void main(String[] args) {
        long[] dp = new long[3];
        dp[0] = 1;
        Arrays.fill(dp, 1, 2, Integer.MAX_VALUE);
        for (long l : dp) {
            System.out.print(l + "\t");
        }
    }

    /**
     * 零钱兑换
     *
     * @param amount
     * @param coins
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }


    /**
     * 组合总数4
     *
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 0; i <= target; i++) {
            for (int num : nums) {
                if (num <= i) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }


    /**
     * 外星语言是否排序 Map+从后往前查找
     *
     * @param words
     * @param order
     * @return
     */
    public boolean isAlienSorted(String[] words, String order) {
        if (words.length == 1) {
            return true;
        }
        // 计数
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            map.put(order.charAt(i), i); // 保存字符与其下标
        }
        // 从后面向前面排序
        for (int i = words.length - 2; i >= 0; i--) {
            // 找到最小的size
            int a = words[i].length();
            int b = words[i + 1].length();
            // 寻找小的长度
            int size = a > b ? b : a;
            for (int m = 0; m < size; m++) {
                char c1 = words[i].charAt(m);
                char c2 = words[i + 1].charAt(m);
                if (map.get(c1) < map.get(c2)) break; // 表示该点的字符在其前面
                if (map.get(c1) > map.get(c2)) return false;
                if (m == size - 1 && a > b) return false;
            }
        }
        return true;
    }


    /**
     * 方法1：将字符串排序后放入 map<String,List<String>> 中，如果相同则是变位词       快
     * 方法2：计数
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            int[] ctr = new int[26];
            int idx;
            for (int j = 0; j < strs[i].length(); j++) {
                idx = strs[i].charAt(j) - 'a';
                ctr[idx]++;
            }
            StringBuilder sb = new StringBuilder();
            // 将排序好的数据放入sb，转成key
            for (int m = 0; m < 26; m++) {
                if (ctr[m] != 0) {
                    sb.append((char) ('a' + m));
                    sb.append(ctr[m]);
                }
            }
            String key = sb.toString();
            System.out.println(strs[i] + " key " + key);
            List<String> res = map.getOrDefault(key, new ArrayList<String>());
            res.add(strs[i]);
            map.put(key, res);
        }
        // 转换成List
        List<List<String>> ans = new ArrayList<>(map.values());
        return ans;
    }

}
