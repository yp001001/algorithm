package com.yp.search;

import java.util.ArrayList;
import java.util.List;

/*
二分查找法
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 8, 10, 89, 1000, 1000, 1234};
        List<Integer> indexs = binarySearchs(arr, 0, arr.length - 1, 1000);
        System.out.println(indexs);
    }

    // 只能查找一个
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        if (arr[mid] > findVal) {
            return binarySearch(arr, left, mid - 1, findVal);
        } else if (arr[mid] < findVal) {
            return binarySearch(arr, mid + 1, right, findVal);
        } else {
            return mid;
        }
    }

    // 查找符合要求的多个下标
    public static List<Integer> binarySearchs(int[] arr, int left, int right, int findVal) {
        ArrayList<Integer> list = new ArrayList<>();
        if (left > right) {
            return null;
        }
        int mid = (left + right) / 2;
        if (arr[mid] > findVal) {
            return binarySearchs(arr, left, mid - 1, findVal);
        } else if (arr[mid] < findVal) {
            return binarySearchs(arr, mid + 1, right, findVal);
        } else {
            list.add(mid);
            int l = mid - 1;
            int r = mid + 1;
            while (l >= left && arr[l] == findVal) {
                list.add(l);
                l--;
            }
            while (r <= right && arr[r] == findVal) {
                list.add(r);
                r++;
            }
            return list;
        }
    }
}
