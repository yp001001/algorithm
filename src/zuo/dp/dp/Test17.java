package zuo.dp.dp;

public class Test17 {

    public static int ways(int n) {
        if (n < 1) return 0;
        int[] record = new int[n];
        return process(0, record, n);
    }

    // 当前来到第i行，一共0 - N-1行
    // 在i行上放皇后，所有列都尝试
    private static int process(int i, int[] record, int n) {
        if (i == n) return 1;
        int res = 0;
        for (int j = 0; j < n; j++) {
            if (isValid(record, i, j)) {
                record[i] = j;
                res += process(i + 1, record, n);
            }
        }
        return res;
    }

    public static boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) return false;
        }
        return true;
    }


    public static int ways2(int n) {
        if (n < 1) return 0;
        int[] record = new int[n];
        return process2(0, record, n);
    }

    private static int process2(int i, int[] record, int n) {
        if (i == n) return 1;
        int res = 0;
        for (int j = 0; j < n; j++) {
            if (isValid2(i, j, record)) {
                record[i] = j;
                res += process2(i + 1, record, n);
            }
        }
        return res;
    }

    private static boolean isValid2(int i, int j, int[] record) {
        for (int x = 0; x < i; x++) {
            if (record[x] == j || Math.abs(x - i) == Math.abs(record[x] - j)) return false;
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(ways(8));
        System.out.println(ways2(8));
    }
}
