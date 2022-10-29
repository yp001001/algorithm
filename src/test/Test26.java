package test;

import java.util.*;

public class Test26 {
    public static void main(String[] args) {
        Test26 test26 = new Test26();
        int[][] res = test26.merge(new int[][]{{1, 4}, {5, 6}});
        for (int[] re : res) {
            System.out.println(re[0] + "\t" + re[1]);
        }
        Set<Integer> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        for (int integer : set) {

        }
    }

    /**
     * 合并区间
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        // 自定义排序
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, (x1, x2) -> {
            return x1[0] - x2[0];
        });
        List<int[]> merged = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int L = intervals[i][0], R = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }


    /**
     * 不能重叠（错误...）
     *
     * @param intervals
     * @return
     */
    public int[][] merge_error(int[][] intervals) {
        // 最小值大于下一个的最大值
        BitSet bitSet = new BitSet();
        for (int i = 0; i < intervals.length; i++) {
            bitSet.set(intervals[i][0], intervals[i][1] + 1, true);
        }
        int max = 10000;
        Map<Integer, Integer> map = new HashMap<>();
        int start = -1;
        for (int i = 0; i <= max; i++) {
            if (bitSet.get(i)) {
                if (start == -1) {
                    start = i;
                }
            }
            if (!bitSet.get(i)) {
                if (start != -1) {
                    map.put(start, i - 1);
                    start = -1;
                }
            }
        }
        int[][] res = new int[map.size()][2];
        int idx = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            res[idx][0] = entry.getKey();
            res[idx][1] = entry.getValue();
            idx++;
        }
        return res;
    }


    public int[] twoSum_2(int[] numbers, int target) {
        // 双指针
        int l = 0;
        int r = numbers.length - 1;
        // 因为有序
        while (l < r) {
            int sum = numbers[l] + numbers[r];
            if (sum < target) {
                l++;
            } else if (sum > target) {
                r--;
            } else {
                return new int[]{l + 1, r + 1};
            }
        }
        return null;
    }

    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int idx = binarySearch(numbers, target - numbers[i]);
            if (idx != -1 && idx != i) {
                int[] res = new int[]{idx + 1, i + 1};
                Arrays.sort(res);
                return res;
            }
        }
        return null;
    }

    private int binarySearch(int[] numbers, int target) {
        int l = 0;
        int r = numbers.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (numbers[mid] > target) {
                r = mid - 1;
            } else if (numbers[mid] < target) {
                l = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums1) {
            set.add(num);
        }
        Set<Integer> set2 = new HashSet<>();
        for (int num : nums2) {
            if (set.contains(num)) set2.add(num);
        }
        int[] res = new int[set2.size()];
        Iterator iterator = set2.iterator();
        int idx = 0;
        while (iterator.hasNext()) {
            res[idx++] = (int) iterator.next();
        }
        return res;
    }

    /**
     * 电话号码的组合关系
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        if (digits.length() == 0) {
            return list;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        StringBuilder sb = new StringBuilder();
        dfs(digits, phoneMap, list, 0, sb);
        return list;
    }

    private void dfs(String digits, Map<Character, String> phoneMap, List<String> list, int index, StringBuilder sb) {
        if (index == digits.length()) {
            list.add(sb.toString());
            return;
        }
        String str = phoneMap.get(digits.charAt(index));
        for (int i = 0; i < str.length(); i++) {
            sb.append(str.charAt(i));
            dfs(digits, phoneMap, list, index + 1, sb);
            sb.deleteCharAt(index);
        }
    }


    /**
     * 在排序数组中查找第一个位置和最后一个位置
     * *****两次二分，先查找左边的，然后查找右边的
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        return null;
    }

    public int getNumber(TreeSet root, int[][] ops) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < ops.length; i++) {
            int a = ops[i][0];
            int b = ops[i][1];
            int c = ops[i][2];
            map.put(b, a);
            map.put(c, a);
        }
        return -1;
    }
}
