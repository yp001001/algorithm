package test2;


public class Test10 {

    public static void main(String[] args) {
        Test10 test10 = new Test10();
        int[] array = test10.sortArray(new int[]{2, 43, 21, 3, 11, 3, 1, 4, 3});
        for (int i : array) {
            System.out.print(i + " ");
        }
    }


    /**
     * 乘积不大于k的最大子数组的个数
     *
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        // 使用双指针
        int n = nums.length;
        int count = 0;
        int sum;
        for (int i = 0; i < n; i++) {
            int x = i;
            sum = nums[i];
            while (x < n && k > sum) {
                count++;
                x++;
                if (x >= n) {
                    break;
                }
                sum *= nums[x];
            }
        }
        return count;

    }

    /**
     * 乘积不大于k的最大子数组的个数
     *
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK_huadongchuangkou1(int[] nums, int k) {
        int ret = 0;
        int prod = 1;
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            prod *= nums[i];
            while (j <= i && prod >= k) {
                prod /= nums[i];
                j++;
            }
            ret += (i - j + 1);
        }
        return ret;
    }

    /**
     * 归并排序
     *
     * @param nums
     * @return
     */
    public int[] sortArray(int[] nums) {
        int[] temp = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1, temp);
        return nums;
    }

    void mergeSort(int[] nums, int left, int right, int[] temp) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(nums, left, mid, temp);
            mergeSort(nums, mid + 1, right, temp);
            merge(nums, left, mid, right, temp);
        }
    }

    /**
     * @param nums  待排序数组
     * @param left  左边边界
     * @param mid   中间
     * @param right 右边边界
     * @param temp  临时数组
     */
    void merge(int[] nums, int left, int mid, int right, int[] temp) {
        int index = 0;
        int r = mid + 1;
        int l = left;
        while (l <= mid && r <= right) {
            if (nums[l] < nums[r]) {
                temp[index++] = nums[l++];
            } else {
                temp[index++] = nums[r++];
            }
        }
        while (l <= mid) {
            temp[index++] = nums[l++];
        }
        while (r <= right) {
            temp[index++] = nums[r++];
        }
        // 将数据复制回nums
        index = 0;
        int ltemp = left;
        while (ltemp <= right) {
            nums[ltemp++] = temp[index++];
        }
    }
}
