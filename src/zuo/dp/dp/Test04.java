package zuo.dp.dp;


// 组合字符
public class Test04 {

    public static int ways1(String number) {
        if (number == null || number.length() == 0) return 0;
        return process1(number.toCharArray(), 0);
    }

    private static int process1(char[] number, int index) {
        if (index == number.length) return 1;
        // i没到最后，说明有字符
        if (number[index] == '0') return 0;

        int ways = process1(number, index + 1);
        if (index < number.length - 1 && (number[index] - '0') * 10 + (number[index + 1] - '0') < 27) {
            ways += process1(number, index + 2);
        }
        return ways;
    }

    public static int ways2(String number) {
        char[] array = number.toCharArray();
        int N = number.length();
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            if (array[i] != '0') {
                int ways = dp[i + 1];
                if (i < N - 1 && (array[i] - '0') * 10 + (array[i + 1] - '0') < 27) {
                    ways += dp[i + 2];
                }
                dp[i] = ways;
            }
        }

        return dp[0];
    }


    public static void main(String[] args) {
        System.out.println(ways1("92917289461920191"));
        System.out.println(ways2("92917289461920191"));
    }
}
