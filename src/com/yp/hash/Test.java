package com.yp.hash;

public class Test {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(16);
        hashTab.add(new Emp(1,"张三"));
        hashTab.add(new Emp(16,"jack"));
        hashTab.add(new Emp(2,"李四"));
        hashTab.add(new Emp(3,"王五"));
        hashTab.add(new Emp(4,"赵六"));
        hashTab.list();
        Emp emp = hashTab.get(2);
        System.out.println(emp);
    }
}
