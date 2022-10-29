package topic.package18;

import java.util.HashMap;
import java.util.TreeSet;

public class MinimumDifference {

    public int minimumDifference(int[] nums) {
        int half = nums.length >> 1;
        HashMap<Integer, TreeSet<Integer>> lmap = new HashMap<>();
        HashMap<Integer, TreeSet<Integer>> rmap = new HashMap<>();
        process(nums, 0, half, 0, 0, lmap);
        process(nums, half, nums.length, 0, 0, rmap);
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int ans = Integer.MAX_VALUE;
        for (int leftNum : lmap.keySet()) {
            for (int leftSum : lmap.get(leftNum)) {
                Integer rightSum = rmap.get(half - leftNum).floor((sum >> 1) - leftSum);
                if (rightSum != null) {
                    int pickSum = leftSum + rightSum;
                    int restSum = sum - pickSum;
                    ans = Math.min(ans, restSum - pickSum);
                }
            }
        }
        return ans;
    }

    /*
        pick：挑选了几个数
        map：记录挑选的数与大小
     */
    public static void process(int[] arr, int index, int end, int pick, int sum, HashMap<Integer, TreeSet<Integer>> map) {
        if (index == end) {
            if (!map.containsKey(pick)) {
                map.put(pick, new TreeSet<>());
            }
            map.get(pick).add(sum);
        } else {
            process(arr, index + 1, end, pick, sum, map);
            process(arr, index + 1, end, pick + 1, sum + arr[index], map);
        }
    }

}
