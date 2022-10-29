package topic.package14;

import java.util.ArrayList;
import java.util.List;

public class MinColors {

    // N * M 的棋盘
    // 每种颜色的格子数必须相同
    // 相邻格子染的颜色必须不同
    // 所有格子必须染色
    // 返回至少多少种颜色可以完成任务
    public static int minColors(int N, int M) {
        for (int i = 2; i < N * M; i++) {
            int[][] matrix = new int[N][M];
            if ((N * M) % i == 0 && can(matrix, N, M, i)) {
                return i;
            }
        }
        return N * M;
    }

    // 判断pNum种颜色是否可以做到
    public static boolean can(int[][] matrix, int N, int M, int pNum) {
        int all = N * M;
        int every = all / pNum;
        ArrayList<Integer> rest = new ArrayList<>();
        rest.add(0);
        for (int i = 1; i <= pNum; i++) {
            rest.add(every);
        }
        return process(matrix, N, M, pNum, 0, 0, rest);
    }

    private static boolean process(int[][] matrix, int N, int M, int pNum, int row, int col, List<Integer> rest) {
        if (row == N) {
            return true;
        }
        if (col == M) {
            return process(matrix, N, M, pNum, row + 1, 0, rest);
        }
        int left = col == 0 ? 0 : matrix[row][col - 1];
        int up = row == 0 ? 0 : matrix[row - 1][col];
        for (int color = 1; color <= pNum; color++) {
            if (color != left && color != up && rest.get(color) > 0) {
                int count = rest.get(color);
                rest.set(color, count - 1);
                matrix[row][col] = color;
                if (process(matrix, N, M, pNum, row, col + 1, rest)) {
                    return true;
                }
                rest.set(color, count);
                matrix[row][col] = 0;
            }
        }
        return false;
    }

}
