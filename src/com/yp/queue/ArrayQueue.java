package com.yp.queue;

public class ArrayQueue {
    private int front = -1; // 指向队列头的前一个地址
    private int real = -1;  // 指向队列尾
    private int[] array;
    private int maxLength;

    public ArrayQueue(int maxLength) {
        this.maxLength = maxLength;
        array = new int[maxLength];
    }

    /**
     * 判断队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return front == real;
    }

    /**
     * 判断队列是否已满
     *
     * @return
     */
    public boolean isFull() {
        return real == maxLength - 1;
    }

    /**
     * 出队操作
     *
     * @return
     */
    public int pull() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，出队失败~~");
        }
        return array[++front];
    }


    /**
     * 入队操作
     *
     * @param num
     */
    public void push(int num) {
        if (isFull()) {
            throw new RuntimeException("队列已满，入队失败~~");
        }
        array[++real] = num;
    }

    /**
     * 遍历队列所有数据
     */
    public void all() {
        for (int i = front + 1; i <= real; i++) {
            System.out.print(array[i] + "\t");
        }
    }


    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(5);
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        queue.all();
        System.out.println();
        System.out.println(queue.pull());
        System.out.println(queue.pull());
        queue.push(5);
//        queue.push(6);
    }
}
