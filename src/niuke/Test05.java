package niuke;

import java.util.Scanner;

public class Test05 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0;i < n;i++){
            arr[i] = sc.nextInt();
        }
        int[] dp = new int[n];
        dp[0] = arr[0];
        int res = dp[0];
        for(int i = 1;i < n;i++){
            dp[i] = Math.max(dp[i-1] + arr[i],arr[i]);
            res = Math.max(res,dp[i]);
        }
        System.out.print(res);
    }

    // 3 5 2 4 2 1 3 3 5 3 7 5 9 1 10
    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] desk = new int[n];
        for (int i = 0; i < n; i++) {
            desk[i] = sc.nextInt();
        }
        int[] number = new int[m];
        int[] consumer = new int[m];
        for (int i = 0; i < m; i++) {
            number[i] = sc.nextInt();
            consumer[i] = sc.nextInt();
        }
        System.out.println(maxMoney(desk, number, consumer));
    }


    /**
     * @param desk    下标对应的数字表示该桌子能够坐下多少人       （容量）
     * @param number  表示这一批次有多少人坐                    （体积）
     * @param consume 表示消费的金额                           （价值）
     * @return
     */
    public static int maxMoney(int[] desk, int[] number, int[] consume) {
        marked = new boolean[number.length];
        return process(desk, number, consume, 0);
    }

    static boolean[] marked;

    private static int process(int[] desk, int[] number, int[] consume, int index) {
        if (index == desk.length) return 0;
        int maxValue = 0;
        for (int i = 0; i < number.length; i++) {
            if (marked[i] || desk[index] < number[i]) continue;
            marked[i] = true;
            maxValue = process(desk, number, consume, index + 1) + consume[i];
            marked[i] = false;
        }
        return maxValue;
    }


}
