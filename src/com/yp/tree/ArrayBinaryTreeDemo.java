package com.yp.tree;

public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree tree = new ArrayBinaryTree(arr);
        tree.preOrder(0);
    }
}


// 编写一个ArrayBinaryTree，实现顺序存储二叉树遍历
class ArrayBinaryTree {
    private int[] arr; //存储数据节点的数组

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    // 数组中序遍历
    public void midOrder(int index) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int l = index * 2 + 1;
        int r = index * 2 + 2;
        if (l < arr.length) {
            midOrder(l);
        }
        System.out.println(arr[index]);
        if (r < arr.length) {
            midOrder(r);
        }
    }

    // 可以进行重载（代码简洁）
    public void preOrder() {
        this.preOrder(0);
    }

    // 数组前序遍历
    // index表示遍历的数组下标
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            return;
        }
        System.out.println(arr[index]);
        // 左递归
        int l = index * 2 + 1;
        int r = index * 2 + 2;
        if (l < arr.length) {
            preOrder(l);
        }
        if (r < arr.length) {
            preOrder(r);
        }
    }
}
