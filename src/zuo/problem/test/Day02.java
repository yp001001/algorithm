package zuo.problem.test;

public class Day02 {

    public static void main(String[] args) {
        Day02 test = new Day02();
        int reverse = test.reverse(-2147483412);
        System.out.println(reverse);
//        System.out.println(-2143847412 > Integer.MIN_VALUE);
        // -2147483648
        // -2143847412
    }

    public int strStr(String haystack, String needle) {
        int len = needle.length();
        if (len == 0) return 0;
        if (len > haystack.length()) return -1;
        String res = haystack.substring(0, len);
        if (res.equals(needle)) return 0;
        for (int i = 1; i <= haystack.length() - len; i++) {
            res = haystack.substring(i, len + i);
            if (res.equals(needle)) return i;
        }
        return -1;
    }

    public int reverse(int x) {
        String value = String.valueOf(x);
        boolean flag = false;
        int ans = 0;
        char c = value.charAt(0);
        int i = 0;
        if (c == '-') {
            i++;
            flag = true;
        }
        String sub = value.substring(i, value.length());
        String reverse = reverse(sub, 0, sub.length() - 1);
        if (flag) {
            String min = String.valueOf(Integer.MIN_VALUE);
            int len = min.length();
            min = min.substring(1, len);
            if (valid(reverse, min)) return 0;
        } else {
            String max = String.valueOf(Integer.MAX_VALUE);
            if (valid(reverse, max)) return 0;
        }
        ans = Integer.parseInt(reverse);
        return flag ? ans * -1 : ans;
    }

    public boolean valid(String a, String b) {
        if (a.length() < b.length()) return false;
        if (a.length() > b.length()) return true;
        for (int i = 0; i < a.length(); i++) {
            int k = (a.charAt(i) - '0') - (b.charAt(i) - '0');
            if (k < 0) break;
            else if (k > 0) return true;
        }
        return false;
    }

    public String reverse(String s, int start, int end) {
        char[] arr = new char[end - start + 1];
        for (int i = start, j = end; i <= j; i++, j--) {
            arr[i] = s.charAt(j);
            arr[j] = s.charAt(i);
        }
        return new String(arr);
    }
}
