package zuo.package04;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Greed {

    public static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);
        }
    }

    // 求出字符串数组拼接的最小字符串
    public static String lowestString(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        Arrays.sort(strs, new MyComparator());
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str);
        }
        return sb.toString();
    }

    public static int lessMoney(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        return process(arr, 0);
    }

    private static int process(int[] arr, int pre) {
        if (arr.length == 0) return pre;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                ans = Math.min(ans, process(copyAndMergeTwo(arr, i, j), pre + arr[j] + arr[i]));
            }
        }
        return ans;
    }

    // 拷贝
    private static int[] copyAndMergeTwo(int[] arr, int i, int j) {
        return null;
    }

    public static int lessMoney2(int[] arr) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int a : arr) {
            queue.add(a);
        }
        int lessMoney = 0;
        int cur = 0;
        while (!queue.isEmpty()) {
            cur = queue.poll() + queue.poll();
            lessMoney += cur;
            queue.add(cur);
        }
        return lessMoney;
    }


    // 花费更少
    public static class SmallComparator implements Comparator<Progrom> {
        @Override
        public int compare(Progrom o1, Progrom o2) {
            return o1.c - o2.c;
        }
    }

    // 收益更高
    public static class BigComparator implements Comparator<Progrom> {
        @Override
        public int compare(Progrom o1, Progrom o2) {
            return o2.p - o1.p;
        }
    }


    // 点灯笼
    public static int lightLantern(String road) {
        char[] array = road.toCharArray();
        int i = 0, light = 0;
        while (i < array.length) {
            if (array[i] == 'x') {
                i++;
            } else {
                light++;
                if (i + 1 == array.length) break;
                else if (array[i + 1] == 'x') i += 2;
                else i += 3;
            }
        }
        return light;
    }


    // 获得最大利润
    public static int findMaximizedCopital(int k, int w, int[] profits, int[] coptial) {
        if (coptial == null || coptial.length == 0) return 0;
        PriorityQueue<Progrom> smallHeap = new PriorityQueue<>(new SmallComparator());
        PriorityQueue<Progrom> bigHeap = new PriorityQueue<>(new BigComparator());
        int source = w;
        for (int i = 0; i < coptial.length; i++) {
            Progrom progrom = new Progrom(profits[i], coptial[i]);
            smallHeap.add(progrom);
        }
        while (k > 0) {
            while (!smallHeap.isEmpty() && smallHeap.peek().c <= w) {
                bigHeap.add(smallHeap.poll());
            }
            Progrom progrom = bigHeap.poll();
//             如果w小于需要的支出，直接退出
            if (bigHeap.isEmpty()) break;
            w += progrom.p;
//            w大于或等于这些需要的支出，不需要再加入小根堆
//            while (!bigHeap.isEmpty()) {
//                smallHeap.add(bigHeap.poll());
//            }
            k--;
        }
        return w - source;
    }

    public static class Progrom {
        public int p;
        public int c;

        public Progrom(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }
}
