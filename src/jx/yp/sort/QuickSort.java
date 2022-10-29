package jx.yp.sort;

/**
 * 快排
 */
public class QuickSort {

    public static void main(String[] args) {
//        int[] arr = {-1, 2, 4, 5, 6, 1, 10, 9, 8, 7, 20, 0, 30};
//        int[] arr = {5, 1, 1, 2, 0, 0};
        int[] arr = {5, 2, 3, 1};
        quickSort2(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }


    private static void quickSort2(int[] arr, int left, int right) {
        if (left > right) {
            return;
        }
        int l = left, r = right;
        int temp = arr[left];
        while (l < r) {
            // 从右边开始
            while (l < r && arr[r] >= temp) {
                r--;
            }
            while (l < r && arr[l] <= temp) {
                l++;
            }
            if (l < r) {
                swap(arr, l, r);
            }
        }
        swap(arr,left,r);
        quickSort2(arr,left,r-1);
        quickSort2(arr,r+1,right);
    }
    

    private static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int l = left, r = right;
        int temp = arr[left]; // 将最左边的数作为基数
        while (l < r) {
            while (arr[r] >= temp && l < r) {
                r--;
            }
            while (arr[l] <= temp && l < r) {
                l++;
            }
            if (l < r) {
                swap(arr, l, r);
            }
        }
        // 将基准数与
        swap(arr, left, r);
        quickSort(arr, left, r - 1);
        quickSort(arr, r + 1, right);
    }


    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
