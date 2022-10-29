package niuke;

import java.util.ArrayList;
import java.util.List;

public class Test04 {
    static List<String> res = new ArrayList<>();

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        backtrack(1, 23, sb);
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }
    }

    /**
     * path = 1,表示轮到A拿，2表示轮到B拿
     * num 代表剩余石子数量
     **/
    public static void backtrack(int path, int num, StringBuilder sb) {
        // 表示此时轮到B拿，表示上一把A拿的是最后一块石头
        if (path == 2 && num == 0) {
            res.add(sb.toString());
            return;
        }
        if (num <= 0) return;
        if (path == 1) {
            sb.append("1");
            backtrack(2, num - 1, sb);
            sb.deleteCharAt(sb.length() - 1);

            sb.append("2");
            backtrack(2, num - 2, sb);
            sb.deleteCharAt(sb.length() - 1);

            sb.append("3");
            backtrack(2, num - 3, sb);
            sb.deleteCharAt(sb.length() - 1);

            sb.append("4");
            backtrack(2, num - 4, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (path == 2) {
            sb.append("1");
            backtrack(1, num - 1, sb);
            sb.deleteCharAt(sb.length() - 1);

            sb.append("2");
            backtrack(1, num - 2, sb);
            sb.deleteCharAt(sb.length() - 1);

            sb.append("3");
            backtrack(1, num - 3, sb);
            sb.deleteCharAt(sb.length() - 1);

            sb.append("4");
            backtrack(1, num - 4, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
