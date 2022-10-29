package everyday;

// https://leetcode.cn/problems/incremental-memory-leak/
public class Test19 {

    public static void main(String[] args) {
        memLeak(8, 11);
    }

    public static int[] memLeak(int memory1, int memory2) {
        int useMemory = 1;
        int time = 0;
        while (memory1 >= useMemory || memory2 >= useMemory) {
            if (memory1 >= memory2) memory1 -= useMemory;
            else memory2 -= useMemory;
            time++;
            useMemory++;
        }
        return new int[]{++time, memory1, memory2};
    }

}
