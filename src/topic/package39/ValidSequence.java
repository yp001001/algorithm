package topic.package39;


// 来自腾讯
// 给定一个长度为n的数组arr，求有多少个子数组满足 :
// 子数组两端的值，是这个子数组的最小值和次小值，最小值和次小值谁在最左和最右无所谓
// n<=100000（10^5） n*logn  O(N)
public class ValidSequence {

    public static int nums(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int n = arr.length;
        int[] values = new int[n];
        int[] times = new int[n];
        int size = 0;
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            while (size != 0 && values[size - 1] > arr[i]) {
                size--;
                ans += times[size] + cn2(times[size]);
            }
            if (size != 0 && values[size - 1] == arr[i]) {
                times[size - 1]++;
            } else {
                values[size] = arr[i];
                times[size++] = 1;
            }
        }
        while (size != 0) {
            ans += cn2(times[--size]);
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            while (size != 0 && values[size - 1] > arr[i]) {
                ans += times[--size];
            }
            if (size != 0 && values[size - 1] == arr[i]) {
                times[size - 1]++;
            } else {
                values[size] = arr[i];
                times[size++] = 1;
            }
        }
        return ans;
    }

    public static int cn2(int n) {
        return (n * (n - 1)) >> 1;
    }


}
