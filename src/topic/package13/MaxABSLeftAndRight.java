package topic.package13;

import java.util.HashSet;
import java.util.Set;

/* 得到该数组的最大可整合数组长度(子数组) */
public class MaxABSLeftAndRight {

    public static void main(String[] args) {
        int[] arr = new int[]{4, 2, 1, 3, 5, 6, 5};
        System.out.println(maxABS(arr));
    }

    public static int maxABS(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        Set<Integer> set = new HashSet<>();
        int n = arr.length;
        int ans = 0;
        for (int i = 0; i < n - 1; i++) {
            set.clear();
            int max = arr[i], min = arr[i];
            set.add(arr[i]);
            for (int j = i + 1; j < n; j++) {
                if (set.contains(arr[j])) break;
                max = Math.max(arr[j], max);
                min = Math.min(arr[j], min);
                set.add(arr[j]);
                if (max - min == j - i) {
                    ans = Math.max(ans, max - min + 1);
                }
            }
        }
        return ans;
    }

}
