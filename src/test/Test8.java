package test;

public class Test8 {

    public static void main(String[] args) {
        int i = uniquePathsWithObstacles(new int[][]{{0, 1}});
//        int i = uniquePathsWithObstacles(new int[][]{{0, 1, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}});
        System.out.println(i);
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid.length == 1) {
            for (int i = 0; i < obstacleGrid[0].length; i++) {
                if (obstacleGrid[0][i] == 1) {
                    return 0;
                }
            }
            return 1;
        }
        int row = obstacleGrid.length;
        int column = obstacleGrid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (obstacleGrid[i][j] == 1) {
                    obstacleGrid[i][j] = -1;
                }
            }
        }
        int[][] dp = new int[row][column];
        for (int i = 0; i < column; i++) {
            dp[0][i] = 1;
        }
        for (int i = 0; i < row; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                if (obstacleGrid[i][j] == -1) continue;
                if (obstacleGrid[i - 1][j] == -1) {
                    dp[i][j] = dp[i][j - 1];
                } else if (obstacleGrid[i][j - 1] == -1) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[row - 1][column - 1];
    }
}
