package test2;

public class Main {
    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        double median = medianFinder.findMedian();
        System.out.println(median);
    }
}
