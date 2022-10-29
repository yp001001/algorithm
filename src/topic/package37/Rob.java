package topic.package37;

import everyday.TreeNode;

import java.util.HashMap;
import java.util.Map;

// 打家劫舍III
public class Rob {

    // 备忘录
    Map<TreeNode, Integer> map = new HashMap<>();

    public int rob(TreeNode root) {
        if (root == null) return 0;
        if (map.containsKey(root)) return map.get(root);
        int do_it = root.val
                + (root.left == null ? 0 : rob(root.left.left) + rob(root.left.right))
                + (root.right == null ? 0 : rob(root.right.right) + rob(root.right.left));
        int not_it = rob(root.left) + rob(root.right);
        int ans = Math.max(do_it, not_it);
        map.put(root, ans);
        return ans;
    }

}
