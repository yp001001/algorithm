package topic.package04;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 数组为{3，2，2，3，1}，查询为（0，3，2）
// 表示在下标为0-3之间有多少个2，查询非常频繁
public class Problem01 {


    public static class QueryBox1 {
        private int[] arr;

        public QueryBox1(int[] array) {
            arr = new int[array.length];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = array[i];
            }
        }

        public int query(int L, int R, int v) {
            int ans = 0;
            for (; L <= R; L++) {
                if (arr[L] == v) {
                    ans++;
                }
            }
            return ans;
        }
    }

    public static class QueryBox2 {
        Map<Integer, List<Integer>> dict;

        public QueryBox2(int[] arr) {
            dict = new HashMap<>();
            for (int i = 0; i < arr.length; i++) {
                if (!dict.containsKey(arr[i])) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    dict.put(arr[i], list);
                } else {
                    dict.get(arr[i]).add(i);
                }
            }
        }

        public int query(int L, int R, int v) {
            if (!dict.containsKey(v)) {
                return 0;
            }
            List<Integer> list = dict.get(v);
            int left = countLess(list, L);
            int right = countLess(list, R + 1);
            return right - left;
        }


        // 在有序数组arr中，用二分的方法数出<limit的数有几个
        // 也就是用二分法，找到<limit的数中最右的位置
        private int countLess(List<Integer> arr, int limit) {
            int l = 0, r = arr.size() - 1;
            int ans = -1;
            while (l <= r) {
                int mid = (l + r) >> 1;
                if (arr.get(mid) < limit) {
                    ans = mid;
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            return ans + 1;
        }
    }


    public static int[] generateRandomArray(int len, int value) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * value) + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        int len = 300;
        int value = 20;
        int testTimes = 100000;
        int queryTimes = 1000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomArray(len, value);
            int N = arr.length;
            QueryBox1 box1 = new QueryBox1(arr);
            QueryBox2 box2 = new QueryBox2(arr);
            for (int j = 0; j < queryTimes; j++) {
                int a = (int) (Math.random() * N);
                int b = (int) (Math.random() * N);
                int L = Math.min(a, b);
                int R = Math.max(a, b);
                int v = (int) (Math.random() * value) + 1;
                if (box1.query(L, R, v) != box2.query(L, R, v)) {
                    System.out.println("Oops!");
                }
            }
        }
        System.out.println("test end");
    }

}
