package com.yp.sort;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

// 基数排序
public class RadixSort {
    public static void main(String[] args) throws Exception {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);
        long nameOffest = unsafe.objectFieldOffset(Cat.class.getDeclaredField("name"));
        Cat cat = new Cat();
        cat.setName("张三");
        System.out.println(cat);
        // CAS交换
        unsafe.compareAndSwapObject(cat, nameOffest, "张三", "李四");
        System.out.println(cat);
    }
}

class Cat {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                '}';
    }
}
