package test.tree;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Test {

    static StringBuilder builder = new StringBuilder();

    public int maxProduct(String[] words) {
        int max = 0;
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (!hasSameChar2(words[i], words[j])) {
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }
        return max;
    }

    // 判断两个字符串是否有相等的数
    private boolean hasSameChar2(String s1, String s2) {
        int mask1 = 0;
        int mask2 = 0;
        for (int i = 0; i < s1.length(); i++) mask1 |= (1 << (s1.charAt(i) - 'a'));
        for (int i = 0; i < s2.length(); i++) mask2 |= (1 << (s2.charAt(i) - 'a'));
        return (mask1 & mask2) == 0;
    }


    public static void main(String[] args) {
        Test test = new Test();
        boolean flag = test.hasSameChar2("adxcaf", "c");
        System.out.println(flag);
    }

    // Brian Kernighan 算法，能将末尾的1变为0，直到该数为0
    private int countOnes(int x) {
        int ones = 0;
        while (x > 0) {
            x &= (x - 1);
            ones++;
        }
        return ones;
    }


    public int[] countBits(int n) {
        int[] result = new int[n + 1];
        result[0] = 0;
        for (int i = 1; i <= n; i++) {
            if (result[i - 1] % 10 == 0) {
                result[i] = result[i - 1] + 1;
            } else {
                if (result[i - 1] == 1) {
                    result[i] = 10;
                } else {
                    result[i] = (result[i - 1] - 1) * 10 + 1;
                }
            }
        }
        return result;
    }

    // “1(2(4))(3)”
    private void dfs(TreeNode root) {
        builder.append(root.val);

        if (root.left != null) {
            builder.append("(");
            dfs(root.left);
        }
        builder.append(")");

        if (root.right != null) {
            builder.append("(");
            dfs(root.right);
        }
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


