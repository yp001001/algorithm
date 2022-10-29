package zuo.package07;

public class Hanoi {

    public static void hanoi1(int n) {
        leftToRight(n);
    }

    private static void leftToRight(int n) {
        if (n == 1) {
            System.out.println("Move 1 from left to Right");
            return;
        }
        leftToMid(n - 1);
        System.out.println("Move " + n + " from left to right");
        midToRight(n - 1);
    }

    private static void midToRight(int n) {
        if (n == 1) {
            System.out.println("Move 1 from mid to right");
            return;
        }
        midToLeft(n - 1);
        System.out.println("Move " + n + " from mid to right");
        leftToRight(n - 1);
    }

    private static void midToLeft(int n) {
        if (n == 1) {
            System.out.println("Move 1 form mid to left");
            return;
        }
        midToRight(n - 1);
        System.out.println("Move " + n + " from mid to right");
        rightToLeft(n - 1);
    }

    private static void rightToLeft(int n) {
        if (n == 1) {
            System.out.println("Move 1 from right to left ");
            return;
        }
        rightToMid(n - 1);
        System.out.println("Move " + n + " from right to mid");
        midToLeft(n - 1);
    }

    private static void leftToMid(int n) {
        if (n == 1) {
            System.out.println("Move 1 from left to mid");
            return;
        }
        leftToRight(n - 1);
        System.out.println("Move " + n + " from left to mid");
        rightToMid(n - 1);
    }

    private static void rightToMid(int n) {
        if (n == 1) {
            System.out.println("Move 1 from right to mid");
            return;
        }
        rightToLeft(n - 1);
        System.out.println("Move " + n + " from right to left");
        leftToMid(n - 1);
    }

    public static void hanoi2(int N, String from, String to, String other) {
        func(N, "left", "right", "mid");
    }

    public static void func(int N, String from, String to, String other) {
        if (N == 1) {
            System.out.println("Move 1 from " + from + " to " + to);
        } else {
            func(N - 1, from, other, to);
            System.out.println("Move " + N + " from " + from + " to " + to);
            func(N - 1, other, to, from);
        }
    }



    public static void main(String[] args) {
        hanoi1(3);
        System.out.println("=====================");
        hanoi2(3, "left", "right", "mid");
    }


}
