package topic.package06;

public class Test {


    public static void main(String[] args) {
        System.out.println(2 >> 1);
    }


    public int minimumOperations(String leaves) {
        if (leaves == null || leaves.length() < 3) return 0;
        int n = leaves.length();
        // 表示从0-i之间状态为0，1，2的最小操作个数
        int[][] f = new int[n][3];
        // 第一个必须为0
        f[0][0] = leaves.charAt(0) == 'r' ? 0 : 1;
        f[0][1] = f[0][2] = Integer.MAX_VALUE;
        f[1][2] = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            int red = leaves.charAt(i) == 'r' ? 0 : 1;
            int yellow = leaves.charAt(i) == 'y' ? 0 : 1;
            f[i][0] = f[i - 1][0] + red;
            f[i][1] = Math.min(f[i - 1][0], f[i - 1][1]) + yellow;
            if (i >= 2) {
                f[i][2] = Math.min(f[i - 1][1], f[i - 1][2]) + red;
            }
        }
        return f[n - 1][2];
    }


    // 一个图的一定相互连线
    public static int arrayNesting(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int ans = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            while (nums[i] < n) {
                int num = nums[i];
                nums[i] = n;
                i = num;
                ++cnt;
            }
            ans = Math.max(ans, cnt);
        }
        return ans;
    }
}
