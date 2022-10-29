package topic.package13;

/* 切一刀使得 Max(left) - Max(right) = MaxVal */
public class CutMaxVal {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 7, 1, 8, 2};
        System.out.println(MaxVal(arr));
    }


    public static int MaxVal(int[] arr) {
        int maxVal = Integer.MIN_VALUE;
        int cutIdx = -1;
        for (int i = 0; i <= arr.length - 2; i++) {
            if (maxVal < arr[i]) {
                maxVal = arr[i];
                cutIdx = i;
            }
        }
        return cutIdx;
    }

}
