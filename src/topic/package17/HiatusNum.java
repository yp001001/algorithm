package topic.package17;

import java.util.ArrayList;
import java.util.List;

// 163 -> 缺失的数字
public class HiatusNum {

    public static List<String> HiatusNum(int[] arr, int lower, int upper) {
        if (arr == null || lower > upper) return null;
        List<String> res = new ArrayList<String>();
        for (int a : arr) {
            if (lower < a) {
                res.add(miss(lower, a - 1));
            }
            if (a == upper) break;
            lower = a + 1;
        }
        return res;
    }

    public static String miss(int lower, int upper) {
        StringBuilder sb = new StringBuilder();
        for (int i = lower; i <= upper; i++) {
            sb.append(i + "-");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
