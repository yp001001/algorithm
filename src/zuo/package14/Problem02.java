package zuo.package14;

import java.util.List;
import java.util.Random;
import java.util.TreeSet;

public class Problem02 {


    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int[] arr = randomArr(100);
            int val = new Random().nextInt(100) + 7;
            if (max1(arr, val) != max3(arr, val)) {
                System.out.println("oops");
                return;
            }
        }
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


    // 给定非负数组arr，和一个正数m，返回arr所有子序列中累加和%m之后的最大值
    public static int max1(int[] arr, int m) {
        int N = arr.length;
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        boolean[][] dp = new boolean[N][sum + 1];
        for (int i = 0; i < N; i++) {
            dp[i][0] = true;
        }
        dp[0][arr[0]] = true;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= sum; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= arr[i])
                    dp[i][j] |= dp[i - 1][j - arr[i]];
            }
        }
        int max = 0;
        for (int i = 0; i <= sum; i++) {
            if (dp[N - 1][i])
                max = Math.max(max, i % m);
        }
        return max;
    }


    public static int max2(int[] arr, int m) {
        int N = arr.length;
        boolean[][] dp = new boolean[N][m];
        for (int i = 0; i < N; i++) {
            dp[i][0] = true;
        }
        dp[0][arr[0] % m] = true;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = dp[i - 1][j];
                int cur = arr[i] % m;
                if (j - cur >= 0) {
                    dp[i][j] |= dp[i - 1][j - cur];
                } else {
                    dp[i][j] |= dp[i - 1][m + j - cur];
                }
            }
        }
        int max = 0;
        for (int i = 0; i < m; i++) {
            if (dp[N - 1][i])
                max = i;
        }
        return max;
    }


    // 当动态规划规格太大，我们可以使用分治
    public static int max3(int[] arr, int m) {
        if (arr.length == 1) return arr[0] % m;
        int mid = (arr.length - 1) / 2;
        TreeSet<Integer> treeSet1 = new TreeSet<>();
        process(arr, 0, 0, mid, m, treeSet1);
        TreeSet<Integer> treeSet2 = new TreeSet<>();
        process(arr, mid + 1, 0, arr.length - 1, m, treeSet2);
        int ans = 0;
        for (Integer leftMod : treeSet1) {
            ans = Math.max(ans, leftMod + treeSet2.floor(m - 1 - leftMod));
        }
        return ans;
    }


    public static void process(int[] arr, int index, int sum, int end, int m, TreeSet<Integer> sortSet) {
        if (index > end) {
            sortSet.add(sum % m);
            return;
        }
        process(arr, index + 1, sum, end, m, sortSet);
        process(arr, index + 1, sum + arr[index], end, m, sortSet);
    }



    // 卡特兰数：k(0) = 1,k(1) = 1 k(0)*k(n-1)+k(1)*k(n-2) + ... + k(n-1)*k(0) (n≥2) = C2n^n - C2n^n-1


    public static String replaceWords(List<String> dictionary, String sentence) {
        Trie trie = new Trie();
        for (String d : dictionary) {
            trie.build(d);
        }
        StringBuilder sb = new StringBuilder();
        for (String sen : sentence.split("\\s+")) {
            String s = trie.find(sen);
            sb.append(s + " ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    static class Trie {
        Trie[] children;
        boolean bottom;

        public Trie() {
            children = new Trie[26];
            bottom = false;
        }

        public void build(String root) {
            Trie cur = this;
            for (int i = 0; i < root.length(); i++) {
                char c = root.charAt(i);
                int index = c - 'a';
                if (cur.children[index] == null) {
                    cur.children[index] = new Trie();
                }
                cur = cur.children[index];
                if (i == root.length() - 1) cur.bottom = true;
            }
        }


        public String find(String root) {
            Trie cur = this;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < root.length(); i++) {
                char c = root.charAt(i);
                int index = c - 'a';
                cur = cur.children[index];
                if (cur == null) {
                    break;
                }
                sb.append(c);
                if (cur.bottom == true) return sb.toString();
            }
            return root;
        }
    }
}
