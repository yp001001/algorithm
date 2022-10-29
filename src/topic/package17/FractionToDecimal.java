package topic.package17;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.cn/problems/fraction-to-recurring-decimal/
public class FractionToDecimal {

    public static void main(String[] args) {
        System.out.println(fractionToDecimal(1, Integer.MIN_VALUE));
    }

    public static String fractionToDecimal(int num, int den) {
        if (num == 0) return "0";
        StringBuilder res = new StringBuilder();
        if ((num < 0) ^ (den < 0)) {
            res.append("-");
        }
        long numerator = Math.abs((long) num);
        long denominator = Math.abs((long) den);
        Map<Long, Integer> map = new HashMap<>();
        res.append(numerator / denominator);
        numerator %= denominator;
        if (numerator == 0) return res.toString();
        res.append(".");
        map.put(numerator, res.length());
        while (numerator != 0) {
            numerator *= 10;
            res.append(numerator / denominator);
            numerator %= denominator;
            if (map.containsKey(numerator)) {
                int index = map.get(numerator);
                res.insert(index, "(");
                res.append(")");
                break;
            } else {
                map.put(numerator, res.length());
            }
        }
        return res.toString();
    }

}
