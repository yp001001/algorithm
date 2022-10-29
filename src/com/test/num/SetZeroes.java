package com.test.num;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/*
给定一个 m x n 的矩阵，如果一个元素为 0 ，
则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
 */
public class SetZeroes {

    public static void main(String[] args) {
        int[][] nums = new int[][]{
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5}
        };
        setZeroes(nums);
    }


    public static void setZeroes(int[][] matrix) {
        int y = matrix.length;    // 纵
        int x = matrix[0].length; // 横
        boolean[] row = new boolean[x];
        boolean[] col = new boolean[y];

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (matrix[i][j] == 0) {
                    row[j] = true;
                    col[i] = true;
                }
            }
        }
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
