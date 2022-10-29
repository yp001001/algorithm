package topic.package03;

// 本题测试链接 : https://leetcode.com/problems/largest-1-bordered-square/
public class Problem06 {

    public static int largest1BorderedSquare(int[][] m) {
        int[][] right = new int[m.length][m[0].length];
        int[][] down = new int[m.length][m[0].length];
        setBorderMap(m, right);
        setBorderMap2(m, down);
        int N = m.length;
        int M = m[0].length;
        int C = 0;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                for (int border = 1; border <= Math.min(N - i, M - j); border++) {
                    if (right[i][j] >= border && down[i][j] >= border && right[i + border - 1][j] >= border
                            && down[i][j + border - 1] >= border) {
                        C = Math.max(C, border);
                    }
                }
            }
        }
        return C * C;
    }

    private static void setBorderMap2(int[][] m, int[][] down) {
        for (int i = 0; i < m[0].length; i++) {
            down[m.length - 1][i] = m[m.length - 1][i] == 1 ? 1 : 0;
            for (int j = m.length - 2; j >= 0; j--) {
                if (m[j][i] != 0)
                    down[j][i] = down[j + 1][i] + 1;
            }
        }
    }

    private static void setBorderMap(int[][] m, int[][] right) {
        for (int i = 0; i < m.length; i++) {
            right[i][m[0].length - 1] = (m[i][m[0].length - 1] == 1 ? 1 : 0);
            for (int j = m[0].length - 2; j >= 0; j--) {
                if (m[i][j] != 0)
                    right[i][j] = right[i][j + 1] + 1;
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(largest1BorderedSquare(new int[][]{{1, 1, 0, 0}}));
    }
}
