package everyday;

public class Test28 {

    public static void main(String[] args) {
        MyCircularDeque deque = new MyCircularDeque(3);
        deque.insertLast(2);
        System.out.println(deque.getFront());
    }


    static class MyCircularDeque {

        private int prev;
        private int last;
        private int size;
        private int[] nums;

        public MyCircularDeque(int k) {
            prev = 0;
            nums = new int[k];
            last = k - 1;
            size = 0;
        }

        public boolean insertFront(int value) {
            if (isFull()) return false;
            nums[prev = prev == 0 ? nums.length - 1 : prev - 1] = value;
            size++;
            return true;
        }

        public boolean insertLast(int value) {
            if (isFull()) return false;
            nums[last = last == nums.length - 1 ? 0 : last + 1] = value;
            size++;
            return true;
        }

        public boolean deleteFront() {
            if (isEmpty()) return false;
            prev = prev == nums.length - 1 ? 0 : prev + 1;
            size--;
            return true;
        }

        public boolean deleteLast() {
            if (isEmpty()) return false;
            last = last == 0 ? nums.length - 1 : last - 1;
            size--;
            return true;
        }

        public int getFront() {
            if (isEmpty()) return -1;
            return nums[prev];
        }

        public int getRear() {
            if (isEmpty()) return -1;
            return nums[last];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == nums.length;
        }
    }

}
