package everyday;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Test64 {

    public static void main(String[] args) {
        getKthMagicNumber(8);
    }

    public static int getKthMagicNumber(int k) {
        int[] factors = {3, 5, 7};
        Set<Long> seen = new HashSet<>();
        PriorityQueue<Long> heap = new PriorityQueue<>();
        heap.offer(1L);
        seen.add(1L);
        long ugly = 0;
        for (int i = 0; i < k; i++) {
            ugly = heap.poll();
            long curr = ugly;
            for (int factor : factors) {
                long next = curr * factor;
                if (seen.add(next)) {
                    heap.offer(next);
                }
            }
        }
        return (int) ugly;
    }

}
