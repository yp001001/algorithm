package test;

import java.util.*;

public class KthLargest {
    PriorityQueue<Integer> queue;
    int k;

    public KthLargest(int k, int[] nums) {
        queue = new PriorityQueue<>();
        this.k = k;
        for (int num : nums) {
            add(num);
        }
    }

    /**
     * 后面的数都比第一个数大，所以是第k大的数
     *
     * @param val
     * @return
     */
    public int add(int val) {
        queue.offer(val);
        if (queue.size() > k) {
            queue.poll();
        }
        return queue.peek();
    }
}
