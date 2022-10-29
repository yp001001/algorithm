package niuke;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class Test03 {

    public static void main(String[] args) {
    }

    public long distinctNames(String[] ideas) {
        Set<String> set = new HashSet<>();
        for (String idea : ideas) {
            set.add(idea);
        }
        int count = 0;
        for (int i = 0; i < ideas.length; i++) {
            for (int j = i + 1; j < ideas.length; j++) {
                char[] arr1 = ideas[i].toCharArray();
                char[] arr2 = ideas[j].toCharArray();
                char c = arr1[0];
                arr1[0] = arr2[0];
                arr2[0] = c;
                if (!set.contains(new String(arr1)) && !set.contains(new String(arr2))) {
                    count++;
                }
            }
        }
        return count;
    }


    int answer = Integer.MAX_VALUE;
    Map<Integer, Integer> memo = new HashMap<>();

    public int distributeCookies(int[] cookies, int k) {
        int sum = 0;
        for (int cookie : cookies) {
            sum += cookie;
        }
        int avg = sum / k;
        int[] children = new int[k];
        backtrack(cookies, 0, children, avg);
        return answer;
    }

    void backtrack(int[] cookies, int path, int[] children, int avg) {
        if (path == cookies.length) {
            int max = 0;
            for (int child : children) {
                max = Math.max(max, child);
            }
            if (max - avg < answer - avg) {
                answer = max;
            }
            return;
        }
        int empty = 0;
        for (int c : children) {
            if (c == 0) empty++;
        }
        // 剩余饼干的数量小于还未分到饼干的儿童数量，直接返回
        if (empty > cookies.length - path) return;
        for (int i = 0; i < children.length; i++) {
            children[i] += cookies[path];
            backtrack(cookies, path + 1, children, avg);
            children[i] -= cookies[path];
        }
    }


    public double calculateTax(int[][] brackets, int income) {
        double res = 0;
        for (int i = 0; i < brackets.length; i++) {
            if (income <= 0) break;
            double money;
            if (i == 0) money = brackets[i][0];
            else money = brackets[i][0] - brackets[i - 1][0];
            double ctx = brackets[i][1];
            if (income >= money) {
                res += money * ctx / 100;
                income -= money;
            } else {
                res += income * ctx / 100;
                break;
            }
        }
        return res;
    }
}
