package topic.package17;

import java.util.Arrays;
import java.util.Comparator;

/*
俄罗斯套娃信封问题
 */
// 至少人数：c/(x+1) 向上取整
public class Envelopes {

    public static void main(String[] args) {
        int count = maxEnvelopes(new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}});
        System.out.println(count);
    }

    // 变种最长子序列问题
    public static int maxEnvelopes(int[][] es) {
        Arrays.sort(es, (x1, x2) -> x1[0] == x2[0] ? x2[1] - x1[1] : x1[0] - x2[0]);
        int[] dp = new int[es.length];
        Arrays.fill(dp, 1);
        int res = 1;
        for (int i = 0; i < es.length; i++) {
            for (int j = 0; j < i; j++) {
                if (es[i][1] > es[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static int maxEnvelopes_binary(int[][] matrix) {
        Envelope[] arr = sort(matrix);
        int[] ends = new int[matrix.length];
        ends[0] = arr[0].h;
        int right = 0;
        int l = 0;
        int r = 0;
        int m = 0;
        for (int i = 1; i < arr.length; i++) {
            l = 0;
            r = right;
            while (l <= r) {
                m = (l + r) / 2;
                if (arr[i].h > ends[m]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            right = Math.max(right, l);
            ends[l] = arr[i].h;
        }
        return right + 1;
    }

    public static class Envelope {
        public int l;
        public int h;

        public Envelope(int weight, int hight) {
            l = weight;
            h = hight;
        }
    }

    public static class EnvelopeComparator implements Comparator<Envelope> {
        @Override
        public int compare(Envelope o1, Envelope o2) {
            return o1.l != o2.l ? o1.l - o2.l : o2.h - o1.h;
        }
    }

    public static Envelope[] sort(int[][] matrix) {
        Envelope[] res = new Envelope[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            res[i] = new Envelope(matrix[i][0], matrix[i][1]);
        }
        Arrays.sort(res, new EnvelopeComparator());
        return res;
    }

}
