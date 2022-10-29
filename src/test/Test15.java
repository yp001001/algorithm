package test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Test15 {

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new HashMap<>();
        int len = arr1.length;
        for (int i = 0; i < len; i++) {
            map.put(arr1[i], map.getOrDefault(arr1[i], 0) + 1);
        }
        int[] n = new int[len];
        int idx = 0;
        for (int i = 0; i < arr2.length; i++) {
            int count = map.get(arr2[i]);
            while (count > 0) {
                n[idx++] = arr2[i];
            }
            map.remove(arr2[i]);
        }
        Set<Integer> set = map.keySet();
        Iterator itr = set.iterator();
        while (itr.hasNext()) {
            int k = (Integer) itr.next();
            int v = map.get(k);
            while (v > 0) {
                n[idx++] = k;
                v--;
            }
        }
        return n;
    }


    public static void main(String[] args) {
        int[] n = countSort(new int[]{2, 4, 1, 2, 5, 3, 4, 8, 7});
        for (int i : n) {
            System.out.print(i + "\t");
        }
    }

    /***
     * 计数排序
     * @param nums
     */
    public static int[] countSort(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = num > max ? num : max;
        }
        // 计数数组
        int[] t = new int[max + 1];
        for (int i = 0; i <= max; i++) {
            t[nums[i]] += 1;    // 计数
        }
        // 累积数组，放置的是位置（并不一定需要，只要idx计数就行）
        for (int i = 1; i <= max; i++) {
            t[i] += t[i - 1]; // 累积数组
        }
        // 最终结果
        int len = nums.length;
        int[] n = new int[len];
        for (int i = 0; i < len; i++) {
            int idx = t[nums[i]] - 1;
            t[nums[i]] -= 1;
            n[idx] = nums[i];
        }
        return n;
    }
}
