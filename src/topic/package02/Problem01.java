package topic.package02;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

public class Problem01 {

    // 难度升序，金钱降序
    public static class JobComparator implements Comparator<Job> {
        @Override
        public int compare(Job o1, Job o2) {
            return o1.hard != o2.hard ? o1.hard - o2.hard : o2.money - o1.money;
        }
    }

    public static class Job {
        int hard;
        int money;
    }


    public static int[] getMoneys(Job[] job, int[] ability) {
        Arrays.sort(job, new JobComparator());
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        treeMap.put(job[0].hard, job[0].money);
        Job pre = job[0];
        for (int i = 1; i < job.length; i++) {
            if (job[i].hard != pre.hard && job[i].money > pre.money) {
                pre = job[i];
                treeMap.put(pre.hard, pre.money);
            }
        }
        int[] ans = new int[ability.length];
        for (int i = 0; i < ability.length; i++) {
            Integer key = treeMap.floorKey(ability[i]);
            ans[i] = key != null ? treeMap.get(key) : 0;
        }
        return ans;
    }
}
