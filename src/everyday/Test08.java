package everyday;

import java.util.Arrays;

public class Test08 {

    public static void main(String[] args) {
        int[] p1 = {0, 0};
        int[] p2 = {5, 0};
        int[] p3 = {5, 4};
        int[] p4 = {0, 4};
        System.out.println(validSquare(p1, p2, p3, p4));
    }

    public static boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        if (Arrays.equals(p1, p2) || Arrays.equals(p1, p3) || Arrays.equals(p1, p4) || Arrays.equals(p2, p3) || Arrays.equals(p3, p4))
            return false;
        return getSize(p1, p2) == getSize(p3, p4) &&
                getSize(p1, p3) == getSize(p2, p4) &&
                getSize(p1, p4) == getSize(p2, p3) &&
                (getSize(p1, p2) == getSize(p1, p3) || getSize(p1, p2) == getSize(p1, p4) || getSize(p1, p3) == getSize(p1, p4));
    }

    public static long getSize(int[] p1, int[] p2) {
        return (long) Math.pow(p1[0] - p2[0], 2) + (long) Math.pow(p1[1] - p2[1], 2);
    }

}
