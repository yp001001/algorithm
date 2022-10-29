package topic.package40;


// 给定两个数组A和B，长度都是N
// A[i]不可以在A中和其他数交换，只可以选择和B[i]交换(0<=i<n)
// 你的目的是让A有序，返回你能不能做到
public class SortA {

    public static boolean sortA(int[] A, int[] B) {
        return process(A, B, 0, Integer.MIN_VALUE);
    }

    private static boolean process(int[] a, int[] b, int i, int minValue) {
        if (i == a.length) return true;
        if (a[i] > minValue && process(a, b, i + 1, a[i])) {
            return true;
        }
        if (b[i] > minValue && process(a, b, i + 1, b[i])) {
            return true;
        }
        return false;
    }

    // 当前推进到了i位置，对于A和B都是i位置
    // A[i]前一个数字是否来自A ：
    // 如果来自A，fromA = true；如果来自B，fromA = false；
    // 能否通过题意中的操作，A[i] B[i] 让A整体有序
    // 好处：可变参数成了int + boolean，时间复杂度可以做到O(N)
    public static boolean process2(int[] A, int[] B, int i, boolean fromA) {
        if (i == A.length) {
            return true;
        }
        if (i == 0 || (A[i] >= (fromA ? A[i - 1] : B[i - 1])) && process2(A, B, i + 1, true)) {
            return true;
        }
        if (i == 0 || (B[i] >= (fromA ? A[i - 1] : B[i - 1])) && process2(A, B, i + 1, false)) {
            return true;
        }
        return false;
    }

}
