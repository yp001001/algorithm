package topic.package04;

// 返回数组中子数组最大累加和
public class Problem02 {


    public static int maxValue(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int N = arr.length;
        int maxValue = arr[0];
        int sum = arr[0];
        for (int i = 1; i < N; i++) {
            if (sum < 0) sum = arr[i];
            else sum += arr[i];
            maxValue = Math.max(maxValue, sum);
        }
        return maxValue;
    }

    public static void main(String[] args) {
        System.out.println(maxValue(new int[]{1, 2, 3, 4, -1, 2, -3, -4}));
    }

}
