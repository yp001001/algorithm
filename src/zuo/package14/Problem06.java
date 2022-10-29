package zuo.package14;

// 顺时针旋转矩阵90°
public class Problem06 {

    public void rotate(int[][] matrix) {
        int a = 0;
        int b = 0;
        int c = matrix.length - 1;
        int d = matrix[0].length - 1;
        while (a < c) {
            rotateEdge(matrix, a++, b++, c--, d--);
        }
    }

    private void rotateEdge(int[][] matrix, int a, int b, int c, int d) {
        int tmp;
        for (int i = 0; i < d - b; i++) {
            tmp = matrix[a][b + i];
            matrix[a][b + i] = matrix[c - i][b];
            matrix[c - i][b] = matrix[c][d - i];
            matrix[c][d - i] = matrix[a + i][d];
            matrix[a + i][d] = tmp;
        }
    }
}
