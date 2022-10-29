package zuo.package15;

/* 最好的划分 */
public class Problem01 {

    public static int[] bestSplit(int[] arr) {
        if (arr == null || arr.length == 0) return new int[0];
        int N = arr.length;
        int[] ans = new int[N];
        ans[0] = 0;
        int[] sum = new int[N + 1];
        for (int i = 0; i < N; i++) {
            sum[i + 1] = sum[i] + arr[i];
        }
        int best = 0;
        for (int range = 1; range < N; range++) {
            while (best + 1 < range) {
                // 两个差值尽量小，而总量不变，则两个数小值应该尽量大
                int before = Math.min(sum(sum, 0, best), sum(sum, best + 1, range));
                int after = Math.min(sum(sum, 0, best + 1), sum(sum, best + 2, range));
                if (before <= after) {
                    best++;
                } else {
                    break;
                }
            }
            ans[range] = Math.min(sum(sum, 0, best), sum(sum, best + 1, range));
        }
        return ans;
    }

    private static int sum(int[] sum, int i, int best) {
        return sum[best] - sum[i];
    }

}
