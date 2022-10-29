package topic.package14;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
    长度为N的数组，一定可以组成N^2个数字对
    第一维数据从小到大排序，第二维数据也同理
    求第K小的数值对
 */
public class GiveMinK {

    public static void main(String[] args) {
        int[] arr = GiveMinK(new int[]{3, 1, 2, -1, 10, 2, 4, -8}, 20);
        int[] arr2 = GiveMinK2(new int[]{3, 1, 2, -1, 10, 2, 4, -8}, 20);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr2));
    }

    public static int[] GiveMinK(int[] arr, int k) {
        if (arr == null || arr.length == 0) return null;
        int n = arr.length;
        Arrays.sort(arr);
        if (k == 0) {
            return new int[]{arr[0], arr[0]};
        }
        int oneIdx = k / n;
        int twoIdx = k % n;
        oneIdx = twoIdx == 0 ? oneIdx - 1 : oneIdx;
        twoIdx = twoIdx == 0 ? n - 1 : twoIdx - 1;
        return new int[]{arr[oneIdx], arr[twoIdx]};
    }

    public static int[] GiveMinK2(int[] arr, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0];
            }
        });
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                queue.offer(new int[]{arr[i], arr[j]});
            }
        }
        while (--k > 0) {
            queue.poll();
        }
        return queue.poll();
    }

}
