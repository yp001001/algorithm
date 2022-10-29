package zuo.package01.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Problem {

    public static class Line {
        public int start;
        public int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static class StartComparator implements Comparator<Line> {
        @Override
        public int compare(Line o1, Line o2) {
            return o1.end - o2.end;
        }
    }

    public static int maxCover(int[][] m) {
        Line[] lines = new Line[m.length];
        for (int i = 0; i < m.length; i++) {
            lines[i] = new Line(m[i][0], m[i][1]);
        }
        Arrays.sort(lines, new StartComparator());
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int max = 0;
        for (int i = 0; i < lines.length; i++) {
            while (!queue.isEmpty() && queue.peek().intValue() <= lines[i].start) {
                queue.poll();
            }
            queue.add(lines[i].end);
            max = Math.max(max, queue.size());
        }
        return max;
    }

    public static void main(String[] args) {
        Stu stu1 = new Stu("zhangsan", 20);
        Stu stu2 = new Stu("lisi", 21);
        PriorityQueue<Stu> queue = new PriorityQueue<>(new StuComparator());
        queue.add(stu1);
        queue.add(stu2);
        System.out.println(queue.peek());
        stu1.age = 30;
        System.out.println(queue.peek());
    }
}

class StuComparator implements Comparator<Stu> {
    @Override
    public int compare(Stu o1, Stu o2) {
        return o1.age - o2.age;
    }
}

class Stu {
    public String name;
    public int age;

    @Override
    public String toString() {
        return "Stu{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Stu(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
