package topic.package19;

import java.util.Arrays;

// 会议室问题
public class Room {

    // 就是求两个数组不能含有重复
    public static int count(int[][] meets) {
        if (meets == null || meets.length == 0) return 0;
        Arrays.sort(meets, (x1, x2) -> x1[0] == x2[0] ? x1[1] - x2[1] : x1[0] - x2[0]);
        int end = meets[0][1];
        int count = 1;
        for (int i = 1; i < meets.length; i++) {
            if (meets[i][0] <= end) {
                count++;
                end = Math.max(end, meets[i][1]);
            }
        }
        return count;
    }

}
