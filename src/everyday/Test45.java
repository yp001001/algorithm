package everyday;

import java.util.*;

// https://leetcode.cn/problems/find-duplicate-subtrees/
public class Test45 {

    // 寻找重复子树
    Map<String, TreeNode> seen = new HashMap<>();
    Set<TreeNode> repeat = new HashSet<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        serialize(root);
        return new ArrayList<>(repeat);
    }



    public String serialize(TreeNode node) {
        if (node == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(node.val);
        sb.append("(");
        sb.append(serialize(node.left));
        sb.append(")(");
        sb.append(serialize(node.right));
        sb.append(")");
        String serial = sb.toString();
        if (seen.containsKey(serial)) {
            repeat.add(seen.get(serial));
        } else {
            seen.put(serial, node);
        }
        return serial;
    }

}
