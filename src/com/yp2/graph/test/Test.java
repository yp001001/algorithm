package com.yp2.graph.test;

import java.util.ArrayList;
import java.util.Iterator;

public class Test {

    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();
        list.add("A");
        list.add("C");
        list.add("D");
        list.add(1, "E");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String context = iterator.next();
            System.out.println(context);
            if ("A".equals(context)) {
                iterator.remove();
            }
        }
//        for (String s : list) {
//            if ("A".equals(s)) {
//                list.remove(s);
//            }
//        }

        /**
         * ForEach循环底层使用的是迭代器，所以在修改的时候也会报并发修改异常
         * <p/>
         * For循环不会报错
         */

    }
}
