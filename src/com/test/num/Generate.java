package com.test.num;

import java.util.ArrayList;
import java.util.List;

/*
杨辉三角
 */
public class Generate {


    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    list.add(1);
                } else {
                    list.add(lists.get(i - 1).get(j - 1) + lists.get(i - 1).get(j));
                }
            }
            lists.add(list);
        }
        return lists;
    }
}
