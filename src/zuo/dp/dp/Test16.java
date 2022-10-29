package zuo.dp.dp;


// 数组分成两组平均数的较小值
// 两个集合包含数一样多或者一个集合比另一个集合多一个
public class Test16 {

    public static int right(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        int sum = 0;
        for (int a : arr) {
            sum += a;
        }
        if ((arr.length & 1) == 0)
            return process(arr, 0, arr.length / 2, sum / 2);
        return Math.max(process(arr, 0, arr.length / 2, sum / 2),
                process(arr, 0, arr.length / 2 + 1, sum / 2));
    }

    private static int process(int[] arr, int index, int picks, int rest) {
        if (index == arr.length) {
            // -1表示不符合要求
            return picks == 0 ? 0 : -1;
        }
        int p1 = process(arr, index + 1, picks, rest);
        int p2 = -1;
        if (arr[index] <= rest) {
            p2 = process(arr, index + 1, picks - 1, rest - arr[index]);
        }
        if (p2 != -1) {
            p2 = p2 + arr[index];
        }
        return Math.max(p1, p2);
    }
}
