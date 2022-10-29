package jx.yp.sort.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HappyNum_202 {


    public int getNext(int n){
        Map<Integer,Integer> map = new HashMap<>();
        int target = 0;
        int x;
        while(n != 0){
            // 获取n的每一位数字
            x = n % 10;
            n = n / 10;
            target += x*x;
        }
        return target;
    }

    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<Integer>();
        int num = getNext(n);
        while(!set.contains(num)){
            if(num==1){
                return true;
            }
            set.add(num);
            num = getNext(num);
        }
        return false;
    }
}
