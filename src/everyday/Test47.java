package everyday;


// https://leetcode.cn/problems/rearrange-spaces-between-words/
public class Test47 {

    public static void main(String[] args) {
        String s = reorderSpaces("  this   is  a sentence ");
        System.out.println(s);
    }

    public static String reorderSpaces(String text) {
        if (text == null || text.length() == 0) return text;
        int n = text.length();
        text = text.trim();
        String[] sp = text.split("\\s+");
        int trimCount = 0;
        for (int i = 0; i < sp.length; i++) {
            for (int j = 0; j < sp[i].length(); j++) {
                trimCount++;
            }
        }
        trimCount = n - trimCount;
        int c1 = 0;
        int c2 = 0;
        if (sp.length > 1) {
            c1 = trimCount / (sp.length - 1);
            c2 = trimCount % (sp.length - 1);
        }
        if (sp.length == 1) c2 = trimCount;
        StringBuilder sb = new StringBuilder();
        String trim = "";
        for (int i = 0; i < c1; i++) {
            trim += " ";
        }
        for (int i = 0; i < sp.length; i++) {
            sb.append(sp[i]);
            if (i < sp.length - 1) {
                sb.append(trim);
            }
        }
        for (int i = 0; i < c2; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

}
