package niuke;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test07 {

    /**
     * 对给定的二叉树依次完成前序，中序，后序遍历，并输出遍历结果
     * @param input int整型一维数组 -1表示Nil节点
     * @return int整型ArrayList<ArrayList<>>
     */
    public ArrayList<ArrayList<Integer>> binaryTreeScan (int[] input) {
        // write code here
        ArrayList<Integer> pre=new ArrayList<Integer>();
        ArrayList<Integer> in=new ArrayList<Integer>();
        ArrayList<Integer> last=new ArrayList<Integer>();
        preSort(input,pre,0);
        inSort(input,in,0);
        lastSort(input,last,0);
        ArrayList<ArrayList<Integer>> list=new ArrayList<ArrayList<Integer>>();
        list.add(pre);
        list.add(in);
        list.add(last);
        return list;
    }
    public static void preSort(int[] input, List<Integer> pre, int i){
        if(i>input.length-1){
            return;
        }
        if(input[i]!=-1){
            pre.add(input[i]);
        }
        preSort(input,pre,2*i+1);
        preSort(input,pre,2*i+2);
    }
    public static void inSort(int[] input,List<Integer> in, int i){
        if(i>input.length-1){
            return;
        }
        inSort(input,in,2*i+1);
        if(input[i]!=-1){
            in.add(input[i]);
        }
        inSort(input,in,2*i+2);
    }
    public static void lastSort(int[] input, List<Integer> last, int i){
        if(i>input.length-1){
            return;
        }
        lastSort(input,last,2*i+1);
        lastSort(input,last,2*i+2);
        if(input[i]!=-1){
            last.add(input[i]);
        }
    }


    /**
     * @param peoples string字符串
     * @return int整型
     */
    public static int lineup(String peoples) {
        // write code here
        // G放在最左边
        char[] str = peoples.toCharArray();
        int index = 0;
        int leftStep = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == 'G') {
                leftStep += i - index;
                index++;
            }
        }
        index = str.length - 1;
        int rightStep = 0;
        for (int i = str.length - 1; i >= 0; i--) {
            if (str[i] == 'G') {
                rightStep += index - i;
                index--;
            }
        }
        return Math.min(leftStep, rightStep);
    }


    /**
     * 删除重复元素
     *
     * @param array int整型一维数组
     * @return int整型一维数组
     */
    public int[] removeDuplicate(int[] array) {
        // write code here
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : array) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }
        int[] ans = new int[map.size()];
        int index = 0;
        for (int a : array) {
            if (map.get(a) == 1) {
                ans[index++] = a;
            } else {
                map.put(a, map.get(a) - 1);
            }
        }
        return ans;
    }


    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String ans = strs[0];
        for (int i = 1; i < strs.length; i++) {
            ans = prefixStr(ans, strs[i]);
        }
        return ans;
    }

    private static String prefixStr(String s1, String s2) {
        StringBuilder sb = new StringBuilder();
        int n = s1.length() > s2.length() ? s2.length() : s1.length();
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) != s2.charAt(i)) break;
            sb.append(s1.charAt(i));
        }
        return sb.toString();
    }

}
