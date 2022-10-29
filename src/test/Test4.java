package test;

public class Test4 {


    public String compressString(String S) {
        if (S == null) {
            return null;
        }
        if (S.length() == 0) {
            return S;
        }
        StringBuilder s = new StringBuilder();
        int l = 0;
        int r = 0;
        char[] cs = S.toCharArray();
        int len = S.length();
        while (r < len) {
            if (cs[l] != cs[r]) {
                s.append(cs[l] + "" + (r - l));
            } else {
                r++;
            }
        }
        s.append(cs[l] + "" + (r - l));
        String x = s.toString();
        return x.length() > S.length() ? S : x;
    }

    /**
     * 堆排序
     *
     * @param nums
     * @return
     */
    public int[] sortArray(int[] nums) {
        // 创建大顶堆
        createHeap(nums);
        int n = nums.length - 1;
        while (n > 0) {
            swap(nums, n, 0);
            n--;
            sink(nums, 0, n);
        }
        return nums;
    }

    public void createHeap(int[] nums) {
        for (int i = nums.length / 2; i >= 0; i--) {
            sink(nums, i, nums.length - 1);
        }
    }

    /**
     * 下沉处理
     */
    public void sink(int[] nums, int n, int len) {
        int max = 0;
        while (n * 2 + 1 <= len) {
            if (n * 2 + 2 <= len) {
                if (less(nums, n * 2 + 1, n * 2 + 2)) {
                    max = n * 2 + 2;
                } else {
                    max = n * 2 + 1;
                }
            } else {
                max = n * 2 + 1;
            }
            // 当大于max的时候，直接退出
            if (!less(nums, n, max)) {
                break;
            }
            swap(nums, n, max);
            n = max;
        }
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public boolean less(int[] arr, int i, int j) {
        return arr[i] < arr[j];
    }

    /***
     * 归并排序
     */
    public int[] merge(int[] nums) {
        return null;
    }
}
