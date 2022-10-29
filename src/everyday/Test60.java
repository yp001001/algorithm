package everyday;

import java.util.HashMap;
import java.util.Map;

public class Test60 {

    public static void main(String[] args) {
        System.out.println(canFormArray(new int[]{3, 4, 5}, new int[][]{{4, 5}, {3}}));
    }

    public static boolean canFormArray(int[] arr, int[][] pieces) {
        int n = arr.length, m = pieces.length;
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < m; i++) {
            index.put(pieces[i][0], i);
        }
        for (int i = 0; i < n; ) {
            if (!index.containsKey(arr[i]))
                return false;
            int j = index.get(arr[i]), len = pieces[j].length;
            for (int k = 0; k < len; k++) {
                if (arr[i + k] != pieces[j][k]) {
                    return false;
                }
            }
            i += len;
        }
        return true;
    }

}
