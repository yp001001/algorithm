package everyday;

public class Test23 {

    public static void main(String[] args) {
        String reformat = reformat("a1b23");
        System.out.println(reformat);
    }


    public static String reformat(String s) {
        if (s.length() == 1) return s;
        int numCount = 0;
        int charCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) numCount++;
            else charCount++;
        }
        if (Math.abs(numCount - charCount) > 1) return "";
        char[] ans = new char[s.length()];
        int i = 0, j = 0;
        if (numCount > charCount) j++;
        else i++;
        for (int k = 0; k < s.length(); k++) {
            char c = s.charAt(k);
            if (Character.isDigit(c)) {
                ans[i] = c;
                i += 2;
            } else {
                ans[j] = c;
                j += 2;
            }
        }
        return String.valueOf(ans);
    }


    public static String reformat_low(String s) {
        if (s.length() == 1) return s;
        StringBuilder numSb = new StringBuilder();
        StringBuilder charSb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) numSb.append(c);
            else charSb.append(c);
        }
        int size = Math.abs(charSb.length() - numSb.length());
        if (size > 1) return "";
        StringBuilder res = new StringBuilder();
        String s1 = charSb.toString();
        String s2 = numSb.toString();
        int n1 = s1.length(), n2 = s2.length();
        for (int i = 0; i < Math.min(n1, n2); i++) {
            res.append(s1.charAt(i));
            res.append(s2.charAt(i));
        }
        if (n1 == n2) return res.toString();
        else if (n1 > n2) {
            res.append(s1.charAt(n1 - 1));
        } else {
            String string = res.toString();
            string = s2.charAt(n2 - 1) + string;
            return string;
        }
        return res.toString();
    }

}
