package test;

public class NumMatrix {


    int[][] sums;
    int[][] matrix;

    public NumMatrix(int[][] matrix) {
        /**
         初始化sums数组
         */
        this.matrix = matrix;
        if (matrix.length > 0) {
            int row = matrix.length;
            int column = matrix[0].length;
            sums = new int[row][column + 1];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    sums[i][j + 1] = sums[i][j] + matrix[i][j];
                }
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int i = row1; i <= row2; i++) {
            sum += (sums[i][col2 + 1] - sums[i][col1]);
        }
        return sum;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}};
        NumMatrix numMatrix = new NumMatrix(matrix);
        int sum = numMatrix.sumRegion(1, 1, 2, 2);
        System.out.println(sum);
    }
}
