package everyday;


import java.util.HashMap;
import java.util.Map;

// https://leetcode.cn/problems/largest-component-size-by-common-factor/
public class Test09 {

    public static void main(String[] args) {
        System.out.println(largestComponentSize(new int[]{20, 50, 9, 63}));
    }

    public static int largestComponentSize(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        UnionSet unionSet = new UnionSet(nums);
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int limit = (int) Math.sqrt(nums[i]);
            for (int j = 1; j <= limit; j++) {
                if (nums[i] % j == 0) {
                    if (j != 1) {
                        if (!map.containsKey(j)) {
                            map.put(j, i);
                        } else {
                            unionSet.union(i, map.get(j));
                        }
                    }
                    int other = nums[i] / j;
                    if (other != 1) {
                        if (!map.containsKey(other)) {
                            map.put(other, i);
                        } else {
                            unionSet.union(map.get(other), i);
                        }
                    }
                }
            }
        }
        return unionSet.size();
    }

    public static int gcb(int a, int b) {
        int remainder = a % b;
        while (remainder != 0) {
            a = b;
            b = remainder;
            remainder = a % b;
        }
        return b;
    }

    static class UnionSet {

        int[] parent;
        int[] size;
        int[] help;
        int ans = 1;

        public UnionSet(int[] nums) {
            int n = nums.length;
            parent = new int[n];
            size = new int[n];
            help = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }


        public int find(int index) {
            int p = 0;
            while (index != parent[index]) {
                help[p++] = index;
                index = parent[index];
            }
            for (int i = 0; i < p; i++) {
                parent[help[i]] = index;
            }
            return index;
        }

        public void union(int a, int b) {
            int f1 = find(a);
            int f2 = find(b);
            if (f1 != f2) {
                int size1 = size[f1];
                int size2 = size[f2];
                if (size1 > size2) {
                    size[f1] += size[f2];
                    parent[f2] = parent[f1];
                    ans = Math.max(parent[f2], ans);
                } else {
                    size[f2] += size[f1];
                    parent[f1] = parent[f2];
                    ans = Math.max(parent[f1], ans);
                }
            }
        }

        public int size() {
            int ans = 0;
            for (int x : size) {
                ans = Math.max(x, ans);
            }
            return ans;
        }


    }

}
