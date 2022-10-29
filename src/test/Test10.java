package test;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * UNION UNIONALL 区别
 */
public class Test10 implements RejectedExecutionHandler {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 1) {
            return 0;
        }
        int min;
        int max = 0;
        if (prices[0] > prices[1]) {
            min = prices[1];
        } else {
            max = prices[1] - prices[0];
            min = prices[0];
        }
        for (int i = 2; i < prices.length; i++) {
            max = Math.max(max, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return max;
    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        // 等待一段时间然后执行任务
        try {
            BlockingQueue<Runnable> queue = executor.getQueue();
            while (queue.size() == 5) {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(300));
            }
            executor.execute(r);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


