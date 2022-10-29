package everyday;


public class Test22 {

    public static void main(String[] args) {
        System.out.println(solveEquation("x+2x-3=x+2"));
    }

    public static String solveEquation(String equation) {
        String[] strs = equation.split("=");
        int left = 0;
        int right = 0;
        String[] str1 = strs[0].replace("-", "+-").split("\\+");
        String[] str2 = strs[1].replace("-", "+-").split("\\+");

        // 等式左边的处理
        for (String s : str1) {
            if (s.equals("x")) {
                left++;
            } else if (s.equals("-x")) {
                left--;
            } else if (s.contains("x")) {
                left += Integer.parseInt(s.substring(0, s.length() - 1));
            } else if (!s.equals("")) {
                right -= Integer.parseInt(s);
            }
        }

        for (String s : str2) {
            if (s.equals("x")) {
                left--;
            } else if (s.equals("-x")) {
                left++;
            } else if (s.contains("x")) {
                left -= Integer.parseInt(s.substring(0, s.length() - 1));
            } else if (!s.equals("")) {
                right += Integer.parseInt(s);
            }
        }
        if (left == 0) {
            if (right == 0) {
                return "Infinite solutions";
            } else {
                return "No solution";
            }
        } else {
            return "x=" + right / left;
        }
    }
}
