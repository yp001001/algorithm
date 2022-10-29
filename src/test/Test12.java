package test;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * UNION UNIONALL 区别
 */
public class Test12 {

    /**
     * 打印九九乘法表
     */
    public static void print99() {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + "*" + i + "=" + i * j + "\t");
            }
            System.out.println();
        }
    }

    public String makeGood(String s) {
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (int i = 1; i < chars.length - 1; i++) {
            if (stack.isEmpty()) {
                stack.push(chars[i]);
            } else if ((stack.peek() ^ chars[i]) == 32) {
                stack.pop();
            } else {
                stack.push(chars[i]);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }


    public static void main(String[] args) throws IOException {
        char a = 'a';
        char A = 'A';
        // 小写字母 小 大写字母32
        System.out.println(A - a);

        print99();

    }

    public void quickSort(int[] nums, int left, int right) {
        if (left > right) {
            return;
        }
        int l = left;
        int r = right;
        int temp = nums[left];
        while (l < r) {
            while (l < r && nums[r] >= temp) {
                r--;
            }
            while (l < r && nums[l] <= temp) {
                l++;
            }
            if (l < r) {
                swap(nums, l, r);
            }
        }
        // 交换基准数
        swap(nums, left, r);
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
        quickSort(nums, left, r - 1);
        quickSort(nums, r + 1, right);
    }

    private void swap(int[] nums, int a, int b) {
        int t = nums[a];
        nums[a] = nums[b];
        nums[b] = t;
    }

    /**
     * 课程表（能否修完课程）
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    List<List<Integer>> res;
    boolean[] onStack;
    boolean[] marked;
    boolean noCycle = true;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        res = new ArrayList<>();
        marked = new boolean[numCourses];
        onStack = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            res.add(new ArrayList<>());
        }
        for (int[] p : prerequisites) {
            // A -> B
            res.get(p[0]).add(p[1]);
        }
        for (int i = 0; i < numCourses; i++) {
            if (!marked[i]) {
                dfs(i);
            }
        }
        return noCycle;
    }

    private void dfs(int path) {
        onStack[path] = true;
        marked[path] = true;
        for (Integer x : res.get(path)) {
            if (!marked[x]) {
                dfs(x);
            }
            if (onStack[x] == true) {
                noCycle = false;
                return;
            }
        }
        onStack[path] = false;
    }


    /**
     * 下降路径最小和
     *
     * @param matrix
     * @return
     */
    public static int minFallingPathSum(int[][] matrix) {
        int row = matrix.length;
        int column = matrix[0].length;
        int[][] dp = new int[row][column];
        for (int i = 0; i < column; i++) {
            dp[0][i] = matrix[0][i];
        }
        int step = Integer.MAX_VALUE;
        int sum = 0;
        if (column == 1) {
            for (int i = 0; i < row; i++) {
                sum += matrix[i][0];
            }
            return sum;
        }
        for (int i = 1; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (j == 0) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j + 1]) + matrix[i][j];
                } else if (j == column - 1) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + matrix[i][j];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i - 1][j + 1]) + matrix[i][j];
                }
            }
        }
        for (int i = 0; i < column; i++) {
            step = Math.min(step, dp[row - 1][i]);
        }
        return step;
    }


    /**
     * 三角形最小路径和（正上方的也可以选择）
     *
     * @param triangle
     * @return
     */
    public static int minimumTotal(List<List<Integer>> triangle) {
        int size = triangle.size();
        int[][] dp = new int[size][size];
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < size; i++) {
            dp[i][0] = triangle.get(i).get(0) + dp[i - 1][0];
        }
        for (int i = 1; i < size; i++) {
            for (int j = 1; j <= i; j++) {
                if (j < i) {
                    dp[i][j] = Math.min(dp[i - 1][j] + triangle.get(i).get(j), dp[i - 1][j - 1] + triangle.get(i).get(j));
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + triangle.get(i).get(j);
                }
            }
        }
        int step = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            step = Math.min(step, dp[size - 1][i]);
        }
        return step;
    }

    /**
     * 杨辉三角
     *
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> l = null;
        for (int i = 0; i < numRows; i++) {
            if (i > 0) {
                l = res.get(i - 1);
            }
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    list.add(1);
                } else {
                    list.add(l.get(j - 1) + l.get(j));
                }
            }
            res.add(list);
        }
        return res;
    }


    /**
     * 乘积为正数的最长子数组长度
     *
     * @param nums
     * @return
     */
    public static int getMaxLen(int[] nums) {
        int n = nums.length;
        int[] P = new int[n];
        int[] N = new int[n];
        int count = 0;
        if (nums[0] > 0) {
            count = 1;
        }
        if (nums[0] == 0) {
            P[0] = 0;
            N[0] = 0;
        } else {
            P[0] = nums[0] > 0 ? 1 : 0;
            N[0] = nums[0] > 0 ? 0 : 1;
        }
        for (int i = 1; i < n; i++) {
            if (nums[i] > 0) {
                P[i] = P[i - 1] + 1;
                if (N[i - 1] > 0) {
                    N[i] = N[i - 1] + 1;
                } else {
                    N[i] = 0;
                }
            } else if (nums[i] == 0) {
                N[i] = 0;
                P[i] = 0;
            } else {
                if (N[i - 1] > 0) {
                    P[i] = N[i - 1] + 1;
                } else {
                    P[i] = 0;
                }
                N[i] = P[i - 1] + 1;
            }
            count = Math.max(count, P[i]);
        }
        return count;
    }


    public boolean exist(char[][] board, String word) {
        char[] chars = word.toCharArray();
        int len = chars.length;
        // 记录该点是否被走过
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boolean[][] marked = new boolean[board.length][board[0].length];
                if (dfs(board, chars, 0, marked, i, j, len)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(char[][] board, char[] chars, int path, boolean[][] marked, int i, int j, int len) {
        if (i >= board.length || j >= board[0].length || i < 0 || j < 0 || marked[i][j] == true || board[i][j] != chars[path]) {
            return false;
        }
        if (path == len - 1) {
            return true;
        }
        marked[i][j] = true;
        boolean res = dfs(board, chars, path + 1, marked, i + 1, j, len) || dfs(board, chars, path + 1, marked, i, j + 1, len) || dfs(board, chars, path + 1, marked, i - 1, j, len) || dfs(board, chars, path + 1, marked, i, j - 1, len);
        marked[i][j] = false;   // 确实有问题
        return res;
    }


    public static int longestOnes(int[] nums, int k) {
        return maxOnes(nums, k);
    }

    public static int maxOnes(int[] nums, int k) {
        int n = nums.length;
        int l = 0, r = 0, max = 0, count = 0;
        while (r < n) {
            count += nums[r++] == 1 ? 0 : 1;
            while (count > k) {
                count -= nums[l++] == 1 ? 1 : 0;
            }
            max = Math.max(max, r - l);
        }
        return max;
    }

    public int maxConsecutiveChar(String answerKey, char ch, int sum) {
        int n = answerKey.length();
        int l = 0, r = 0, max = 0;
        int count = 0;
        while (r < n) {
            count += answerKey.charAt(r++) != ch ? 1 : 0;
            while (sum < count) {
                count -= answerKey.charAt(l++) == ch ? 0 : 1;
            }
            max = Math.max(max, r - l);
        }
        return max;
    }


    int l, r = 0;

    int n = 10;
    static int m = 20;

    static {
        System.out.println("test12");
    }

    static class A {
        int mmm = 0;
        static int mm = 11;

        static {
            System.out.println("A");
        }
    }


    /**
     * 11ac2b3c
     *
     * @param s
     * @return
     */
    public static String jiemi(String s) {
        StringBuilder sb = new StringBuilder();
        String x = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                x += c;
            } else {
                int count = Integer.parseInt(x);
                for (int j = 0; j < count; j++) {
                    sb.append(c);
                }
                x = "";
            }
        }
        return sb.toString();
    }
}

class BA {
    public void test() throws IOException, Exception {

    }
}

class BAA extends BA {
    public void test() throws Exception {

    }
}
