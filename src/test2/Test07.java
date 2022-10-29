package test2;

import java.util.*;

public class Test07 {

    public static void main(String[] args) {
        TreeNode A = new TreeNode(1);
        TreeNode B = new TreeNode(1);
        System.out.println(new Test07().isSubStructure(A, B));
    }


    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            Deque<Integer> list = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                if (res.size() % 2 == 1) {
                    list.offerFirst(node.val);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(new LinkedList<>(list));
        }
        return res;
    }


    /**
     * 最近公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        // 找到 p / q
        if (root == p || root == q) {
            return root;
        }
        // 左边遍历（将大问题划分为小问题）
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        if (left != null) {
            return left;
        }
        if (right != null) {
            return right;
        }
        return null;
    }

    /**
     * 最近公共祖先II，节点可能不在该树上
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor_2(TreeNode root, TreeNode p, TreeNode q) {
        RsNode rsNode = lowest(root, p, q);
        return rsNode.hasRight && rsNode.hasRight ? rsNode.node : null;
    }

    public RsNode lowest(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return new RsNode(false, false, null);
        }
        // 因为我们需要遍历到最后，所以此处不需要进行处理
        RsNode left = lowest(root.left, p, q);
        RsNode right = lowest(root.right, p, q);
        boolean left_exist = (left.hasLeft || right.hasRight || root == p);
        boolean right_exist = (left.hasRight || right.hasRight || root == q);
        if (root == p || root == q) {
            return new RsNode(false, false, root);
        }
        // 该点就是父节点
        if (left.node != null && right.node != null) {
            return new RsNode(left_exist, right_exist, root);
        }
        if (left.node != null) {
            return new RsNode(left_exist, right_exist, left.node);
        }
        if (right.node != null) {
            return new RsNode(left_exist, right_exist, right.node);
        }
        return new RsNode(left_exist, right_exist, null);
    }


    /**
     * 树的子结构（判断B是否是A的子结构）
     *
     * @param A
     * @param B
     * @return
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        return dfs(A, B);
    }

    boolean dfs(TreeNode A, TreeNode B) {
        if (A == null) {
            return false;
        }
        if (isLike(A, B)) return true;
        return dfs(A.left, B) || dfs(A.right, B);
    }

    boolean isLike(TreeNode A, TreeNode B) {
        if (A == null && B == null) {
            return true;
        }
        if (A == null || B == null || A.val != B.val) {
            return false;
        }
        return isLike(A.left, B.left) && isLike(A.right, B.right);
    }

}

class RsNode {
    TreeNode node;
    boolean hasLeft;
    boolean hasRight;

    public RsNode(boolean hasLeft, boolean hasRight, TreeNode node) {
        this.hasLeft = hasLeft;
        this.hasRight = hasRight;
        this.node = node;
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}