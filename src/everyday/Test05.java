package everyday;

// https://leetcode.cn/problems/fraction-addition-and-subtraction/
public class Test05 {


    public static void main(String[] args) {
        System.out.println(fractionAddition("1/2"));
    }

    public static String fractionAddition(String expression) {
        if (expression == null || expression.length() == 0) return "";
        long denominator = 0, numerator = 1; // 分子，分母
        int index = 0, n = expression.length();
        while (index < n) {
            long dedenominator1 = 0, numerator1 = 0, sign = 1;
            if (expression.charAt(index) == '+' || expression.charAt(index) == '-') {
                sign = expression.charAt(index) == '-' ? -1 : 1;
                index++;
            }
            // 求出分子
            while (index < n && Character.isDigit(expression.charAt(index))) {
                dedenominator1 = dedenominator1 * 10 + expression.charAt(index) - '0';
                index++;
            }
            index++;
            // 求出分母
            while (index < n && Character.isDigit(expression.charAt(index))) {
                numerator1 = numerator1 * 10 + expression.charAt(index) - '0';
                index++;
            }
            dedenominator1 = (sign == 1 ? dedenominator1 : -dedenominator1);
            denominator = denominator * numerator1 + dedenominator1 * numerator;
            numerator = numerator * numerator1;
        }
        if (denominator == 0) return "0/1";
        long g = gcb(Math.abs(denominator), numerator);
        return Long.toString(denominator / g) + "/" + Long.toString(numerator / g);
    }

    // 求最大公约数
    public static long gcb(long a, long b) {
        long remainder = a % b;
        while (remainder != 0) {
            a = b;
            b = remainder;
            remainder = a % b;
        }
        return b;
    }


}
