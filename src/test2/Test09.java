package test2;

import java.util.*;

public class Test09 {

    static private double t = 0.00;
    static private int[] flag;
    static private double res = -1000; // 0.00只能过80%
    static private List<Integer> result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            double[][] nums = new double[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    nums[j][j] = sc.nextDouble();
                }
            }
            solution(n, nums);
        }
    }

    static void solution(int n, double[][] nums) {
        result = new ArrayList<>();
        flag = new int[n];
        for (int i = 0; i < n; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(i + 1);
            flag[i] = 1;
            t = nums[0][i];
            backtracking(nums, list, t, 1);
            flag[i] = 0;
            t = 0;
        }
        System.out.println(String.format("%.2f", res));
        for (int i = 0; i < result.size(); ++i) {
            System.out.println((i + 1) + " " + result.get(i));
        }
    }


    static void backtracking(double[][] nums, List<Integer> list, double t, int end) {
        int n = nums.length;
        if (end == nums.length) {
            if (res < t) {
                res = t;
                result = new ArrayList<>(list);
            }
        }
        for (int i = 0; i < n; i++) {
            if (flag[i] != 1) {
                t += nums[end][i];
                list.add(i + 1);
                backtracking(nums, list, t, end + 1);
                list.remove(list.size() - 1);
                t = nums[end][i];
                flag[i] = 0;
            }
        }
    }


    public static void main4(String[] args) {
        Scanner sc = new Scanner(System.in);
        String p = sc.next();
        String s = sc.next();
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) dp[0][i] = dp[0][i - 1] && p.charAt(i - 1) == '*';
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        System.out.println((dp[m][n] ? 1 : 0));
    }


    /**
     * 判断是否是boolean值
     *
     * @param s
     * @return
     */
    public static boolean isBoolean(String s) {
        if ("false".equals(s) || "true".equals(s)) {
            return true;
        }
        return false;
    }

    public static void main3(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] ans = sc.nextLine().split(" ");
        int n = ans.length;
        if (!isBoolean(ans[0]) || !isBoolean(ans[n - 1])) {
            System.out.println("error");
        } else {
            Stack<String> stack = new Stack<String>();
            for (int i = 0; i < n; i++) {
                if (stack.isEmpty()) {
                    stack.push(ans[i]);
                } else if (isBoolean(ans[i])) {
                    // 如果栈的前面一个也是boolean类型，error
                    if (isBoolean(stack.peek())) {
                        System.out.println("error");
                        return;
                    } else {
                        stack.push(ans[i]);
                    }
                } else {
                    // 如果栈的前一个也是and or，error
                    if (!isBoolean(stack.peek())) {
                        System.out.println("error");
                        return;
                    } else if ("and".equals(ans[i])) {
                        String pop = stack.pop();
                        if ("false".equals(pop) || "false".equals(ans[i + 1])) {
                            stack.push("false");
                        } else {
                            stack.push("true");
                        }
                        i++;
                    } else {
                        stack.push(ans[i]);
                    }
                }
            }
            // 此时栈中只剩下 true,false,or（只要有true就表示真）
            while (!stack.isEmpty()) {
                String pop = stack.pop();
                if ("true".equals(pop)) {
                    System.out.println("true");
                    return;
                }
                if (stack.isEmpty()) {
                    System.out.println("false");
                }
            }
        }
    }

    /**
     * 还原数列
     *
     * @param args
     */
    public static void main2(String[] args) {
        long result = 0;
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        long[] res = new long[n];
        for (int i = 0; i < n; i++) {
            res[i] = scan.nextLong();
        }
        Arrays.sort(res);
        while (res[n - 1] >= n) {
            result += res[n - 1] / n;
            for (int i = 0; i < n - 1; i++) {
                res[i] += res[n - 1] / n;
            }
            res[n - 1] = res[n - 1] % n;
            Arrays.sort(res);
        }
        System.out.println(result);
        scan.close();
    }
}
