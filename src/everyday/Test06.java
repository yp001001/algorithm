package everyday;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://leetcode.cn/problems/rank-transform-of-an-array/
public class Test06 {

    public static void main(String[] args) {
        arrayRankTransform(new int[]{1, 1, 2});
    }

    public static int[] arrayRankTransform(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        int[] sortArr = Arrays.copyOfRange(arr, 0, arr.length);
        Arrays.sort(sortArr);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < sortArr.length; i++) {
            map.put(sortArr[i], map.getOrDefault(sortArr[i], map.size() + 1));
        }
        int[] ans = new int[arr.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = map.get(arr[i]);
        }
        return ans;
    }

}
