package topic.package13;

public class MininumCostToMergeStones {

    public static void main(String[] args) {
        System.out.println(MinCostMerge(new int[]{1, 2, 3, 4, 5, 6,7}, 3));
    }


    // 给定数组，只能k个数合并，求合并的最小代价
    public static int MinCostMerge(int[] stones, int k) {
        int n = stones.length;
        if ((n - 1) % (k - 1) > 0) return -1;
        int[] presum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            presum[i + 1] = presum[i] + stones[i];
        }
        return process1(0, n - 1, 1, stones, k, presum);
    }

    // arr[L...R] 一定要合并出P份，返回合并的最小代价！！！ （优化：使用dp）
    public static int process1(int L, int R, int P, int[] arr, int K, int[] preSum) {
        if (L == R) {
            return P == 1 ? 0 : -1;
        }
        if (P == 1) {
            int next = process1(L, R, K, arr, K, preSum);
            if (next == -1) {
                return -1;
            } else {
                return next + preSum[R + 1] - preSum[L];
            }
        } else {
            int ans = Integer.MAX_VALUE;
            for (int i = L; i <= R; i++) {
                int left = process1(L, R, 1, arr, K, preSum);
                int right = process1(L, R, P - 1, arr, K, preSum);
                if (left != -1 && right != -1) {
                    ans = Math.min(ans, left + right);
                }
            }
            return ans;
        }
    }

}
