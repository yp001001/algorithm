package everyday;

public class Test11 {


    static class MyCircularQueue {

        private int[] nums;
        int head;
        int tail;
        int capacity;

        public MyCircularQueue(int k) {
            this.capacity = k + 1;
            this.nums = new int[capacity];
            this.head = 0;
            this.tail = 0;
        }

        public boolean enQueue(int value) {
            if (isFull()) return false;
            nums[tail] = value;
            tail = (++tail) % capacity;
            return true;
        }

        public boolean deQueue() {
            if (isEmpty()) return false;
            head = (++head) % capacity;
            return true;
        }

        public int Front() {
            if (isEmpty()) return -1;
            return nums[head];
        }

        public int Rear() {
            if (isEmpty()) return -1;
            return nums[(tail - 1 + capacity) % capacity];
        }

        public boolean isEmpty() {
            return head == tail;

        }

        public boolean isFull() {
            return (tail + 1) % capacity == head;
        }
    }

}
