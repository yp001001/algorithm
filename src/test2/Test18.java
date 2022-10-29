package test2;

import java.util.*;

public class Test18 {

    public static void main(String[] args) {
        double pow = Math.pow(2, 8);
        System.out.println(pow);
    }

    /**
     * 扑克牌
     *
     * @param nums
     * @return
     */
    public boolean isStraight(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int max = -1;
        int min = 14;
        for (int num : nums) {
            if (num == 0) continue;
            if (set.contains(num)) return false;
            max = Math.max(max, num);
            min = Math.min(min, num);
            set.add(num);
        }
        return max - min < 5;
    }

    public String minNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        quickSort(strs, 0, strs.length - 1);
        StringBuilder res = new StringBuilder();
        for (String s : strs)
            res.append(s);
        return res.toString();
    }

    void quickSort(String[] strs, int l, int r) {
        if (l >= r) return;
        int i = l, j = r;
        String tmp = strs[i];
        while (i < j) {
            while ((strs[j] + strs[l]).compareTo(strs[l] + strs[j]) >= 0 && i < j) j--;
            while ((strs[i] + strs[l]).compareTo(strs[l] + strs[i]) <= 0 && i < j) i++;
            tmp = strs[i];
            strs[i] = strs[j];
            strs[j] = tmp;
        }
        strs[i] = strs[l];
        strs[l] = tmp;
        quickSort(strs, l, i - 1);
        quickSort(strs, i + 1, r);
    }


    // 构建二叉树
//    private Map<Integer, Integer> dict;
//    int[] preorder;
//
//    public TreeNode buildTree(int[] preorder, int[] inorder) {
//        int n = preorder.length;
//        this.preorder = preorder;
//        dict = new HashMap<>();
//        for (int i = 0; i < n; i++) {
//            dict.put(inorder[i], i);
//        }
//        return recur(0, 0, inorder.length - 1);
//    }
//
//    TreeNode recur(int root, int left, int right) {
//        if (left > right) return null;
//        TreeNode node = new TreeNode(preorder[root]);
//        int i = dict.get(preorder[root]);  // 得到中序遍历的根节点位置
//        node.left = recur(root + 1, left, i - 1);
//        node.right = recur(root + i - left + 1, i + 1, right);
//        return node;
//    }


}
