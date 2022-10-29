package everyday;

import java.util.ArrayDeque;

// https://leetcode.cn/problems/shortest-unsorted-continuous-subarray/
public class Test15 {

    public static void main(String[] args) {
        System.out.println(findUnsortedSubrray_low(new int[]{2, 6, 4, 8, 10, 9, 15}));
    }

    public static int findUnsortedSubrray(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        int max = arr[0];
        int leftIndex = -1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < max) leftIndex = i;
            else max = arr[i];
        }
        if (leftIndex == -1) return 0;
        int min = arr[arr.length - 1];
        int rightIndex = -1;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > min) {
                rightIndex = i;
            } else {
                min = arr[i];
            }
        }
        return Math.abs(leftIndex - rightIndex) + 1;
    }

    public static int findUnsortedSubrray_low(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int left = Integer.MAX_VALUE, right = -1;
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < arr.length; i++) {
            while (!deque.isEmpty() && arr[deque.peekLast()] > arr[i]) {
                left = Math.min(left, deque.pollLast());
            }
            deque.addLast(i);
        }
        if (left == Integer.MAX_VALUE) return 0;
        deque = new ArrayDeque<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!deque.isEmpty() && arr[deque.peekLast()] < arr[i]) {
                right = Math.max(right, deque.pollLast());
            }
            deque.addLast(i);
        }
        if (right == -1) return 0;
        return right - left + 1;
    }

}
