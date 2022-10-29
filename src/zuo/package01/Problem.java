package zuo.package01;


/**
 * 右边或左边求大小值可以往这边想（归并排序）
 */
public class Problem {

    /* 小和问题 */
    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int l, int r) {
        if (l == r) return 0;
        int res = 0;
        int mid = (l + r) >>> 1;
        // 得到 merge 左边的小和
        res += process(arr, l, mid);
        // 得到 merge 右边的小和
        res += process(arr, mid + 1, r);
        res += merge(arr, l, mid, r);
        return res;
    }

    private static int merge(int[] arr, int l, int mid, int r) {
        int[] temp = new int[r - l + 1];
        int p1 = l, p2 = mid + 1;
        int res = 0;
        int index = 0;
        while (p1 <= mid && p2 <= r) {
            res += arr[p1] < arr[p2] ? arr[p1] * (r - p2 + 1) : 0;
            temp[index++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) temp[index++] = arr[p1++];
        while (p2 <= r) temp[index++] = arr[p2++];
        for (int i = 0; i < temp.length; i++) {
            arr[l + i] = temp[i];
        }
        return res;
    }

    /* 当前数的右边的两倍数小于当前数的个数 */
    private static int mergeDouble(int[] arr, int l, int mid, int r) {
        int[] temp = new int[r - l + 1];
        int p1 = l, p2 = mid + 1;
        int res = 0;

        int windowR = mid + 1;
        for (int i = l; i <= mid; i++) {
            while (windowR <= r && arr[windowR] * 2 < arr[i]) {
                windowR++;
            }
            res += windowR - mid - 1;
        }

        int index = 0;
        while (p1 <= mid && p2 <= r) {
            temp[index++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) temp[index++] = arr[p1++];
        while (p2 <= r) temp[index++] = arr[p2++];
        for (int i = 0; i < temp.length; i++) {
            arr[l + i] = temp[i];
        }
        return res;
    }


    /* 区间和的个数 --> 转换成sum[y]-sum[x]在(lower,upper)区间中 */
    public static int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        long[] arr = new long[n];
        arr[0] = nums[0];
        for (int i = 1; i < n; i++) {
            arr[i] = arr[i - 1] + nums[i];
        }
        return process(arr, lower, upper, 0, nums.length - 1);
    }

    private static int process(long[] arr, int lower, int upper, int l, int r) {
        if (l == r) {
            if (arr[l] >= lower && arr[l] <= upper) return 1;
            return 0;
        }
        int mid = (l + r) >>> 1;
        return process(arr, lower, upper, l, mid) + process(arr, lower, upper, mid + 1, r) + merge(arr, lower, upper, l, mid, r);
    }

    public static int merge(long[] arr, int lower, int upper, int l, int mid, int r) {
        int windowL = l, windowR = l;
        int ans = 0;
        for (int i = mid + 1; i <= r; i++) {
            long min = arr[i] - upper;
            long max = arr[i] - lower;
            while (windowR <= mid && arr[windowR] <= max) {
                windowR++;
            }
            while (windowL <= mid && arr[windowL] < min) {
                windowL++;
            }
            ans += windowR - windowL;
        }

        long[] temp = new long[r - l + 1];
        int p1 = l, p2 = mid + 1;
        int index = 0;
        while (p1 <= mid && p2 <= r) {
            temp[index++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) temp[index++] = arr[p1++];
        while (p2 <= r) temp[index++] = arr[p2++];
        for (int i = 0; i < temp.length; i++) {
            arr[l + i] = temp[i];
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{4, 3, 2, 6, 1, 7, 1};
        System.out.println(smallSum(arr));
    }
}
