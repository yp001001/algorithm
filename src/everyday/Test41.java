package everyday;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// https://leetcode.cn/problems/final-prices-with-a-special-discount-in-a-shop/
public class Test41 {

    public static void main(String[] args) {
        int[] ans = finalPrices(new int[]{8, 4, 6, 2, 3});
        System.out.println(Arrays.toString(ans));
    }

    // 求元素下标右边小于该数的最小下标
    public static int[] finalPrices(int[] prices) {
        if (prices == null || prices.length == 0) return null;
        Deque<Integer> deque = new ArrayDeque<>();
        int[] nums = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            while (!deque.isEmpty() && prices[deque.peekLast()] >= prices[i]) {
                int index = deque.pollLast();
                nums[index] = i;
            }
            deque.offerLast(i);
        }
        while (!deque.isEmpty()) {
            nums[deque.pollLast()] = -1;
        }
        int[] ans = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            if (nums[i] != -1) {
                ans[i] = prices[i] - prices[nums[i]];
            } else {
                ans[i] = prices[i];
            }
        }
        return ans;
    }

}
