package com.yp.hash;

public class HashTab {
    private EmpList[] empList;
    private int size;

    public HashTab(int size) {
        this.size = size;
        empList = new EmpList[size];
        for (int i = 0; i < size; i++) {
            empList[i] = new EmpList();
        }
    }

    public void add(Emp emp) {
        int hashVal = hashVal(emp.no);
        empList[hashVal].add(emp);
    }

    public void list() {
        for (int i = 0; i < size; i++) {
            empList[i].list();
        }
    }

    public Emp get(int no) {
        int hashVal = hashVal(no);
        return empList[hashVal].get(no);
    }

    private int hashVal(int no) {
        return no % (size - 1);
    }
}


class EmpList {
    private Emp head;

    public void add(Emp emp) {
        if (head == null) {
            head = emp;
        } else {
            Emp temp = head;
            while (true) {
                if (temp.next == null) {
                    temp.next = emp;
                    break;
                }
            }
        }
    }

    public Emp get(int no) {
        Emp temp = head;
        while (temp != null) {
            if (temp.no == no) {
                return temp;
            }
        }
        return null;
    }

    public void list() {
        Emp temp = head;
        while (temp != null) {
            System.out.print(temp + "\t");
            temp = temp.next;
        }
        System.out.println();
    }
}


class Emp {
    int no;
    String name;
    Emp next;

    public Emp() {
    }

    public Emp(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
