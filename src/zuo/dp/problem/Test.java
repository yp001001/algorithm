package zuo.dp.problem;

public class Test {
    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        randomizedSet.remove(0);
        randomizedSet.remove(0);
        randomizedSet.insert(0);
        randomizedSet.getRandom();
        randomizedSet.remove(0);
        boolean insert = randomizedSet.insert(0);
        System.out.println(insert);
    }
}
