package test;

import java.util.HashMap;
import java.util.Map;

public class MapSum {

    /**
     * Initialize your data structure here.
     */
    Map<String, Integer> map;
    Map<String, Integer> preMap;

    public MapSum() {
        map = new HashMap<>();
        preMap = new HashMap<>();
    }

    public void insert(String key, int val) {
        int deVal = val - map.getOrDefault(key, 0);
        map.put(key, val);
        for (int i = 0; i < key.length(); i++) {
            String prefix = key.substring(0, i + 1);
            preMap.put(prefix, preMap.getOrDefault(prefix, 0) + deVal);
        }
    }

    public int sum(String prefix) {
        return preMap.get(prefix);
    }
}
