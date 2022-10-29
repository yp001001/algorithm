package com.test.num;

// 买卖股票的最佳时机
/*
给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。

你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 */
public class MaxProfit {

    public static void main(String[] args) {
        int prices[] = {7, 20, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices));
    }

    // 只能购买一次并且抛出一次
    public static int maxProfit(int[] prices) {
        int max = 0;
        int min = prices[0];
        for (int i = 0; i < prices.length; i++) {
            // 获取股票的最高价钱
            max = Math.max(max, prices[i] - min);
            // 获取股票的最低价钱
            min = Math.min(min, prices[i]);
        }
        return max;
    }
}
