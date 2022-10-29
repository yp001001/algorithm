package com.test.tree;

import java.util.concurrent.Semaphore;

public class Test {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        new Thread(() -> {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("1");
        }).start();
        new Thread(() -> {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("2");
        }).start();
        new Thread(() -> {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("3");
            semaphore.release();
        }).start();
        new Thread(() -> {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("4");
        }).start();


    }
}
