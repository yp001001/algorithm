package test2;

import java.util.PriorityQueue;

/**
 * 数据流的中位数
 */
public class MedianFinder {
    PriorityQueue<Integer> minQueue;    // 保存小于等于中位数的值
    PriorityQueue<Integer> maxQueue;    // 保存大于等于中位数的值

    public MedianFinder() {
        minQueue = new PriorityQueue<>((x, y) -> y - x);    // 最小的数据大于等于中位数
        maxQueue = new PriorityQueue<>((x, y) -> x - y);    // 最大的数据小于等于中位数
    }

    // minQueue.size() == maxQueue.size() / minQueue.size() = maxQueue.size() + 1
    public void addNum(int num) {
        // num < minQueue.peek() 表示小于等于中位数
        if (minQueue.isEmpty() || num < minQueue.peek()) {
            minQueue.offer(num);
            if (maxQueue.size() + 1 < minQueue.size()) {
                maxQueue.offer(minQueue.poll());
            }
        } else {
            maxQueue.offer(num);
            if (minQueue.size() < maxQueue.size()) {
                minQueue.offer(maxQueue.poll());
            }
        }
    }

    public double findMedian() {
        if (minQueue.size() > maxQueue.size()) {
            return minQueue.peek();
        } else {
            return ((double)minQueue.peek() + (double)maxQueue.peek()) / 2;
        }
    }
}
