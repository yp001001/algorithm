package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Test5 {


    /**
     * 全排列
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || len == 0) {
            return res;
        }
        boolean[] flag = new boolean[len];   // 用于判断该数是否已经被使用
        Deque<Integer> stack = new ArrayDeque<Integer>(); // 保存数
        dfs(res, nums, len, 0, stack, flag);
        return res;
    }

    /**
     * @param res
     * @param nums
     * @param len
     * @param path
     * @param stack
     * @param flag
     */
    private void dfs(List<List<Integer>> res, int[] nums, int len, int path, Deque<Integer> stack, boolean[] flag) {
        // NumberFormatException
        if (path == len) {
            res.add(new ArrayList(stack));
            return;
        }
        for (int i = 0; i < len; i++) {
            if (flag[i]) {
                continue;
            }
            stack.addLast(nums[i]);
            flag[i] = true;
            dfs(res, nums, len, path + 1, stack, flag);
            stack.removeLast();
            flag[i] = false; // 撤销
        }
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String c = br.readLine();
        String zans = br.readLine();
        int count = Integer.parseInt(c);
        String[] split = zans.split(" ");
        int[] nums = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            nums[i] = Integer.parseInt(split[i]);
        }
        // 打家劫舍
        int[] dp = new int[count];
        dp[0] = nums[0];
        System.out.println("nums[0]" + nums[0]);
        dp[1] = Math.max(nums[0], nums[1]);
        System.out.println("dp[1]" + dp[1]);
        for (int i = 2; i < count; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        System.out.println(dp[count - 1]);
    }

    /**
     * 小红书日志输出
     *
     * @throws IOException
     */
    private static void xiaoHongShuLog() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        char[] cs = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        int len = 0;
        while (len < cs.length) {
            char c = cs[len];
            if (c == '(') {
                while (cs[len] != ')') {
                    len++;
                }
//                len++; // 将')'排除
            } else if (c == '<') {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(c);
            }
            len++;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        StringBuilder reverse = sb.reverse();
        System.out.println(reverse.toString());
    }

    /**
     * 小红书动态规划
     *
     * @throws IOException
     */
    private static void xiaohongshu() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String[] split = s.split(" ");
        int money = Integer.parseInt(split[0]);
        int len = split.length;
        int[] goods = new int[len - 1];
        for (int i = 1; i < len; i++) {
            goods[i - 1] = Integer.parseInt(split[i]);
        }
        int[] m = new int[]{money};
        dfs(0, len - 1, m, goods);
        System.out.println(count);
    }

    private static void dfs(int path, int len, int[] m, int[] goods) {
        if (m[0] == 0) {
            count++;
            return;
        }
        if (m[0] < 0 || path >= len) {
            return;
        }
        m[0] -= goods[path];
        dfs(path, len, m, goods);
        m[0] += goods[path];
        dfs(path + 1, len, m, goods);
    }


    /**
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int row = grid.length;
        int column = grid[0].length;
        int[][] mixed = new int[row][column];
        mixed[0][0] = grid[0][0];
        for (int i = 1; i < row; i++) {
            mixed[i][0] = grid[i][0] + mixed[i - 1][0];
        }
        for (int i = 1; i < column; i++) {
            mixed[0][i] = grid[0][i] + mixed[0][i - 1];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                mixed[i][j] = Math.min(mixed[i - 1][j] + grid[i][j], mixed[i][j - 1] + grid[i][j]);
            }
        }
        return mixed[row - 1][column - 1];
    }


    /**
     * 约瑟夫环问题
     *
     * @param args
     * @throws IOException
     */

    private static class Yuesefu {
        private static void invoke() throws IOException {
            BufferedReader bd = new BufferedReader(new InputStreamReader(System.in));
            String s = bd.readLine();
            int sum = Integer.parseInt(s);
            boolean[] flag = new boolean[sum];
            int count = 0;
            int index = 0;
            int n = sum;
            while (sum > 1) {
                if (!flag[index]) {
                    count++;
                    if (count == 3) {
                        flag[index] = true;
                        sum--;
                        count = 0;
                    }
                }
                index++;
                if (index == n) {
                    index = 0;
                }
            }
            for (int i = 0; i < flag.length; i++) {
                if (!flag[i]) {
                    System.out.println(i + 1);
                    break;
                }
            }
        }
    }

    private static void ballHeight() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();    // 高度
        int count = Integer.parseInt(s);
        double countHeight = 0;
        double h = 100;
        while (count > 0) {
            countHeight += h; // 计算下来的高度
            h /= 2;
            count--;
            countHeight += h; // 计算上去的高度
        }
        countHeight -= h;
        System.out.println(countHeight + " " + h);
    }


}
