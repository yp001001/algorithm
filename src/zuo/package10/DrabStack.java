package zuo.package10;

import java.util.*;

// 每个最小值作为基准数
public class DrabStack {


    // 求数组中某个元素前面及后面小于该数的第一个值
    public static int[][] nums(int[] arr) {
        Deque<Integer> frontDeque = new ArrayDeque<>();
        Deque<Integer> lastDeque = new ArrayDeque<>();
        int[][] ans = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            // 保存大值，小值不去掉，下一个小值弹出大值
            while (!frontDeque.isEmpty() && frontDeque.peekLast() >= arr[i]) {
                frontDeque.removeLast();
            }
            if (frontDeque.isEmpty()) {
                ans[i][0] = -1;
            } else {
                ans[i][0] = frontDeque.peekLast();
            }
            frontDeque.addLast(arr[i]);
        }
        //72, 18, 50, 63, 8, 75, 33, 35, 4, 49, 80, 47, 49, 70, 33, 25, 65, 86, 13, 16, 31
        for (int i = arr.length - 1; i >= 0; i--) {
            // 保存大值，小值不去掉，下一个小值弹出大值
            while (!lastDeque.isEmpty() && lastDeque.peekLast() >= arr[i]) {
                lastDeque.removeLast();
            }
            if (lastDeque.isEmpty()) {
                ans[i][1] = -1;
            } else {
                ans[i][1] = lastDeque.peekLast();
            }
            lastDeque.addLast(arr[i]);
        }
        return ans;
    }


    public static int[][] getNearLess(int[] arr) {
        int[][] res = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                List<Integer> list = stack.pop();
                int preIdx = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                for (Integer x : list) {
                    res[x][0] = preIdx == -1 ? -1 : arr[preIdx];
                    res[x][1] = arr[i];
                }
            }
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(Integer.valueOf(i));
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }
        while (!stack.isEmpty()) {
            List<Integer> list = stack.pop();
            Integer preIdx = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            for (Integer x : list) {
                res[x][0] = preIdx == -1 ? -1 : arr[preIdx];
                res[x][1] = -1;
            }
        }
        return res;
    }


    public static void logarithm(int N, int M) {
        for (int i = 0; i < N; i++) {
            int[] arr = randomArr(M);
            int[][] num1 = getNearLess(arr);
            int[][] num2 = nums(arr);
            if (!Arrays.equals(num1[0], num2[0])) {
                System.out.println(Arrays.toString(arr));
                System.out.println("========================");
                System.out.println(Arrays.toString(num1[0]));
                System.out.println("========================");
                System.out.println(Arrays.toString(num2[0]));
                System.out.println("error");
                return;
            }
        }
    }


    /* 求数组中每个子数组中最小值的累加和 */
    public static int sumSubarrayMins(int[] arr) {
        int[] left = nearLessEqualLeft(arr);
        int[] right = nearLessEqualRight(arr);
        long ans = 0;
        for (int i = 0; i < arr.length; i++) {
            long start = i - left[i];
            long end = right[i] - i;
            ans += start * end * arr[i];
            ans %= 100000007;
        }
        return (int) ans;
    }

    private static int[] nearLessEqualLeft(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] left = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                left[stack.pop()] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            left[stack.pop()] = -1;
        }
        return left;
    }

    private static int[] nearLessEqualRight(int[] arr) {
        int[] right = new int[arr.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                right[stack.pop()] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            right[stack.pop()] = arr.length;
        }
        return right;
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


    public static void main(String[] args) {
        System.out.println(sumSubarrayMins(new int[]{3, 1, 2, 4}));
    }
}
