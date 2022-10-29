package jx.yp.sort.hdck;

public class T_10 {

    /**
     * 滑动窗口如果含有负值不能使用
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 0, j = 0, count = 0, num = 0;
        while (j < nums.length) {
            num += nums[j];
            if (num == k) {
                count++;
            } else if (num > k) {
                while (num > k && i != j) {
                    num -= nums[i];
                    i++;
                }
                if (num == k) {
                    count++;
                }
            }
            j++;
        }
        return count;
    }

}
