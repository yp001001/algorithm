package everyday;

// https://leetcode.cn/problems/crawler-log-folder/
public class Test48 {

    public static void main(String[] args) {
        minOperations(new String[]{"d1/","d2/","../","d21/","./"});
    }

    public static int minOperations(String[] logs) {
        if (logs == null || logs.length == 0) return 0;
        int depth = 0;
        for (String log : logs) {
            if ("../".equals(log)) {
                depth = depth == 0 ? 0 : --depth;
            } else if ("./".equals(log)) {

            } else {
                depth++;
            }
        }
        return depth;
    }

}
