package test2;

import java.util.HashMap;
import java.util.Map;

public class Test20 {

    public static void main(String[] args) {
        String s = new Test20().intToRoman(8);
        System.out.println(s);
    }

    public String intToRoman_2(int num) {
        Map<Integer, Character> map = new HashMap<>();
        map.put(1, 'I');
        map.put(5, 'V');
        map.put(10, 'X');
        map.put(50, 'L');
        map.put(100, 'C');
        map.put(500, 'D');
        map.put(1000, 'M');
        StringBuilder sb = new StringBuilder();
        int count = 0;  // 记录位数
        while (num > 0) {
            int x = num % 10;
            int m = 1;
            for (int i = 0; i < count; i++) {
                m *= 10;
            }
            if (x == 0) {
                count++;
                num /= 10;
                continue;
            }
            ;
            if (x == 4) {
                sb.append(map.get(5 * m));
                sb.append(map.get(m));
            } else if (x == 9) {
                sb.append(map.get(10 * m));
                sb.append(m);
            } else if (x >= 5) {
                int c = x - 5;
                for (int i = 0; i < c; i++) {
                    sb.append(map.get(m));
                }
                sb.append(map.get(5 * m));
            } else {
                for (int i = 0; i < x; i++) {
                    sb.append(map.get(m));
                }
            }
            num /= 10;
            count++;
        }
        return sb.reverse().toString();
    }

    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public String intToRoman(int num) {
        StringBuilder roman = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            int value = values[i];
            String symbol = symbols[i];
            while (num >= value) {
                num -= value;
                roman.append(symbol);
            }
            if (num == 0) {
                break;
            }
        }
        return roman.toString();
    }

    String[] thousands = {"", "M", "MM", "MMM"};
    String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

    /**
     * 求三角形的最大面积
     */
    public double largestTriangleArea(int[][] points) {
        int maxArea = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = 0; k < points.length; k++) {
                    maxArea = Math.max(maxArea, area(i, points[i][0], j, points[j][1], k, points[k][0]));
                }
            }
        }
        return maxArea;
    }

    private int area(int indexI, int i, int indexJ, int j, int indexK, int k) {
        return -1;
    }
}
