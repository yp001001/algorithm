package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.concurrent.FutureTask;

public class Test7 {
    public static void main(String[] args) throws IOException {
    }

    /**
     * 薯队长来到了迷宫的尽头，面前出现了N只魔物，Hi表示第i只魔物的血量，薯队长需要在T个回合内击败所有魔物才能获胜。
     * 每个回合薯队长可 以选择物理攻击一只魔物，对其造成1点伤害（物理攻击次数无上限）;
     * 或者消耗1点法力释放必杀技对其造成固定X点伤害（薯队长开始拥有M 点法力）。问X至少多大，薯队长才有机会获胜；
     * 如果无论如何都无法在T回合内获胜，则输出-1
     */
    /**
     * @param H len表示多少怪兽，H[i]表示怪兽血量
     * @param T T表示回合
     * @param X X表示法术伤害
     * @param M M表示法力
     */
    public static void xiaohongshu04(int[] H, int T, int X, int M) {
        // 一直打血多的
        Arrays.sort(H);
        int len = H.length;
        int count = 0;

    }

    /**
     * 10分 25分 5分 1分的硬币
     *
     * @param n
     */
    public static void xiaohongshu01(int n) {

    }

    public static void xiaohongshu03(int[] nums) {
        /**
         * 薯队长写了n篇笔记，编号从1~n,每篇笔记都获得了不少点赞数。
         * 薯队长想从中选出一些笔记，作一个精选集合。挑选的时候有两个规则：
         *  1.不能出现连续编号的笔记。
         * 2.总点赞总数最多
         * 如果满足1，2条件有多种方案，挑选笔记总数最少的那种
         */
        if (nums == null || nums.length == 0) {
            return;
        }
        int[] res = new int[2];
        if (nums.length == 1) {
            System.out.println("zan：" + nums[0] + " 步长：" + 1);
            return;
        }
        if (nums.length == 2) {
            System.out.println("zan：" + (nums[0] > nums[1] ? nums[0] : nums[1]) + " 步长：" + 1);
            return;
        }
        int len = nums.length;
        int[] dp = new int[len];
        int[] step = new int[len];
        dp[0] = nums[0];
        dp[1] = nums[1] > nums[0] ? nums[1] : nums[0];
        step[0] = 1;
        step[1] = 1;
        for (int i = 2; i < len; i++) {
            if (dp[i - 1] > dp[i - 2] + nums[i]) {
                dp[i] = dp[i - 1];
                step[i] = step[i - 1];
            } else {
                dp[i] = dp[i - 2] + nums[i];
                step[i] = step[i - 2] + 1;
            }
        }
        System.out.println("zans：" + dp[len - 1] + " 步长：" + nums[len - 1]);
    }

    private static void xiaohongshu02() throws IOException {
        /**
         * 薯队长带着小红薯参加密室逃脱团建游戏，首先遇到了反转游戏，小红薯们根据游戏提示收集了多个单词线索，
         * 并将单词按要求加一个空格组 成了句子，最终要求把句子按单词反转解密。
         * 说明：收集的时候单词前后可能会有多个空格，反转后单词不能有多个空格，具体见输入输出样例。
         *</p>
         *  xaiqdwu euif hello    pppp
         *  反转，中间只能有一个空格
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String content = br.readLine();
        String[] str = content.trim().replaceAll("\t", " ").split(" ");
        StringBuilder sb = new StringBuilder();
        for (String s : str) {
            if (!s.equals("")) {
                sb.insert(0, " " + s);
            }
        }
        String trim = sb.toString().trim();
        System.out.println(trim);
    }
}
