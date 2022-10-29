package topic.package37;

// https://leetcode.cn/problems/decode-string/
public class DecodeString {

    public static String decodeString(String s) {
        return process(s.toCharArray(), 0).ans;
    }

    private static Info process(char[] str, int i) {
        StringBuilder sb = new StringBuilder();
        int time = 0;
        while (i < str.length && str[i] != ']') {
            if (str[i] >= 'a' && str[i] <= 'z' || str[i] >= 'A' && str[i] <= 'Z') {
                sb.append(str[i++]);
            } else if (str[i] >= '0' && str[i] <= '9') {
                time = time * 10 + str[i++] - '0';
            } else {
                Info info = process(str, i + 1);
                i = info.stop + 1;
                sb.append(timeString(time, info.ans));
                time = 0;
            }
        }
        return new Info(sb.toString(), i);
    }

    public static String timeString(int times, String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

    public static class Info {
        String ans;
        int stop;

        Info(String a, int e) {
            ans = a;
            stop = e;
        }
    }

//    public static class Info {
//        public String ans;
//        public int stop;
//
//        public Info(String a, int e) {
//            ans = a;
//            stop = e;
//        }
//    }
//
//    // s[i...] 何时停？ 遇到 ']' 或者遇到s的终止位置，停止
//    public static Info process(char[] s, int i) {
//        StringBuilder ans = new StringBuilder();
//        int times = 0;
//        while (i < s.length && s[i] != ']') {
//            if ((s[i] >= 'a' && s[i] <= 'z') || s[i] >= 'A' && s[i] <= 'Z') {
//                ans.append(s[i++]);
//            } else if (s[i] >= '0' && s[i] <= 9) {
//                times = times * 10 + s[i++] - '0';
//            } else {
//                Info next = process(s, i + 1);
//                ans.append(timeString(times, next.ans));
//                times = 0;
//                i = next.stop + 1;
//            }
//        }
//        return new Info(ans.toString(), i);
//    }

}
