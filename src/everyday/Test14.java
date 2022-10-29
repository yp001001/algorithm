package everyday;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.cn/problems/minimum-subsequence-in-non-increasing-order/
public class Test14 {

    public static void main(String[] args) {
        Test14 test14 = new Test14();
        List<Integer> avg = test14.minSubsequence(new int[]{2, 3, 4, 5, 6});
        for (Integer a : avg) {
            System.out.println(a);
        }
    }

    public List<Integer> minSubsequence(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        Arrays.sort(nums);
        int cur = 0;
        List<Integer> ans = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            cur += nums[i];
            ans.add(nums[i]);
            if (sum - cur < cur) break;
        }
        return ans;
    }

}
