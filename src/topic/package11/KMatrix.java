package topic.package11;


// https://leetcode.cn/problems/kth-smallest-element-in-a-sorted-matrix/
public class KMatrix {

    public static void main(String[] args) {
        KMatrix kMatrix = new KMatrix();
        kMatrix.kthSmallest(new int[][]{{-5}}, 1);
    }

    class Info {
        int near;
        int num;

        public Info(int near, int num) {
            this.near = near;
            this.num = num;
        }
    }

    // 寻找小于等于当前值的最大个数以及最接近数
    public Info noMoreMax(int[][] matrix, int val) {
        int near = Integer.MIN_VALUE;
        int num = 0;
        int N = matrix.length;
        int M = matrix[0].length;
        int row = 0, col = M - 1;
        while (row < N && col >= 0) {
            if (matrix[row][col] <= val) {
                near = Math.max(near, matrix[row][col]);
                num += col + 1;
                row++;
            } else {
                col--;
            }
        }
        return new Info(near, num);
    }

    public int kthSmallest(int[][] matrix, int k) {
        int N = matrix.length, M = matrix[0].length;
        int left = matrix[0][0], right = matrix[N - 1][M - 1];
        int ans = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            Info info = noMoreMax(matrix, mid);
            if (info.num < k) {
                left = mid + 1;
            } else {
                ans = info.near;
                right = mid - 1;
            }
        }
        return ans;
    }
}
