package niuke;

import everyday.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Test08 {

    public static void main(String[] args) {
        long[] res = FarmerNN(4, 6);
        System.out.println(Arrays.toString(res));
    }

    public static String NS_String(String s, int k) {
        // write code here
        char[] str = s.toCharArray();
        TreeSet<Character> set = new TreeSet<>();
        for (char c : str) {
            set.add(c);
        }
        char target = set.first();
        for (int i = 0; i < k; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < str.length; j++) {
                if (str[j] != target) {
                    sb.append(str[j]);
                }
            }
            if (set.size() == 0) break;
            set.remove(set.first());
            target = set.first();
            str = sb.toString().toCharArray();
        }
        return new String(str);
    }

    public static long[] FarmerNN(int n, long m) {
        // write code here
        long a = m / (n - 1);
        long b = m % (n - 1);
        long[] res = new long[n];
        for (int i = 0; i < n; i++) {
            if (i == 0) res[i] = (a + 1) / 2;
            else if (i == n - 1) res[i] = a / 2;
            else res[i] = a;
        }
        if(a % 2 == 0){
            for(int i = 0; i < b; i++){
                res[i]++;
            }
        } else {
            for(int i = n - 1; i >= n - b; i--){
                res[i]++;
            }
        }
        return res;
    }


    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param root TreeNode类
     * @return int整型
     */
    Map<String, Integer> subTree; // 存储所有的子树结构及对应的数量
    Map<String, Integer> treeNodeNumber; // 存储所有的子树所包含的节点数量
    public int maxSubTree (TreeNode root) {
        // write code here
        subTree = new HashMap<>();
        treeNodeNumber = new HashMap<>();
        dfs(root);
        int res = 0;
        for(String key: subTree.keySet()){
            //找同构的子树，所以子树结构的数量大于1
            if(subTree.get(key) > 1){
                res = Math.max(res, treeNodeNumber.get(key));
            }
        }
        return res;
    }

    public String dfs(TreeNode root){
        if(root == null){
            return "";
        }else{
            StringBuilder sb = new StringBuilder();
            sb.append("1").append(",");
            String left = dfs(root.left);
            String right = dfs(root.right);
            sb.append(left).append(",").append(right);
            String tree = sb.toString();
            subTree.put(tree, subTree.getOrDefault(tree, 0) + 1);
            treeNodeNumber.put(tree, treeNodeNumber.getOrDefault(left, 0) + treeNodeNumber.getOrDefault(right, 0) + 1);
            return tree;
        }
    }
}
