package zuo.package01;

import java.util.Arrays;

public class MergeSort {

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int l, int r) {
        if (l == r) return;
        int mid = (l + r) >>> 1;
        process(arr, l, mid);
        process(arr, mid + 1, r);
        // 合并
        merge(arr, l, mid, r);
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        int[] temp = new int[r - l + 1];
        int p1 = l, p2 = mid + 1;
        int index = 0;
        while (p1 <= mid && p2 <= r) {
            temp[index++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) temp[index++] = arr[p1++];
        while (p2 <= r) temp[index++] = arr[p2++];

        for (int i = 0; i < r - l + 1; i++) {
            arr[l + i] = temp[i];
        }
    }


    public static void mergeSort3(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int mergeSize = 1;
        int N = arr.length;
        while (mergeSize < N) {
            int L = 0;
            while (L < N) {
                int M = L + mergeSize - 1;
                if (M >= N) break;
                int R = Math.min(M + mergeSize, N - 1);
                merge(arr, L, M, R);
                L = R + 1;
            }
            // 防止溢出
            if (mergeSize > N / 2) break;
            mergeSize <<= 1;
        }
    }


    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int N = arr.length;
        // 步长
        int mergeSize = 1;
        while (mergeSize < N) {
            int L = 0;
            while (L < N) {
                int M = L + mergeSize - 1;
                if (M >= N) break;
                int R = Math.min(M + mergeSize, N - 1);
                merge(arr, L, M, R);
                L = R + 1;
            }
            if (mergeSize > N / 2) break;
            mergeSize <<= 1;
        }
    }


    public static void main(String[] args) {
        int[] arr = new int[]{3, 4, 1, 0, 9, 8, 6, 7, 5, 2, -1};
        mergeSort3(arr);
        System.out.println(Arrays.toString(arr));
    }
}
