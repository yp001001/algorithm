package everyday;

// https://leetcode.cn/problems/rotated-digits/
public class Test63 {

    public static void main(String[] args) {
        System.out.println(rotatedDigits(1000));
    }

    public static int rotatedDigits(int n) {
        if (n < 1) return 0;
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (isValid(i)) {
                count++;
            }
        }
        return count;
    }

    /* 判断是否存在2,5,6,9，，且不存在3，4，7 */
    public static boolean isValid(int num) {
        boolean flag = false;
        while (num > 0) {
            int k = num % 10;
            if(k == 3 || k == 4 || k == 7) return false;
            if (k == 2 || k == 5 || k == 6 || k == 9) {
                flag = true;
            }
            num /= 10;
        }
        return flag;
    }

}
