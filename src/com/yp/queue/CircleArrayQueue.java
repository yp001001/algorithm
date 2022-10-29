package com.yp.queue;

/**
 * 环形队列
 */
public class CircleArrayQueue {
    private int front = 0; // 指向队列的第一个元素
    private int rear = 0;  // 指向队列的最后一个元素的后一个地址
    private int maxLength;
    private int[] array;

    public CircleArrayQueue(int maxLength) {
        this.maxLength = maxLength;
        array = new int[maxLength];
    }

    /**
     * 判断环形队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 判断环形队列是否已满
     * （rear - front + maxLength） % maxLength = maxLength 表示满
     * @return
     */
    public boolean isFull() {
        if (rear > front) {
            return rear - front == maxLength - 1;
        }
        return rear + 1 == front;
    }
    // (rear + 1) % maxSize == front;

    /**
     * 入队操作
     *
     * @param num
     */
    public void push(int num) {
        if (isFull()) {
            throw new RuntimeException("队列已满~~~");
        }
        array[rear] = num;
        if (rear == maxLength - 1) rear = 0;
        else rear = (rear + 1);
        // rear = (rear + 1) % maxLength
    }

    /**
     * 出队操作
     *
     * @return
     */
    public int pull() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空~~~");
        }
        int tag = front;
        if (front == maxLength) front = 0;
        else front = front + 1;
        return array[tag];

        // front = (front + 1) % maxLength
    }

    /**
     * 遍历环形队列
     */

    // 当前队列有效个数（rear + maxLength -front）% maxLength
    // 取出数据公式： array[i % maxLength] % maxLength
    public void all() {
        if (front > rear) {
            for (int i = front; i < maxLength; i++) {
                System.out.print(array[i] + "\t");
            }
            for (int i = 0; i < rear; i++) {
                System.out.print(array[i] + "\t");
            }
        } else {
            for (int i = front; i < rear; i++) {
                System.out.println(array[i]);
            }
        }
    }

    public static void main(String[] args) {
        CircleArrayQueue queue = new CircleArrayQueue(5);
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        queue.push(9);
        queue.pull();
        queue.pull();
        queue.push(5);
        queue.push(6);
        queue.all();
    }
}
