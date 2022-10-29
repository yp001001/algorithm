package topic.package12;

import java.util.Arrays;

// 反转数组
public class ReverseNum {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        reverseNum(arr, 5);
        System.out.println(Arrays.toString(arr));
    }

    public static void reverseNum(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k == 0) return;
        reverse(arr, 0, arr.length - 1);
        reverse(arr, 0, k);
        reverse(arr, k + 1, arr.length - 1);
    }

    private static void reverse(int[] arr, int i, int j) {
        int temp;
        for (; i < j; i++, j--) {
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

}
