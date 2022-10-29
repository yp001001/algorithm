package everyday;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

// https://leetcode.cn/problems/find-k-closest-elements/
public class Test31 {

    public static void main(String[] args) {
        int index = binarySearch(new int[]{1, 2, 3, 4, 6, 7}, 5);
        System.out.println(index);
    }


    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        if (arr == null || arr.length == 0) return null;
        PriorityQueue<Integer> queue = new PriorityQueue<>((x1, x2) -> {
            int a = Math.abs(x1 - x);
            int b = Math.abs(x2 - x);
            if (a == b) return x2 - x1;
            return b - a;
        });
        for (int a : arr) {
            queue.offer(a);
            if (queue.size() > k) queue.poll();
        }
        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            res.add(queue.poll());
        }
        Collections.sort(res);
        return res;
    }

    public static List<Integer> findClosestElements2(int[] arr, int k, int x) {
        int right = binarySearch(arr, x);
        int left = right - 1;
        while (k-- > 0) {
            if (left < 0) {
                right++;
            } else if (right >= arr.length) {
                left--;
            } else if (x - arr[left] <= arr[right] - x) {
                left--;
            } else {
                right++;
            }
        }
        List<Integer> ans = new ArrayList<Integer>();
        for (int i = left + 1; i < right; i++) {
            ans.add(arr[i]);
        }
        return ans;
    }


    // 找到数组中第一个 >= target的数
    public static int binarySearch(int[] arr, int target) {
        if (arr == null) return -1;
        int l = 0, r = arr.length - 1, ans = r;
        while (l <= r) {
            int mid = (r - l) / 2 + l;
            if (arr[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
                ans = mid;
            }
        }
        return ans;
    }

}
