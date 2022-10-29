package topic.package37;

import everyday.TreeNode;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.cn/problems/path-sum-iii/
public class PathSum {

    // 前缀和问题
    public int pathSum(TreeNode root, int targetSum) {
        // 保存前缀和的个数
        Map<Long, Integer> prefixMap = new HashMap<>();
        prefixMap.put(0L, 1);
        return process(root, targetSum, 0L, prefixMap);
    }

    private int process(TreeNode root, int targetSum, long curVal, Map<Long, Integer> prefixMap) {
        if (root == null) return 0;
        curVal += root.val;
        int ret = prefixMap.getOrDefault(curVal - targetSum, 0);
        prefixMap.put(curVal, prefixMap.getOrDefault(curVal, 0) + 1);
        ret += process(root.left, targetSum, curVal, prefixMap);
        ret += process(root.right, targetSum, curVal, prefixMap);
        prefixMap.put(curVal, prefixMap.get(curVal) - 1);
        return ret;
    }

}
