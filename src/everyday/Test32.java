package everyday;


public class Test32 {

    public static int maxProduct(int[] nums) {
        int N = nums.length;
        int[] maxNums = new int[N];
        maxNums[N - 1] = nums[N - 1];
        // 找到i下标及之后的最大元素
        for (int i = N - 2; i >= 0; i--) {
            maxNums[i] = Math.max(nums[i], maxNums[i + 1]);
        }
        int res = -1;
        for (int i = 0; i <= N - 2; i++) {
            res = Math.max((nums[i] - i) * (maxNums[i + 1] - i), res);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{3, 7}));
    }

}
