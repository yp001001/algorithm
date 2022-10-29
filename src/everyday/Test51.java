package everyday;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.cn/problems/contiguous-array/
// 连续数组
public class Test51 {

    public static void main(String[] args) {
        System.out.println(findMaxLength(new int[]{0, 1}));
    }

    // 前缀和问题
    public static int findMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] prefixNum = new int[nums.length];
        prefixNum[0] = nums[0] == 0 ? -1 : 1;
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        map.put(prefixNum[0], 0);
        for (int i = 1; i < nums.length; i++) {
            prefixNum[i] = prefixNum[i - 1] + (nums[i] == 0 ? -1 : 1);
            if (map.containsKey(prefixNum[i])) {
                ans = Math.max(ans, i - map.get(prefixNum[i]));
            } else {
                map.put(prefixNum[i], i);
            }
        }
        return ans;
    }

}
