package test;

import java.util.Arrays;

/**
 * 数据流的第K大数值
 * 我们使用小顶堆（第K大），PriorityQueue就是基于堆
 * <p>
 * //        默认 compareTo 升序
 * //        PriorityQueue<Integer> queue = new PriorityQueue<>((x1, x2) -> {
 * //            int s = x1.compareTo(x2);
 * //            return s > 0 ? -1 : 1;
 * //        });
 */
public class KthLargest_2 {
    int[] nums;
    int k;

    public KthLargest_2(int k, int[] array) {
        this.k = k;
        this.nums = new int[k + 1];
//        reason: no instance(s) of type variable(s) T exist so that int[] conforms to T[]
        // 我们取出前k个元素保存到nums中
        Arrays.sort(array);
        int idx = 1;
        for (int i = array.length - 1; i >= array.length - k; i--) {
            nums[idx++] = array[i];
        }
        // 构造小顶堆
        for (int i = k / 2; i > 0; i--) {
            sink(nums, i, k);
        }
    }

    // nums[1] 最小

    public int add(int val) {
        if (val < nums[k]) return nums[1];
        else {
            nums[1] = val;
            sink(nums, 1, k);
            return nums[1];
        }
    }

    /**
     * 下沉，构造小顶堆
     *
     * @param nums
     * @param target
     * @param end
     */
    private void sink(int[] nums, int target, int end) {
        while (target * 2 <= end) {
            int max;
            if (target * 2 + 1 <= end) {
                max = nums[target * 2] < nums[target * 2 + 1] ? target * 2 : target * 2 + 1;
            } else {
                max = target * 2;
            }
            if (nums[max] > nums[target]) {
                break;
            } else {
                swap(nums, max, target);
            }
            target = max;
        }
    }

    private void swap(int[] nums, int a, int b) {
        int t = nums[a];
        nums[a] = nums[b];
        nums[b] = t;
    }
}
