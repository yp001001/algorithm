package com.test.num;

import java.util.HashMap;

public class TwoSum2 {


    // 使用Hash表
    public static int[] twoSum(int[] nums, int target) {
        int[] answer = new int[2];
        // 存储下标和元素的补位
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                answer[0] = map.get(nums[i]);
                answer[1] = i;
            }
            // 保存补位和下标
            map.put(target - nums[i], i);
        }
        return answer;
    }
}
