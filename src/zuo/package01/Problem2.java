package zuo.package01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem2 {


    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // arr[L...R]上，以arr[R]位置的数做划分
    public static int partition(int[] arr, int L, int R) {
        if (L > R) return -1;
        if (L == R) return L;
        int lessEqual = L - 1;
        int index = L;
        while (index < R) {
            if (arr[index] <= arr[R]) {
                swap(arr, index, ++lessEqual);
            }
            index++;
        }
        swap(arr, ++lessEqual, R);
        return lessEqual;
    }

    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L > R) return new int[]{-1, -1};
        if (L == R) return new int[]{L, R};
        int less = L - 1, more = R, index = L;
        while (index < more) {
            if (arr[index] == arr[R]) {
                index++;
            } else if (arr[index] < arr[R]) {
                swap(arr, ++less, index++);
            } else {
                swap(arr, index, --more);
            }
        }
        swap(arr, R, more);
        return new int[]{less + 1, more};
    }


    //============================================


//    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
//        movePlate(A.size(), A, B, C);
//    }
//
//    private void movePlate(int num, List<Integer> A, List<Integer> B, List<Integer> C) {
//        if (num == 1) {    // 只剩一个盘子时，直接移动即可
//            C.add(A.remove(A.size() - 1));
//            return;
//        }
//
//        movePlate(num - 1, A, C, B);   // 将 size-1 个盘子，从 A 移动到 B
//        C.add(A.remove(A.size() - 1));   // 将 第size个盘子，从 A 移动到 C
//        movePlate(num - 1, B, A, C);   // 将 size-1 个盘子，从 B 移动到 C
//    }


    // 1    2     3     4
    // 5    6     7     8
    // 9    10    11    12
    // 13   14    15    16
    public static List<Integer> spiralOrder(int[][] matrix) {
        int row = matrix.length, col = matrix[0].length;
        int m = 0, n = 0;
        List<Integer> ans = new ArrayList<>();
        int size = row * col;
        row--;
        col--;
        while (ans.size() != size) {
            for (int i = n; i <= col; i++) {
                ans.add(matrix[m][i]);
            }
            if (ans.size() == size) break;
            m++;
            for (int i = m; i <= row; i++) {
                ans.add(matrix[i][col]);
            }
            if (ans.size() == size) break;
            col--;
            for (int i = col; i >= n; i--) {
                ans.add(matrix[row][i]);
            }
            if (ans.size() == size) break;
            row--;
            for (int i = row; i >= m; i--) {
                ans.add(matrix[i][n]);
            }
            if (ans.size() == size) break;
            n++;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 5, 1};
        netherlandsFlag(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

//        int[][] matrix = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
//        List<Integer> ans = spiralOrder(matrix);
//        for (int i = 0; i < ans.size(); i++) {
//            System.out.print(ans.get(i) + " ");
//        }
    }

}
