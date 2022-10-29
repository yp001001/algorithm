package topic.package04;



// 最大子矩阵
public class Problem03 {


    // 本题测试链接 : https://leetcode-cn.com/problems/max-submatrix-lcci/
    public static int[] getMaxMatrix(int[][] m) {
        int N = m.length;
        int M = m[0].length;
        int max = Integer.MIN_VALUE;
        int cur = 0;
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        for (int i = 0; i < N; i++) {
            int[] s = new int[M];
            for (int j = i; j < N; j++) {
                cur = 0;
                int begin = 0;
                for (int k = 0; k < M; k++) {
                    s[k] += m[j][k];
                    cur += s[k];
                    if (max < cur) {
                        max = cur;
                        a = i;
                        b = begin;
                        c = j;
                        d = k;
                    }
                    if (cur < 0) {
                        cur = 0;
                        begin = k + 1;
                    }
                }
            }
        }
        return new int[] { a, b, c, d };
    }
}
