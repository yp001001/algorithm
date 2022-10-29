package everyday;

import java.util.*;

// https://leetcode.cn/problems/exclusive-time-of-functions/
public class Test18 {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("0:start:0");
        list.add("1:start:2");
        list.add("1:end:4");
        list.add("0:end:6");
        int[] ans = exclusiveTime(2, list);
        for (int an : ans) {

        }
    }

    public static int[] exclusiveTime(int n, List<String> logs) {
        Stack<int[]> stack = new Stack<>();
        int[] res = new int[n];
        for (String log : logs) {
            String[] split = log.split(":");
            int id = Integer.parseInt(split[0]);
            int time = Integer.parseInt(split[2]);
            if ("start".equals(split[1])) {
                stack.push(new int[]{id, time});
            } else {
                int[] pop = stack.pop();
                int interval = time - pop[1] + 1;
                res[pop[0]] += interval;
                if (!stack.isEmpty()) {
                    res[stack.peek()[0]] -= interval;
                }
            }
        }
        return res;
    }

}
