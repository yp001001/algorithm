package test;

import java.sql.Time;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Test2 {
    static List<Thread> list = new ArrayList<>();
    static Object lock = new Object();

    /**
     * EntryList 倒序执行
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("111");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();

        TimeUnit.SECONDS.sleep(10);
    }


    private static void test() throws InterruptedException {
        Queue<Integer> queue = new LinkedList<>();
        queue.poll();
        Integer peek = queue.peek();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Thread t = new Thread(() -> {
                synchronized (lock) {
                    System.out.println("thread executed  " + finalI);
                    try {
                        TimeUnit.MILLISECONDS.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "t-" + i);
            list.add(t);
        }

        synchronized (lock) {
            for (Thread thread : list) {
                //这个打印主要是为了看到线程启动的顺序
                System.out.println(("{}-启动顺序--正序0-9" + thread.getName()));
                thread.start();//  CPU 调度？

                //这个睡眠相当重要，如果没有这个睡眠会有很大问题
                //这里是因为线程的start仅仅是告诉CPU线程可以调度了，但是会不会立马调度是不确定的
                //如果这里不睡眠 就有有这种情况出现
                // 主线程执行t1.start--Cpu没有调度t1--继续执行主线程t2-start cpu调度t2--然后再调度t1
                //虽然我们的启动顺序是正序的（t1--t2），但是调度顺序是错乱的  t2---t1

                TimeUnit.MILLISECONDS.sleep(1);
            }
            System.out.println(("-------执行顺序--正序9-0"));
        }
    }
}
