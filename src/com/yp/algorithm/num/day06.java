package com.yp.algorithm.num;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class day06 {
    public static void main(String[] args) {
//        int x = 1<<3;

//        System.out.println(x);
//        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        Iterator<Integer> iterator = linkedList.iterator();
        while(iterator.hasNext()){
            iterator.next();
        }
    }
}