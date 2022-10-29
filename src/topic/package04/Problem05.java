package topic.package04;

// 字符串交织
public class Problem05 {

    public static boolean isInterleave2(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        char[] str3 = s3.toCharArray();
        if (str3.length != str1.length + str2.length) {
            return false;
        }
        boolean[][] dp = new boolean[str1.length + 1][str2.length + 1];
        dp[0][0] = true;
        for (int i = 1; i <= str1.length; i++) {
            if (str1[i - 1] != str3[i - 1]) {
                break;
            }
            dp[i][0] = true;
        }
        for (int j = 1; j <= str2.length; j++) {
            if (str2[j - 1] != str3[j - 1]) {
                break;
            }
            dp[0][j] = true;
        }
        for (int i = 1; i <= str1.length; i++) {
            for (int j = 1; j <= str2.length; j++) {
                if ((str1[i - 1] == str3[i + j - 1] && dp[i - 1][j]) ||
                                (str2[j - 1] == str3[i + j - 1] && dp[i][j - 1])) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[str1.length][str2.length];
    }


    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        if (s1.length() + s2.length() != s3.length()) return false;
        int N = s1.length();
        int M = s2.length();
        boolean[][] dp = new boolean[N + 1][M + 1];
        dp[0][0] = true;
        for (int i = 1; i <= N; i++) {
            if (s1.charAt(i - 1) != s3.charAt(i - 1)) break;
            dp[i][0] = true;
        }
        for (int i = 1; i <= M; i++) {
            if (s2.charAt(i - 1) != s3.charAt(i - 1)) break;
            dp[0][i] = true;
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                dp[i][j] =
                        (s1.charAt(i - 1) == s3.charAt(i + j - 1) && dp[i - 1][j])
                                ||
                                (s2.charAt(j - 1) == s3.charAt(i + j - 1) && dp[i][j - 1]);
            }
        }
        return dp[N][M];
    }
}
