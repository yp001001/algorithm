package topic.package08;

public class MinIndexEq {

    public static void main(String[] args) {
        System.out.println(containExactly("eidbaooo", "ab"));
    }

    public static int containExactly(String s1, String s2) {
        if (s1 == null || s2 == null) return 0;
        char[] str = s1.toCharArray();
        int M = s2.length();
        int[] count = new int[256];
        for (int i = 0; i < M; i++) {
            count[s2.charAt(i)]++;
        }
        int all = M;
        // 初始化窗口
        for (int i = 0; i < M; i++) {
            if (count[str[i]]-- > 0) all--;
        }

        for (int k = M; k < str.length; k++) {
            if (all == 0) return k - M;
            if (count[str[k]]-- > 0) all--;
            if (count[str[k - M]]++ >= 0) all++;
        }
        return all == 0 ? str.length - M : -1;
    }

}
