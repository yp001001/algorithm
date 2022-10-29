package everyday;

public class Test27 {

    public static void main(String[] args) {
        int maxScore = maxScore("01001");
        System.out.println(maxScore);
    }

    public static int maxScore(String s) {
        if (s == null || s.length() <= 1) return 0;
        char[] str = s.toCharArray();
        int n = str.length;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = str[0] == '0' ? 1 : 0;
        for (int i = 1; i < n - 1; i++) {
            left[i] = left[i - 1] + (str[i] == '0' ? 1 : 0);
        }
        right[n - 1] = str[n - 1] == '1' ? 1 : 0;
        for (int i = n - 2; i > 0; i--) {
            right[i] = right[i + 1] + (str[i] == '1' ? 1 : 0);
        }
        int ans = left[0];
        for (int i = 1; i <= n - 1; i++) {
            if (ans < left[i - 1] + right[i]) {
                ans = left[i - 1] + right[i];
            }
        }
        return ans;
    }

}
