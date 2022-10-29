package com.yp.tree.queue;

public class MaxPriorityQueueTest {
    public static void main(String[] args) {
        MaxPriorityQueue<Person> queue = new MaxPriorityQueue<Person>(10);
        queue.insert(new Person(10, "张三"));
        queue.insert(new Person(11, "李四"));
        queue.insert(new Person(9, "王五"));
        queue.insert(new Person(8, "赵六"));
        queue.insert(new Person(12, "小⑦"));
        queue.insert(new Person(7, "小⑧"));

        while (!queue.isEmpty()) {
            System.out.println(queue.delMax());
        }

    }
}


// age相当于优先级
class Person implements Comparable<Person> {

    int age;
    String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(Person person) {
        return this.age - (person.age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
