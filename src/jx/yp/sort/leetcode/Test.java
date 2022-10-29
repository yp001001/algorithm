package jx.yp.sort.leetcode;

import java.util.HashMap;

public class Test {

    public static void main(String[] args) {
        /**
         * 会将父线程的InheritableThreadLocal赋给子线程的InheritableThreadLocal
         */
        InheritableThreadLocal parentLocal = new InheritableThreadLocal();

        new Thread(() -> {
            parentLocal.set("hahaha");

            new Thread(() -> {
                System.out.println(parentLocal.get());
            }).start();

        }).start();


        HashMap<Object, Object> map = new HashMap<>();
        map.put("A", "A");
        map.put("B", "B");
    }


    public int luck_num(int start, int end) {
        return -1;
    }
}
