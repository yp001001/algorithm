package zuo.package14;


public class Problem05 {

    // 求子数组平均值小于等于v的最长子数组长度
    public static int maxAvgLengthAwesome1(int[] arr, int k) {
        if (arr == null || arr.length == 0) return 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] -= k;
        }
        return maxLength(arr, 0);
    }

    // 找到累加和小于等于k的最长子数组
    private static int maxLength(int[] arr, int k) {
        if (arr == null || arr.length == 0) return 0;
        int N = arr.length;
        int[] sums = new int[N];
        int[] ends = new int[N];
        sums[N - 1] = arr[N - 1];
        ends[N - 1] = N - 1;
        for (int i = N - 2; i >= 0; i--) {
            if (sums[i + 1] > 0) {
                sums[i] = arr[i];
                ends[i] = i;
            } else {
                sums[i] = arr[i] + sums[i + 1];
                ends[i] = ends[i + 1];
            }
        }
        int end = 0;
        int sum = 0;
        int ans = 0;
        for (int i = 0; i < N; i++) {
            while (end < N && sum <= k) {
                sum += sums[i];
                end = ends[i];
            }
            ans = Math.max(ans, end - i);
            if (end > i) {
                sum -= arr[i];
            } else {
                end++;
            }
        }
        return ans;
    }

    // 暴力解，时间复杂度O(N^3)，用于做对数器
    public static int maxAvgLengthAwesome2(int[] arr, int v) {
        int ans = 0;
        for (int L = 0; L < arr.length; L++) {
            for (int R = L; R < arr.length; R++) {
                int sum = 0;
                int k = R - L + 1;
                for (int i = L; i <= R; i++) {
                    sum += arr[i];
                }
                double avg = (double) sum / (double) k;
                if (avg <= v) {
                    ans = Math.max(ans, k);
                }
            }
        }
        return ans;
    }


    // 用于测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * maxLen) + 1;
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = (int) (Math.random() * maxValue);
        }
        return ans;
    }

    // 用于测试
    public static int[] copyArray(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    // 用于测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 用于测试
    public static void main(String[] args) {
        System.out.println("测试开始");
        int maxLen = 20;
        int maxValue = 100;
        int testTime = 500000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int value = (int) (Math.random() * maxValue);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            int ans1 = maxAvgLengthAwesome1(arr1, value);
            int ans2 = maxAvgLengthAwesome2(arr2, value);
            if (ans1 != ans2) {
                System.out.println("测试出错！");
                System.out.print("测试数组：");
                printArray(arr);
                System.out.println("子数组平均值不小于 ：" + value);
                System.out.println("方法1得到的最大长度：" + ans1);
                System.out.println("方法2得到的最大长度：" + ans2);
                System.out.println("=========================");
            }
        }
        System.out.println("测试结束");
    }
}
