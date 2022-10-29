package com.test.linkedlist;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapLruCache<K, V> extends LinkedHashMap<K, V> {

    private int capacity; // 缓存容量

    /*
    true  表示访问顺序
    false 表示插入顺序
     */
    public LinkedHashMapLruCache(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    /*
    该方法返回true时，就会移除该map中最老的键和值
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return super.size() > capacity;
    }

    /*
    LRU算法
     */
    public static void main(String[] args) {
        LinkedHashMapLruCache lruCache = new LinkedHashMapLruCache(3);
        lruCache.put(1, "a");
        lruCache.put(2, "b");
        lruCache.put(3, "c");
        System.out.println(lruCache.keySet());
        Object o = lruCache.get(1);
        lruCache.put(4,"d");
        System.out.println(lruCache.keySet());
    }
}
