package everyday;

import java.util.*;

public class Test71 {

    // https://leetcode.cn/problems/build-an-array-with-stack-operations/
    public List<String> buildArray(int[] target, int n) {
        Set<Integer> set = new HashSet<>();
        for (int num : target) {
            set.add(num);
        }
        List<String> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i > target[target.length - 1]) break;
            res.add("Push");
            if (!set.contains(i)) {
                res.add("Pop");
            }
        }
        return res;
    }


    // https://leetcode.cn/problems/fruit-into-baskets/
    public static int totalFruit(int[] fruits) {
        if (fruits == null || fruits.length == 0) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        int left = 0, right = 0;
        int ans = 0;
        for (int i = 0; i < fruits.length; i++) {
            map.put(fruits[i], map.getOrDefault(fruits[i], 0) + 1);
            while (map.size() > 2) {
                int value = map.get(fruits[left]);
                map.put(fruits[left], value - 1);
                if (value == 1) map.remove(fruits[left]);
                left++;
            }
            right++;
            ans = Math.max(right - left, ans);
        }
        return ans;
    }


    // https://leetcode.cn/problems/possible-bipartition/solution/ke-neng-de-er-fen-fa-by-leetcode-solutio-guo7/
    public boolean possibleBipartition(int n, int[][] dislikes) {
        int[] fa = new int[n + 1];
        Arrays.fill(fa, -1);
        List<Integer>[] g = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] p : dislikes) {
            g[p[0]].add(p[1]);
            g[p[1]].add(p[0]);
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < g[i].size(); j++) {
                unit(g[i].get(0), g[i].get(j), fa);
                if (isconnect(i, g[i].get(j), fa)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void unit(int x, int y, int[] fa) {
        x = findFa(x, fa);
        y = findFa(y, fa);
        if (x == y) return;
        if (fa[x] < fa[y]) {
            int temp = x;
            x = y;
            y = temp;
        }
        fa[x] += fa[y];
        fa[y] = x;
    }

    // 判断两个节点的父亲是否相同
    public boolean isconnect(int x, int y, int[] fa) {
        x = findFa(x, fa);
        y = findFa(y, fa);
        return x == y;
    }

    // 寻找父亲节点
    public int findFa(int x, int[] fa) {
        return fa[x] < 0 ? x : (fa[x] = findFa(fa[x], fa));
    }


    public static void main(String[] args) {
        countStudents(new int[]{1, 1, 0, 0}, new int[]{0, 1, 0, 1});
    }


    // https://leetcode.cn/problems/number-of-students-unable-to-eat-lunch/
    public static int countStudents(int[] students, int[] sandwiches) {
        Queue<Integer> stus = new LinkedList<>();
        Queue<Integer> food = new LinkedList<>();
        for (int i = 0; i < students.length; i++) {
            stus.offer(students[i]);
        }
        for (int i = 0; i < sandwiches.length; i++) {
            food.offer(sandwiches[i]);
        }

        while (!stus.isEmpty() && !food.isEmpty()) {
            int size = stus.size();
            for (int i = 0; i < size; i++) {
                if (stus.peek() == food.peek()) {
                    stus.poll();
                    food.poll();
                } else {
                    stus.offer(stus.poll());
                }
            }
            if (size == stus.size()) break;
        }
        return stus.size();
    }


    // https://leetcode.cn/problems/k-th-symbol-in-grammar/
    public int kthGrammar(int n, int k) {
        if (n == 1) return 0;
        // 得到该节点父节点的值
        int ret = kthGrammar(n - 1, (k + 1) / 2);
        if (ret == 0) {
            // 判断是否左孩子
            return k % 2 == 1 ? 0 : 1;
        } else {
            return k % 2 == 1 ? 1 : 0;
        }
    }

}