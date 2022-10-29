package zuo.dp.problem;

import java.util.*;

// O(1) 时间插入、删除和获取随机元素
public class RandomizedSet {

    Map<Integer, Integer> dict;
    List<Integer> list;
    Random r;

    public RandomizedSet() {
        dict = new HashMap<>();
        list = new ArrayList<>();
        r = new Random();
    }

    public boolean insert(int val) {
        if (dict.containsKey(val)) return false;
        list.add(val);
        dict.put(val, list.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        if (!dict.containsKey(val)) return false;
        int index = dict.get(val);
        Integer v = list.get(list.size() - 1);
        list.set(index, v);
        // 必须先put后删除
        dict.put(v, index);
        dict.remove(val);
        list.remove(list.size() - 1);
        return true;
    }

    public int getRandom() {
        return list.get(r.nextInt(list.size()));
    }
}
