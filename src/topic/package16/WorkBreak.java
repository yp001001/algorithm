package topic.package16;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WorkBreak {

    boolean answer = false;

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        process(s, wordSet);
        return answer;
    }

    private void process(String s, Set<String> wordSet) {
        if (answer) return;
        for (int i = 0; i < s.length(); i++) {
            if (wordSet.contains(s)) answer = true;
            if (wordSet.contains(s.substring(0, i + 1))) {
                String t = s.substring(i + 1);
                process(t, wordSet);
            }
        }
    }

    // 优化
    public boolean dp(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                }
            }
        }
        return dp[n];
    }

}
