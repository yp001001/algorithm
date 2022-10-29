package zuo.package01;

import java.util.Arrays;

public class QuickSort {


    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
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


    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) return;
        process1(arr, 0, arr.length - 1);
    }

    // n^2（最坏时间复杂度）
    public static void process1(int[] arr, int l, int r) {
        if (l >= r) return;
        int[] equalArea = netherlandsFlag(arr, l, r);
        process1(arr, l, equalArea[0] - 1);
        process1(arr, equalArea[1] + 1, r);
    }

    /* 随机快排 */
    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) return;
        process2(arr, 0, arr.length - 1);
    }

    // (n * log2^n) 最坏时间复杂度
    private static void process2(int[] arr, int l, int r) {
        if (l >= r) return;
        swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
        int[] equalArea = netherlandsFlag(arr, l, r);
        process2(arr, l, equalArea[0] - 1);
        process2(arr, equalArea[1] + 1, r);
    }


    // learn（荷兰国旗问题）
    public static int[] netherlandsFlag2(int[] arr, int L, int R) {
        if (L > R) return new int[]{-1, -1};
        if (L == R) return new int[]{L, R};
        int less = L - 1, more = R, index = L;
        while (index < more) {
            if (arr[index] == arr[R]) {
                index++;
            } else if (arr[index] < arr[R]) {
                swap(arr, index++, ++less);
            } else {
                swap(arr, index, --more);
            }
        }
        swap(arr, R, more);
        return new int[]{less + 1, more};
    }

    public static void quickSort3(int[] arr) {
        process3(arr, 0, arr.length - 1);
    }

    private static void process3(int[] arr, int L, int R) {
        if (L >= R) return;
        swap(arr, L + (int) Math.random() * (R - L + 1), R);
        int[] equalArea = netherlandsFlag2(arr, L, R);
        process3(arr, L, equalArea[0] - 1);
        process3(arr, equalArea[1] + 1, R);
    }


    public static void main(String[] args) {
        int[] arr = new int[]{3, 4, 1, 0, 9, 8, 6, 7, 5, 2, -1};
        quickSort3(arr);
        System.out.println(Arrays.toString(arr));
    }

}
