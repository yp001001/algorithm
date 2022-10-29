package topic.package18;

import java.util.*;

/*
    有n块积木，希望将这些积木堆起来，要求如下：
        1. 上面的积木重量不能小于下面的积木重量
        2. 上面的积木重量减去下面积木重量不能超过x
        3. 每堆中最下面的积木没有重量要求
    有k块任意积木，希望能将积木全部堆起来，求最少的堆数
 */
public class BuildingBlock {

    public static void main(String[] args) {
        System.out.println(wordPattern("abba", "dog cat cat fish"));
    }

    public static boolean wordPattern(String s, String pattern) {
        String[] words = pattern.split("\\s");
        if (words.length != s.length()) return false;
        Map<String, Character> stringMap = new HashMap<>();
        Map<Character, String> charMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            char c = s.charAt(i);
            if (stringMap.containsKey(words[i])) {
                if (stringMap.get(words[i]) != c) return false;
            } else {
                if (charMap.containsKey(c)) return false;
                stringMap.put(words[i], c);
                charMap.put(c, words[i]);
            }
        }
        return true;
    }

    public static int minSplit(int[] arr, int k, int x) {
        if (arr == null || arr.length == 0) return 0;
        Arrays.sort(arr);
        int nowBlock = arr[0];
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] > x) {
                list.add(arr[i] - nowBlock);
            }
        }
        Collections.sort(list);
        for (Integer a : list) {
            int used = a / x == 0 ? 1 : a / x + 1;
            if (k >= used) {
                list.remove(a);
                k -= used;
            }
        }
        return list.size() + 1;
    }

    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //左递归
            mergeSort(arr, left, mid, temp);
            // 右递归
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * @param arr   排序数组
     * @param left  最左下标
     * @param mid   中间下标
     * @param right 最右下标
     * @param temp  临时数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int l = left, r = mid + 1;
        int index = 0;
        while (l <= mid && r <= right) {
            if (arr[l] < arr[r]) {
                temp[index++] = arr[l++];
            } else {
                temp[index] = arr[r++];
            }
        }
        while (l <= mid) arr[index++] = arr[l++];
        while (r <= right) arr[index] = arr[r++];
        index = 0;
        for (int i = left; i <= right; i++) {
            arr[i] = arr[index++];
        }
    }

}
