package zuo.package03;

public class Problem {

    public static void sortColors(int[] nums) {
        int ptr = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr++] = temp;
            }
        }
        for (int i = ptr; i < nums.length; i++) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr++] = temp;
            }
        }
    }


    public static class Info {
        public boolean isFull;
        public boolean isCBT;
        public int height;

        public Info(boolean isFull, boolean isCBT, int height) {
            this.isFull = isFull;
            this.isCBT = isCBT;
            this.height = height;
        }

        public static Info process(Node x) {
            if (x == null)
                return new Info(true, true, 0);
            Info leftInfo = process(x.left);
            Info rightInfo = process(x.right);
            int height = Math.max(leftInfo.height, rightInfo.height) + 1;
            boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
            return null;
        }
    }

    public static void main(String[] args) {
        sortColors(new int[]{2, 0, 2, 1, 1, 0});
    }
}

class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int data) {
        this.value = data;
    }
}
