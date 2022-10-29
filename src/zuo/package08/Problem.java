package zuo.package08;

import java.util.LinkedList;

public class Problem {


    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || arr.length < w || w < 1) {
            return null;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int R = 0; R < arr.length; R++) {
            while (!queue.isEmpty() && arr[queue.peekLast()] <= arr[R]) {
                queue.removeLast();
            }
            queue.addLast(arr[R]);
            if (R - queue.peekFirst() == w) {
                queue.removeFirst();
            }
            if (R >= w - 1) {
                res[index++] = arr[queue.peekFirst()];
            }
        }
        return res;
    }
}
