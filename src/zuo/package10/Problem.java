package zuo.package10;

import java.util.Random;
import java.util.Stack;

public class Problem {

    public static int max(int[] arr) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int minNum = Integer.MAX_VALUE;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                    minNum = Math.min(minNum, arr[k]);
                }
                max = Math.max(max, minNum * sum);
            }
        }
        return max;
    }

    // 给定正数数组，求子数组累加和乘以子数组最小值的最大值
    /* 求出每个数作为子数组中最小值即可 */
    public static int maxValue(int[] arr) {
        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = arr[i] + sum[i - 1];
        }
        int maxValue = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                Integer me = stack.pop();   // pop的是Val，然后栈的前一个就是我们达不到的位置
                int value = stack.isEmpty() ? sum[i - 1] : sum[i - 1] - sum[stack.peek()];
                maxValue = Math.max(maxValue, value * arr[me]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer me = stack.pop();
            int value = stack.isEmpty() ? sum[arr.length - 1] : sum[arr.length - 1] - sum[stack.peek()];
            maxValue = Math.max(maxValue, value * arr[me]);
        }
        return maxValue;
    }

    public static int[] randomArr(int N) {
        Random random = new Random();
        int size = random.nextInt(N) + 1;
        int big = random.nextInt(N) + 1;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(big);
        }
        return arr;
    }

    /* 二维数组最大矩阵面积 */
    public static int maximalRectangle(char[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0) return 0;
        int maxArea = 0;
        int[] height = new int[map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                height[j] = map[i][j] == '0' ? 0 : height[j] + 1;
            }
            maxArea = Math.max(maxRecfromBottom(height), maxArea);
        }
        return maxArea;
    }

    public static int maxRecfromBottom(int[] height) {
        int size = height.length;
        Stack<Integer> stack = new Stack<>();
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] >= height[i]) {
                Integer me = stack.pop();
                int value = i - 1 - (stack.isEmpty() ? 0 : stack.peek());
                ans = Math.max(value * height[me], ans);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer me = stack.pop();
            int value = size - 1 - (stack.isEmpty() ? 0 : stack.peek());
            ans = Math.max(value * height[me], ans);
        }
        return ans;
    }


    /* 二维数组矩阵个数 */
    public static int numSubmat(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) return 0;
        int nums = 0;
        int[] height = new int[mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                height[j] = mat[i][j] == 0 ? 0 : height[j] + 1;
            }
            nums += countFromBottom(height);
        }
        return nums;
    }

    private static int countFromBottom(int[] height) {
        if (height == null || height.length == 0) return 0;
        int nums = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] >= height[i]) {
                int cur = stack.pop();
                // 判断两数是否相等，相等不进行操作
                if (height[cur] > height[i]) {
                    // 找到小于cur的前一个小数，没有则是-1
                    int left = stack.isEmpty() ? -1 : stack.peek();
                    // 得到 n
                    int n = i - left - 1;
                    // 得到数组大小差
                    int down = Math.max(left == -1 ? 0 : height[left], height[i]);
                    nums += (height[cur] - down) * num(n);
                }
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer cur = stack.pop();
            int left = stack.isEmpty() ? -1 : stack.peek();
            int n = height.length - 1 - left;
            int down = left == -1 ? 0 : height[left];
            nums += (height[cur] - down) * num(n);
        }
        return nums;
    }

    private static int num(int n) {
        return ((n * (1 + n))) >> 1;
    }

}
