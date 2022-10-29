package test2;

import java.util.*;

public class Test01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] nums = new int[9];
        for (int i = 0; i < 13; i++) {
            nums[sc.nextInt() - 1]++;
        }
        for (int i = 0; i < 9; i++) {
            if (nums[i] <= 3) {
                nums[i]++;
                if (isHu(nums, 0, 14)) {
                    System.out.println((i + 1) + " ");
                }
                nums[i]--;
            }
        }
    }

    private static boolean isHu(int[] nums, int start, int rest) {
        if (rest == 0) {
            return true;
        }
        // 雀头 或 顺子 或 三带
        if (nums[start] >= 2 && rest % 3 != 0) {
            nums[start] -= 2;
        }
        return false;
    }


    /**
     * Z国的货币系统包含面值1元、4元、16元、64元共计4种硬币，以及面值1024元的纸币。
     * 现在小Y使用1024元的纸币购买了一件价值为的商品，请问最少他会收到多少硬币？
     * （完全背包问题）
     */
    private static void zijie03() {
        Scanner sc = new Scanner(System.in);
        int money = 1024 - Integer.parseInt(sc.nextLine());
        long[] dp = new long[money + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        int[] genre = new int[]{1, 4, 16, 64};
        for (int i = 0; i < genre.length; i++) {
            for (int j = 1; j <= money; j++) {
                if (j >= genre[i]) {
                    dp[j] = Math.min(dp[j], dp[j - genre[i]] + 1);
                }
            }
        }
        System.out.println(dp[money]);
    }


    public static void run(int n, int d, int[] position) {
        long sum = 0L;
        long mod = 99997867;
        for (int i = 0, j = 0; i < n; i++) {
            //从第三个开始判断，判断是否违法，如果违法j++
            while (i >= 2 && position[i] - position[j] > d) {
                j++;
            }
            //计算合法的次数，n(n-i)/2
            sum += (long) (i - j) * (long) (i - j - 1) / 2;
        }
        System.out.println(sum % mod);
    }


    /**
     * 寻找数组中小于等于target的数
     *
     * @param target
     * @return
     */
    private int binarySearch(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid;
            }
        }
        return l;
    }

    private static void zijie02(int[][] nums) {
        Scanner sc = new Scanner(System.in);
        String[] strs = sc.nextLine().split(" ");
        int N = Integer.parseInt(strs[0]);    // 可选作为埋伏点的建筑物数
        int D = Integer.parseInt(strs[1]);    // 相距最远的两名特工间的距离的最大值
        int[] places = new int[N];
        String[] sp = sc.nextLine().split(" ");
        for (int i = 0; i < N; i++) {
            places[i] = Integer.parseInt(sp[i]);
        }
        run(N, D, new int[]{1, 2, 3, 4, 5, 6});
    }

    /**
     * 注意：如果我们初始化所有数据为 MIN_VALUE，dp[0] = 0，然后dp[n]就是判断体积刚好最大价值
     * *****如果初始化所有数据为0，dp[n]就是判断体积内最大价值
     *
     * @param goods
     */
    public static int duochongbeibao(int[][] goods, int target) {
        // goods[0]:价值，goods[1]:体积，goods[2]:数量
        int n = goods.length;
        int[] dp = new int[target + 1];
        for (int i = 0; i < n; i++) {
            for (int j = target; j >= 0; j--) {
                for (int k = 1; k <= goods[i][2]; k++) {
                    if (j >= k * goods[i][1]) {
                        dp[j] = Math.max(dp[j], dp[j - k * goods[i][1]] + k * goods[i][0]);
                    }
                }
            }
        }
        return dp[target];
    }


    private static void zijie01() {
        Scanner sc = new Scanner(System.in);
        int count = Integer.parseInt(sc.nextLine());
        List<String> res = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String str = sc.nextLine();
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < str.length(); j++) {
                char c = str.charAt(j);
                if (sb.length() > 1 && sb.charAt(sb.length() - 1) == c) {  // 当第一个与最后一个相同时
                    // AAA，AABB
                    if (sb.charAt(sb.length() - 2) == c || sb.length() > 2 && sb.charAt(sb.length() - 2) == sb.charAt(sb.length() - 3)) {
                        continue;
                    }
                }
                sb.append(c);
            }
            res.add(sb.toString());
        }
        res.forEach(System.out::println);
    }
}
