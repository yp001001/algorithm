package com.yp.algorithm.num;

import java.util.Arrays;

/**
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数
 * <p>
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 * <p>
 * <p>
 * <p>
 * 这时，形参a是一个新的引用，指向c引用的内容，形参b是一个新的指向d的引用，
 * 也就是a、c同时指向内存中的一个位置，b、d同时指向内存中的另一个位置。
 * 那么此时在方法中交换的引用是a、b两个引用指向的地址，此时对c、d的引用、内容均没有影响，
 * 但，如果在方法中c.xxx = newValue，那么此时a、c共同指向的内存中的内容发生了改变。
 */
public class day03 {
    public static void main(String[] args) {
//        int num[] = new int[]{1, 2, 3, 4, 5, 6, 7};
//        System.out.println("mian:"+num);
//        rotate(num, 2);
//        System.out.println("main2:"+num);
//        for (int i = 0; i < num.length; i++) {
//            System.out.println(num[i]);
//        }
        Person person = new Person(0);
        test(person);
        System.out.println(person.getId());

        int i = 0;
        test2(i);
        System.out.println(i);
    }

    public static void rotate(int[] nums, int k) {
        int length = nums.length;
        if (k % length == 0) {
        } else {
            k = k % length;
            int num2[] = new int[nums.length];
            for (int i = 0; i < length; i++) {
                num2[(i + k) % length] = nums[i];
            }
            for (int i = 0; i < num2.length; i++) {
                nums[i] = num2[i];
            }
        }
    }


    public static boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }

    public static void test(Person person) {
        Person person1 = new Person(1);
        person = person1;
    }

    public static void test2(int i) {
        int x = 10;
        i = x;
    }
}

class Person {
    int id;

    public Person(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
