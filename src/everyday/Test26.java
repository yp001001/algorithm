package everyday;

import java.util.ArrayDeque;

// https://leetcode.cn/problems/max-chunks-to-make-sorted-ii/
public class Test26 {

    public static void main(String[] args) {
        int[] arr = new int[]{4, 2, 2, 1, 1};
        maxChunksToSorted(arr);
    }


    public static int maxChunksToSorted(int[] arr) {
        if (arr == null || arr.length <= 1) return 1;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int num : arr) {
            if (stack.isEmpty() || num >= stack.peek()) {
                stack.push(num);
            } else {
                int mx = stack.pop();
                while (!stack.isEmpty() && stack.peek() > num) {
                    stack.pop();
                }
                stack.push(mx);
            }
        }
        return stack.size();
    }

}
