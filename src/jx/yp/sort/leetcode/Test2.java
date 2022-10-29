package jx.yp.sort.leetcode;

import javax.swing.tree.TreeNode;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * 实现双线程打印
 */
public class Test2 {
    private static int count = 1;
    private static int Num = 5; // 打印次数
    private static int flag = 1; // 作为标记

    private static ReentrantLock lock = new ReentrantLock();

    private static Object OBJ = new Object();

    static boolean run = true;

    /**
     * 可见性；
     * synchronized也具有可见性，因为它调用的unlock解锁这个操作规定，放开对某个变量的锁的之前，
     * 需要把这个变量从缓存更新到主内存，因此它也具有可见性。
     * <p>
     * 有序性：
     * synchronized无法禁止指令重排，但是符合as if serial (单线程的指令重排结果不能被改变) 规则，保证了有序性
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        int[] ints = list.stream().mapToInt(Integer::intValue).toArray();
        test();

    }


    /**
     * 交替输出 (方式2)
     * 线程1输出a 5次，线程2输出b 5次，线程3输出c 5次
     * abcacbabcabcabc
     */
    public static void print(int f, int nextFlag, String message) {
        for (int i = 0; i < Num; i++) {
            synchronized (OBJ) {
                while (flag != f) {
                    try {
                        OBJ.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " ：" + message);
                flag = nextFlag;
                OBJ.notifyAll();
            }
        }
    }

    /**
     * 交替打印 1-100
     */
    private static void test() {
        new Thread(() -> {
            while (count < 100) {
                try {
                    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(2000) + 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.lock();
                if (count % 2 != 0) {
                    System.out.println("线程1：" + count++);
                }
                lock.unlock();
            }
        }).start();
        new Thread(() -> {
            while (count < 100) {
                try {
                    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(2000) + 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.lock();
                if (count % 2 == 0) {
                    System.out.println("线程2：" + count++);
                }
                lock.unlock();
            }
        }).start();
    }
}

