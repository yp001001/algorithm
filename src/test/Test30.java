package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Test30 {
    public static void main(String[] args) {
        Test30 test30 = new Test30();
        List<String> list = test30.generateParenthesis(3);
        System.out.println(list);
    }


    /**
     * 含有重复元素集合的全排列
     *
     * @param nums
     * @return
     */
    boolean[] marked;
    List<List<Integer>> res;
    List<Integer> list;

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        int size = nums.length;
        marked = new boolean[size];
        res = new ArrayList<>();
        list = new ArrayList<>();
        dfs(nums, 0);
        return res;
    }

    private void dfs(int[] nums, int path) {
        if (path > nums.length) return;
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 保证每个数字只被填入一次
            if (marked[i] || (i > 0 && nums[i] == nums[i - 1] && !marked[i - 1])) continue;
            list.add(nums[i]);
            marked[i] = true;
            dfs(nums, i + 1);
            list.remove(list.size() - 1);
            marked[i] = false;
        }
    }

    /**
     * 生成匹配的括号
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        dfs(res, n, n, "");
        return res;
    }

    /**
     * @param res
     * @param s
     * @param lt  左边括号剩余个数
     * @param rt  右边括号剩余个数
     */
    private void dfs(List<String> res, int lt, int rt, String s) {
        if (lt == 0 && rt == 0) {
            res.add(s);
            return;
        }
        if (lt == rt) {   // 左括号与右括号相等，我们必须加入左括号
            dfs(res, lt - 1, rt, s + "(");
        } else {
            if (lt > 0) {
                dfs(res, lt - 1, rt, s + "(");
            }
            dfs(res, lt, rt - 1, s + ")");
        }
    }

    /**
     * 判断是否正确
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(s.charAt(i));
            }
        }
        return stack.size() == 0;
    }


    /*============================================**/

    /**
     * 字符串中的单词数
     *
     * @param s
     * @return
     */
    public int countSegments(String s) {
        if (s.length() == 0) return 0;
        int n = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i > 0 && s.charAt(i) == ' ' && s.charAt(i - 1) != ' ') {
                n++;
            }
        }
        if (s.charAt(s.length() - 1) == ' ') return n;
        return n + 1;
    }


    /**
     * 分割回文子串
     *
     * @param s
     * @return
     */
    public String[][] partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> list = new ArrayList<>();
        dfs(res, list, s, 0);
        String[][] array = new String[res.size()][];
        int idx = 0;
        for (List<String> re : res) {
            String[] str = re.toArray(new String[]{});
            array[idx++] = str;
        }
        return array;
    }

    private void dfs(List<List<String>> res, List<String> list, String s, int start) {
        if (start > s.length()) return;
        if (start == s.length()) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (!isValid_2(s.substring(start, i + 1))) continue;    // 如果不是回文子串，返回
            list.add(s.substring(start, i + 1));
            dfs(res, list, s, i + 1);
            list.remove(list.size() - 1);
        }
    }


    /**
     * 判断一个字符串是否是回文串
     *
     * @param s
     * @return
     */
    public boolean isValid_2(String s) {
        if (s == "") return false;
        int len = s.length();
        for (int i = 0, j = len - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) return false;
        }
        return true;
    }
}
