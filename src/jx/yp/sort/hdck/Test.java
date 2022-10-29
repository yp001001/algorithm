package jx.yp.sort.hdck;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Test {
    static List<List<Integer>> all = new ArrayList<>();

    public static void main(String[] args) {
        Test test = new Test();
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        test.executor(new ArrayList<Integer>(), candidates, 8, 0);
        System.out.println(all);
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')' && !stack.isEmpty()) {
                stack.pop();
            } else {
                stack.push(s.charAt(i));
            }
        }
        return stack.size() == 0;
    }

    public void executor(List<Integer> list, int[] candidates, int target, int idx) {
        if (idx == candidates.length || target < 0) {
            return;
        }
        if (target == 0) {
            all.add(new ArrayList<Integer>(list));
            return;
        }
        // [10,1,2,7,6,1,5]
        list.add(candidates[idx]);
        executor(list, candidates, target - candidates[idx], idx + 1);
        list.remove(list.size() - 1);
        executor(list, candidates, target, idx + 1);
    }
}
