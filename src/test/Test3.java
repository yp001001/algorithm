package test;

//import java.util.*;
//import java.util.concurrent.TimeUnit;Line 7: error: cannot find symbol [in __Driver__.java]
//        int ret = new Solution().minSubArrayLen(param_1, param_2);
//        ^
//        symbol:   method minSubArrayLen(int,int[])
//        location: class Solution
//
//import java.util.*;
//import java.util.concurrent.TimeUnit;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Test3 {

    public ListNode sortList(ListNode head) {
        ListNode temp = head;
        ListNode result = new ListNode(Integer.MIN_VALUE);
        ListNode n = result;
        while (temp != null) {
            while (n.next != null && n.next.val <= temp.val) {
                n = n.next;
            }
            ListNode next = temp.next;
            temp.next = n.next;
            n.next = temp;
            temp = next;
            if (temp != null && temp.val <= n.next.val) {
                n = result;
            } else {
                n = n.next;
            }
        }
        return result.next;
    }

    /**
     * 链表指定范围反转
     *
     * @param head
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode temp = new ListNode(-1);
        temp.next = head;
        ListNode prev = temp;
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }
        ListNode last = prev;
        for (int i = 0; i < right - left + 1; i++) {
            last = last.next;
        }
        ListNode result = prev.next;
        ListNode fast = last.next;
        prev.next = null;
        last.next = null;
        ListNode n = reverse(result);
        prev.next = n;
        while (n.next != null) {
            n = n.next;
        }
        n.next = fast;
        return temp.next;
    }

    private ListNode reverse(ListNode node) {
        ListNode temp = new ListNode();
        ListNode t = temp;
        while (node != null) {
            ListNode next = node.next;
            node.next = t.next;
            t.next = node;
            node = next;
        }
        return temp.next;
    }

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        // 滑动窗口
        int slow = 0;
        int fast = 0;
        int sum = 1;
        int min = Integer.MAX_VALUE;
        while (fast < nums.length) {
            sum *= nums[fast];
            while (sum >= k) {
                min = Math.min(min, fast - slow + 1);
                sum /= nums[slow];
                if (slow == fast) {
                    return 1;
                }
                slow++;
            }
            fast++;
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }


    public static int findMaxLength4(int[] nums) {
        int count = 0;
        int sum = 0; // 如果和为0则说明这一段是连续的
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i] == 0 ? -1 : 1;
            sum += x;
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            } else {
                count = Math.max(count, i - map.get(sum));
            }
        }
        return count;
    }

    public boolean winnerOfGame(String colors) {
        if (colors.length() < 3) {
            return false;
        }
        // 计算AAA或者BBB的个数
        int count = 0;
        for (int i = 0; i < colors.length() - 2; i++) {
            if (colors.substring(i, i + 2).equals("AAA")) count++;
            if (colors.substring(i, i + 2).equals("BBB")) count--;
        }
        return count > 0;
    }

    boolean[][] marked;  // 标记是否已经寻找过
    int column;
    int row;
    char[] chars;
    char[][] result;

    public boolean exist(char[][] board, String word) {
        this.row = board.length;
        this.column = board[0].length;
        this.marked = new boolean[row][column];
        this.result = board;
        this.chars = word.toCharArray();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (valid(i, j, 0)) return true;
            }
        }
        return false;
    }

    public boolean valid(int i, int j, int index) {
        if (index >= chars.length) {
            return true;
        }
        if (i < 0 || j < 0 || i >= row || j >= column || result[i][j] != chars[index] || marked[i][j]) {
            return false;
        }
        marked[i][j] = true;

        boolean flag = valid(i + 1, j, index + 1) || valid(i, j + 1, index + 1)
                || valid(i - 1, j, index + 1) || valid(i, j - 1, index + 1);
        marked[i][j] = false;
        return flag;
    }

    public static void main(String[] args) throws InterruptedException {
//        Test3 test3 = new Test3();
//        ListNode root = new ListNode(4);
//        root.next = new ListNode(2);
//        root.next.next = new ListNode(1);
//        root.next.next.next = new ListNode(3);
////        root.next.next.next.next = new ListNode(5);
//        ListNode node = test3.sortList(root);

        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(5);
        list.add(1);
        System.out.println(list);
    }

    public int minSubArrayLen3(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int[] sums = new int[n + 1];
        // 为了方便计算，令 size = n + 1
        // sums[0] = 0 意味着前 0 个元素的前缀和为 0
        // sums[1] = A[0] 前 1 个元素的前缀和为 A[0]
        // 以此类推
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        for (int i = 1; i <= n; i++) {
            int target = s + sums[i - 1];
            int bound = Arrays.binarySearch(sums, target); //-6
            if (bound < 0) {
                bound = -bound - 1;
            }
            if (bound <= n) {
                ans = Math.min(ans, bound - (i - 1));
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    public int minSubArrayLen2(int target, int[] nums) {
        // 使用双指针
        int slow = 0;
        int fast = 0;
        int len = nums.length;
        int min = 0;
        int sum = 0;
        while (fast < len) {
            sum += nums[fast];
            if (target <= sum) {
                if (min == 0) {
                    min = fast - slow + 1;
                } else {
                    min = Math.min(min, fast - slow + 1);
                }
                if (slow == fast) {
                    fast++;
                } else {
                    sum -= nums[slow];
                    sum -= nums[fast];
                    slow++;
                }
            } else {
                fast++;
            }
        }
        return min;
    }

    public int minSubArrayLen(int target, int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int sum = 0;
        int count = 0;
        for (int i = len - 1; i >= 0; i--) {
            sum += nums[i];
            count++;
            if (target <= sum) {
                return count;
            }
        }
        return 0;
    }

    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        movePlant(A.size(), A, B, C);
    }

    public void movePlant(int size, List<Integer> start, List<Integer> auxiliary, List<Integer> target) {
        if (size == 1) {
            target.add(start.remove(start.size() - 1));
            return;
        }
        movePlant(size - 1, start, target, auxiliary);
        // 递归到最后一个
        target.add(start.remove(start.size() - 1));
        movePlant(size - 1, auxiliary, start, target);
    }

    public static int findMaxLength2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int prefixSum = 0;
        int res = 0;
        int j;
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i] == 0 ? -1 : 1;
            if (map.containsKey(prefixSum)) {
                j = map.get(prefixSum);
                res = Math.max(res, i - j);
            } else
                map.put(prefixSum, i);
        }
        return res;
    }

    public static int findMaxLength(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int oneCount = 0;
            int zeroCount = 0;
            int n = i;
            while (n < nums.length && (nums[n] == 1 || nums[n] == 0)) {
                if (nums[n] == 0) {
                    zeroCount++;
                } else {
                    oneCount++;
                }
                n++;
                if (oneCount == zeroCount) {
                    max = max > oneCount * 2 ? max : oneCount * 2;
                }
            }
        }
        return max;
    }


    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return result;
        }
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int len = nums.length;
        // a+b+c=0  => b+c=-a;

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {      // 去重
                continue;
            }
            int prev = i + 1;
            int last = len - 1;
            while (last > prev) {
                sum = nums[prev] + nums[last] + nums[i];
                if (sum > 0) {
                    last--;
                } else if (sum < 0) {
                    prev++;
                } else {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[i]);
                    list.add(nums[prev]);
                    list.add(nums[last]);
                    result.add(list);
                    int p = nums[prev];
                    while (nums[++prev] == p && prev < last) {
                    }
                }
            }
        }
        return result;
    }


    private static void test() throws InterruptedException {
        Runner one = new Runner();
        Thread countThread = new Thread(one, "CountThread");
        countThread.start();
        TimeUnit.SECONDS.sleep(1);
        countThread.interrupt();
        Runner two = new Runner();
        countThread = new Thread(two, "CountThread");
        countThread.start();
        TimeUnit.SECONDS.sleep(1);
        two.cancel();
    }

    private static class Runner implements Runnable {
        private long i;
        private volatile boolean on = true;

        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println("Count i = " + i);
        }

        public void cancel() {
            on = false;
        }
    }
}
