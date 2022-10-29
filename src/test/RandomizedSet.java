package test;

import java.util.*;

public class RandomizedSet {

    Map<Integer, Integer> map;
    List<Integer> arr;
    int size;
    Random rd = new Random();

    public RandomizedSet() {
        map = new HashMap<>();
        arr = new ArrayList<>();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        arr.add(val);
        map.put(val, size++);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        int index = map.get(val);       // 获取val的下标index
        int lastNum = arr.get(--size);  // 获取最后一个元素last
        arr.set(index, lastNum);  // 利用last覆盖index
        map.put(lastNum, index);  // 改变map中last的index
        map.remove(val);    // 删除map中的val
        arr.remove(size);   // 删除最后一个元素
        return true;
    }

    public int getRandom() {
        return arr.get(rd.nextInt(size));
    }
}
