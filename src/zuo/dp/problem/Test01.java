package zuo.dp.problem;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Test01 {

    public static void main(String[] args) {
//        int[][] trips = new int[][]{{2, 1, 5}, {3, 5, 7}};
//        Problem01 test01 = new Problem01();
//        test01.carPooling(trips, 3);
        System.out.println(65532 / 3);
    }


    //=======================================差分数组============================

    public int[] getModifiedArray(int length, int[][] updates) {
        return null;
    }


    //=======================================航班预定统计============================

    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] diff = new int[n];
        for (int i = 0; i < bookings.length; i++) {
            int a = bookings[i][0], b = bookings[i][1];
            diff[a - 1] += bookings[i][2];
            if (b < n) {
                diff[b] -= bookings[i][2];
            }
        }

        // 将数组还原
        int[] res = new int[n];
        res[0] = diff[0];
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] + diff[i];
        }
        return res;
    }

    //=======================================优势洗牌============================

    public int[] advantageCount(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        PriorityQueue<int[]> queue = new PriorityQueue<>((p1, p2) -> {
            return p2[1] - p1[1];
        });
        int n = nums2.length;
        for (int i = 0; i < n; i++) {
            queue.add(new int[]{i, nums2[i]});
        }
        int lo = 0, hi = n - 1;
        int[] ans = new int[n];
        while (!queue.isEmpty()) {
            int[] v = queue.poll();
            if (v[1] >= nums1[hi]) {
                ans[v[0]] = nums1[lo++];
            } else {
                ans[v[0]] = nums1[hi];
            }
        }
        return ans;
    }


    //=======================================拼车（差分数组）============================
    public boolean carPooling(int[][] trips, int capacity) {
        int[] num = new int[1001];
        for (int i = 0; i < trips.length; i++) {
            int a = trips[i][0], b = trips[i][1], c = trips[i][2];
            num[b] += a;
            num[c] -= a;
        }
        int cap = 0;
        for (int c : num) {
            cap += c;
            if (cap > capacity) {
                return false;
            }
        }
        return true;
    }
}