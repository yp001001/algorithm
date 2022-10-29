package test;

import java.util.*;

public class Test18 {

    public static void main(String[] args) {
        Test18 test18 = new Test18();
        int max = test18.findMaxLength(new int[]{0, 1, 1, 0, 1, 1, 1, 0});
        System.out.println(max);
    }

    /**
     * 求0与1相同个数的子数组 （相似题目：和为k的子数组个数）
     *
     * @param nums
     * @return
     */
    public int findMaxLength(int[] nums) {
        // 假设0为-1，相等数都能用0-1来形容？？？
        int max = 0;
        int sum = 0;
        int m;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            m = nums[i] == 0 ? -1 : 1;
            // 如果前面的数与当前数相等，说明中间就是和为0
            sum += m;
            if (map.containsKey(sum)) {
                max = Math.max(i - map.get(sum), max);
            } else {
                map.put(sum, i);
            }
        }
        return max;
    }

    /**
     * 单词长度的最大乘积（不含重复，使用 & |）
     *
     * @param words
     * @return
     */
    public int maxProduct(String[] words) {
        int len = words.length;
        int[] mask = new int[len];      // 保存二进制位，用来比较两个字符串是否有相同
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                mask[i] |= (1 << (words[i].charAt(j) - 'a'));      // 将二进制保存到数组中
            }
        }

        int max = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if ((mask[i] & mask[j]) == 0) max = Math.max(max, words[i].length() * words[j].length());
            }
        }
        return max;
    }

    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        for (Map.Entry<Integer, Integer> entry : entries) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return -1;
    }

    /**
     * 把数字翻译成字符串
     *
     * @param num
     * @return
     */
    public int translateNum(int num) {
        String s = num + "";
        int len = s.length();
        if (len < 2) {
            return 1;
        }
        int[] dp = new int[len + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= len; i++) {
            // 判断当前数+前一个数是否能组成一个小于25的数
            if (s.charAt(i - 2) == '0' || (s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') > 25)
                dp[i] = dp[i - 1];
            else dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[len];
    }

    public int translateNum2(int num) {
        String s = String.valueOf(num);
        int len = s.length();
        if (len < 2) {
            return 1;
        }
        int[] dp = new int[len + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= len; i++) {
            // 判断当前数+前一个数是否能组成一个小于25的数
            if (s.charAt(i - 2) == '0' || (s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') > 25)
                dp[i] = dp[i - 1];
            else dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[len];
    }


    /**
     * 使用归并排序
     *
     * @param nums
     */
    public void mergeSort(int[] nums) {
        int[] temp = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1, temp);
    }

    private void mergeSort(int[] nums, int left, int right, int[] temp) {
        if (left >= right) return;
        int mid = (left + right) >>> 1;
        mergeSort(nums, left, mid, temp);
        mergeSort(nums, mid + 1, right, temp);
        merge(nums, left, mid, right, temp);
    }

    public void merge(int[] nums, int left, int mid, int right, int[] temp) {
        int l = left;
        int idx = 0;
        int x = mid + 1;
        while (l <= mid && x <= right) {
            if (nums[l] < nums[x]) {
                temp[idx++] = nums[l++];
            } else {
                temp[idx++] = nums[x++];
            }
        }
        while (l <= mid) {
            temp[idx++] = nums[l++];
        }
        while (x <= right) {
            temp[idx++] = nums[x++];
        }
        // 将数据还原
        idx = 0;
        while (left <= right) {
            nums[left++] = temp[idx++];
        }
    }


    /**
     * 合并k个有序链表（使用归并）
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }
        if (left > right) {
            return null;
        }
        int mid = (left + right) >>> 1;
        return mergeTwoLists(merge(lists, left, mid), merge(lists, mid + 1, right));
    }


//    常规方法
//    public ListNode mergeKLists(ListNode[] lists) {
//        ListNode ans = null;
//        for (int i = 0; i < lists.length; i++) {
//            ans = mergeTwoLists(ans, lists[i]);
//        }
//        return ans;
//    }

    private ListNode mergeTwoLists(ListNode ans, ListNode node) {
        if (ans == null || node == null) {
            return ans == null ? node : ans;
        }
        ListNode res = new ListNode();
        ListNode temp = res;
        while (ans != null && node != null) {
            if (ans.val < node.val) {
                temp.next = ans;
                ans = ans.next;
            } else {
                temp.next = node;
                node = node.next;
            }
            temp = temp.next;
        }
        temp.next = ans == null ? node : ans;
        return res.next;
    }

    /**
     * 有效三角形的个数
     *
     * @param nums
     * @return
     */
    public int triangleNumber(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int left = j + 1;       // 标记左边的下标
                int right = len - 1;    // 标记右边的下标
                int k = j;
                // 利用二分
                while (left <= right) {
                    int mid = (left + right) >>> 1;
                    if (nums[mid] < nums[i] + nums[j]) {
                        k = mid;
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                count += (k - j);
            }
        }
        return count;
    }
}
