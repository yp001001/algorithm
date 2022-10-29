package topic.package14;

import java.util.HashMap;

/*
    给定一个正数数组arr，长度一定大于6
    一定要选3个数字做分割点，从而分出4个部分，并且每部分都有数
    返回有无可能分出的4个部分累加和一样大
 */
public class Split4Parts {

    public static boolean canSplits1(int[] arr) {
        if (arr == null || arr.length < 7) {
            return false;
        }
        // key 某一个累加和， value出现的位置
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int sum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            map.put(sum, i);
            sum += arr[i];
        }
        int lsum = arr[0]; // 第一刀左侧的累加和
        for (int s1 = 1; s1 < arr.length - 5; s1++) { // s1是第一刀的位置
            int checkSum = lsum * 2 + arr[s1]; // 100 x 100   100*2 + x
            if (map.containsKey(checkSum)) {
                int s2 = map.get(checkSum); // j -> y
                checkSum += lsum + arr[s2];
                if (map.containsKey(checkSum)) { // 100 * 3 + x + y
                    int s3 = map.get(checkSum); // k -> z
                    if (checkSum + arr[s3] + lsum == sum) {
                        return true;
                    }
                }
            }
            lsum += arr[s1];
        }
        return false;
    }


    public static boolean canSplits2(int[] arr) {
        if (arr == null || arr.length < 7) return false;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int sum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            map.put(sum, i);
            sum += arr[i];
        }
        int lsum = arr[0]; // 第一刀左侧的累加和
        for (int i = 1; i < arr.length - 5; i++) {
            int checkSum = lsum * 2 + arr[i];
            if (map.containsKey(checkSum)) {
                int s2 = map.get(checkSum);
                checkSum += lsum + arr[s2];
                if (map.containsKey(checkSum)) {
                    int s3 = map.get(checkSum);
                    if (checkSum + lsum + arr[s3] == sum) return true;
                }
            }
            lsum += arr[i];
        }
        return false;
    }


    public static int[] generateRondomArray() {
        int[] res = new int[(int) (Math.random() * 10) + 7];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int) (Math.random() * 10) + 1;
        }
        return res;
    }

    public static void main(String[] args) {
//        int testTime = 3000000;
//        for (int i = 0; i < testTime; i++) {
//            int[] arr = generateRondomArray();
//            if (canSplits1(arr) && canSplits2(arr)) {
//                System.out.println("Error");
//            }
//        }

        int[] arr = new int[]{3, 2, 3, 7, 4, 4, 3, 1, 1, 6, 7, 1, 5, 2};
        System.out.println(canSplits1(arr));
        System.out.println(canSplits2(arr));
    }

}
