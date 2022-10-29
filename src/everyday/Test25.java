package everyday;

import java.util.Arrays;

public class Test25 {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 4, 6, 8, 9, 10, 1, 5, 7};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length == 0) return;
        merge(arr, 0, arr.length - 1);
    }

    private static void merge(int[] arr, int left, int right) {
        if (left >= right) return;
        int mid = (left + right) >> 1;
        merge(arr, left, mid);
        merge(arr, mid + 1, right);
        sort(arr, left, mid, right);
    }

    private static void sort(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int p1 = left, p2 = mid + 1;
        int index = 0;
        while (p1 <= mid && p2 <= right) {
            temp[index++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) temp[index++] = arr[p1++];
        while (p2 <= right) temp[index++] = arr[p2++];
        for (int i = 0; i < right - left + 1; i++) {
            arr[left + i] = temp[i];
        }
    }


    public static void quickSort(int[] arr) {
        if (arr == null || arr.length == 0) return;

    }
}
