package com.yp.recursion;

public class Statement {


    /**
     * @param baskets  篮子数量
     * @param capacity 篮子容量
     * @param balls    球个数
     * @return
     */
    int countWays(int baskets, int capacity, int balls) {
        if (baskets * capacity < balls) {
            return 0;
        }
        return -1;
    }
}
