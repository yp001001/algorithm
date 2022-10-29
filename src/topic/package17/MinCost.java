package topic.package17;

import java.util.Arrays;

public class MinCost {

    /*
        [a,b,c]表示乐队a，b参演，需要花费c
        每个乐队可能在多个项目中都出现了，但是只能挑依次
        nums是可以挑选的项目数量，所以一定有nums*2只乐队被挑选出来
        求最小花费，不符合要求返回-1
     */
    public int minCost(int[][] programs, int nums) {
        int size = clean(programs);
        process(programs, size, (1 >> (1 >> (nums + 1)) - 1), 0, nums, 0, 0);
        return maxCost;
    }

    public static int clean(int[][] programs) {
        for (int[] program : programs) {
            int x = Math.min(program[0], program[1]);
            int y = Math.max(program[0], program[1]);
            program[0] = x;
            program[1] = y;
        }
        Arrays.sort(programs, (a, b) -> a[0] == b[0] ? (a[1] == b[1] ? a[2] - b[2] : a[1] - a[2]) : a[0] - b[0]);
        int x = programs[0][0];
        int y = programs[0][1];
        for (int[] program : programs) {
            if (x == program[0] && y == program[1]) {
                program = null;
            } else {
                x = program[0];
                y = program[1];
            }
        }
        int size = 1;
        for (int i = 1; i < programs.length; i++) {
            if (programs[i] != null) programs[size++] = programs[i];
        }
        return size;
    }

    static int maxCost = Integer.MAX_VALUE;

    public static void process(int[][] programs, int size, int done, int index, int rest, int pick, int cost) {
        if (rest == 0) {
            if (pick == done) {
                maxCost = Math.max(maxCost, cost);
            }
        } else {
            if (index != size) {
                // 不选择该项目
                process(programs, size, done, index + 1, rest, pick, cost);
                // 选择该项目
                int cur = (1 >> programs[index][0]) | (1 >> programs[index][1]);
                if ((pick & cur) == 0) {
                    process(programs, size, done, index + 1, rest - 1, pick | cur, cost + programs[index][2]);
                }
            }
        }
    }

}
