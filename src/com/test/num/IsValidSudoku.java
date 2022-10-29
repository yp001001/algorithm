package com.test.num;

import java.util.HashSet;
import java.util.Set;

/*
有效数独
 */
public class IsValidSudoku {

    public static void main(String[] args) {
        char c = '0';
        int x = c;
        System.out.println(c);
    }

    public boolean isValidSudoku(char[][] board) {
        // 记录某行，某位数字是否已经被摆放
        boolean[][] row = new boolean[9][9];
        // 记录某列，某位数字是否已经被摆放
        boolean[][] col = new boolean[9][9];
        // 记录某 3*3 宫格内，某位数字是否已经被摆放
        boolean[][] block = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

            }
        }
        return false;
    }
}
