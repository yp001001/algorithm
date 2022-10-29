package jx.yp.sort;

/**
 * 选择排序
 */
public class Choose {



    public static void main(String[] args) {
        int[] arr = {2, 4, 5, 6, 1, 10, 9, 8, 7, 20, 0};
        chooseSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void chooseSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            // 保存最小数的下标
            int temp = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[temp] > nums[j]) {
                    temp = j;
                }
            }
            if (i != temp) swap(nums, i, temp);
        }
    }

    public static void ChooseSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            // 假设最小数为第i个元素
            int temp = i; // 保存最小数的下标
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[temp] > arr[j]) {
                    temp = j;
                }
            }
            if (temp != i) {
                swap(arr, i, temp);
            }
        }
    }


    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
