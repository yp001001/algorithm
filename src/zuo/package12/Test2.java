package zuo.package12;

import java.util.concurrent.TimeUnit;

// jconsole
// jps + jstack pid
public class Test2 {
    static Object o1 = new Object();
    static Object o2 = new Object();

    public static void main(String[] args) {
        Integer i1 = 10;
        int i2 = 10;
        System.out.println(i1 == i2);
    }
}
