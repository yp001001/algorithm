package test2;

public class Test05 {

    public static void main(String[] args) {
        int i = binarySearch(new int[]{5, 7, 7, 8, 8, 10, 11}, 8);
        System.out.println(i);
    }


    private static int binarySearch(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }


    /**
     * 数组中缺失的元素
     *
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == mid) {
                l = mid + 1;
            } else {
                r = mid - 1;    // 需要-1，否则就没有缺失元素
            }
        }
        return l;
    }
}
