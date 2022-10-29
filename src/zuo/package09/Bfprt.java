package zuo.package09;

import java.util.Arrays;
import java.util.Random;

import static zuo.package10.DrabStack.logarithm;

/* 蓄水池算法 */
public class Bfprt {


    public static int minKth(int[] array, int k) {
        int[] arr = copyArray(array);
        return process(arr, 0, arr.length - 1, k - 1);
    }

    private static int[] copyArray(int[] array) {
        int[] ans = new int[array.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = array[i];
        }
        return ans;
    }

    public static int process(int[] arr, int L, int R, int index) {
        if (L == R) { // L == R == index
            return arr[L];
        }
        int pivot = arr[L + (int) (Math.random() * (R - L + 1))];
        int[] range = partition(arr, L, R, pivot);
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return process(arr, L, range[0] - 1, index);
        } else {
            return process(arr, range[1] + 1, R, index);
        }
    }

    private static int[] partition(int[] arr, int L, int R, int pivot) {
        int less = L - 1;
        int more = R + 1;
        int index = L;
        while (index < more) {
            if (arr[index] == pivot) {
                index++;
            } else if (arr[index] < pivot) {
                swap(arr, index++, ++less);
            } else {
                swap(arr, index, --more);
            }
        }
        return new int[]{less + 1, more - 1};
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


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


    public static void main(String[] args) {
        int N = 100;
        for (int i = 0; i < 10000; i++) {
            int[] arr = randomArr(N);
            int[] arr2 = copyArray(arr);
            Arrays.sort(arr2);
            if (minKth(arr, 7) != arr2[6]) {
                System.out.println("error");
                return;
            }
        }
    }


    public static int[] randomArr(int N) {
        Random random = new Random();
        int size = random.nextInt(N) + 11;
        int big = random.nextInt(N) + 11;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(big);
        }
        return arr;
    }
}
