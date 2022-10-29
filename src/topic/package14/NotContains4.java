package topic.package14;

public class NotContains4 {

    public static long[] arr = {0L, 1L, 9L, 81L, 729L, 6561L, 59049L, 531441L, 4782969L, 43046721L, 387420489L,
            3486784401L, 31381059609L, 282429536481L, 2541865828329L, 22876792454961L, 205891132094649L,
            1853020188851841L, 16677181699666569L, 150094635296999121L, 1350851717672992089L};

    // 暴力
    public static long notContains4(long num) {
        long count = 0;
        for (int i = 1; i <= num; i++) {
            if (isNot4(i)) {
                count++;
            }
        }
        return count;
    }

    public static boolean isNot4(long num) {
        while (num != 0) {
            if (num % 10 == 4) {
                return false;
            }
            num /= 10;
        }
        return true;
    }

    public static long notContains4_2(long num) {
        if (num <= 0) return 0;
        int len = decimalLength(num);
        long offset = offset(len);
        long first = num / offset;
        // arr[len] - 1 表示为num位数-1的元素个数
        return arr[len] - 1 + (first - (first < 4 ? 1 : 2)) * arr[len] + process(num % offset, offset / 10, len - 1);
    }

    private static long process(long num, long offset, int len) {
        if (len == 0) return 1;
        long first = num / offset;
        return (first < 4 ? first : (first - 1)) * arr[len] + process(num % offset, offset / 10, len - 1);
    }

    private static int decimalLength(long num) {
        int len = 0;
        while (num > 0) {
            num /= 10;
            len++;
        }
        return len;
    }

    private static long offset(int len) {
        long offest = 1;
        for (int i = 1; i < len; i++) {
            offest *= 10L;
        }
        return offest;
    }


    // 9进制 -> 10进制
    public static long notContains4_3(long num) {
        if (num <= 0) return 0;
        long ans = 0;
        int base = 1;
        long cur;
        while (num > 0) {
            cur = num % 10;
            ans += (cur < 4 ? cur : cur - 1) * base;
            base *= 9;
            num /= 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        long max = 88888888L;
        System.out.println("功能测试开始，验证 0 ~ " + max + " 以内所有的结果");
        for (long i = 0; i <= max; i++) {
            // 测试的时候，输入的数字i里不能含有4，这是题目的规定！
            if (isNot4(i) && notContains4_2(i) != notContains4_3(i)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("如果没有打印Oops说明验证通过");

        long num = 8173528638135L;
        long start;
        long end;
        System.out.println("性能测试开始，计算 num = " + num + " 的答案");
        start = System.currentTimeMillis();
        long ans2 = notContains4_2(num);
        end = System.currentTimeMillis();
        System.out.println("方法二答案 : " + ans2 + ", 运行时间 : " + (end - start) + " ms");

        start = System.currentTimeMillis();
        long ans3 = notContains4_3(num);
        end = System.currentTimeMillis();
        System.out.println("方法三答案 : " + ans3 + ", 运行时间 : " + (end - start) + " ms");
    }


}
