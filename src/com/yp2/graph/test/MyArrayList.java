package com.yp2.graph.test;

import java.lang.invoke.MethodHandle;
import java.util.*;

public class MyArrayList<T> extends ArrayList<T> {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(0);
        MyArrayList list = new MyArrayList();
        boolean structure = list.isSubStructure(root, new TreeNode());
        System.out.println(structure);

    }


    private static TreeNode dfs2(TreeNode root) {
        if (root == null) {
            return null;
        }
        root.left = dfs2(root.left);
        root.right = dfs2(root.right);
        if (root.left == null && root.right == null && root.val == 0) {
            return null;
        }
        return root;
    }

    //================


    static int ans = 0; // 辅助变量

    public static int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left, right;
        left = dfs(root.left);
        right = dfs(root.right);
        ans += Math.abs(left - right); // 记录数据
        return left + right + root.val;
    }


    public boolean isCousins(TreeNode root, int x, int y) {
        // 层序遍历
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> all = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            all.add(list);
        }
        for (List<Integer> l : all) {
            if (l.contains(x) && l.contains(y)) {
                return true;
            }
        }
        return false;

    }

    //=====================
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        return (A != null && B != null) && (isTrue(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B));
    }

    /**
     * 查看两棵树是否相等
     *
     * @param a
     * @param b
     * @return
     */
    private boolean isTrue(TreeNode a, TreeNode b) {
//        if (b == null) return true;
//        if (a == null || a.val != b.val) return false;
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }


        return a.val == b.val && isTrue(a.left, b.left) && isTrue(a.right, b.right);
    }

    /**
     * //====================构造树========================
     **/
    HashMap<Integer, Integer> map = new HashMap<>();//标记中序遍历
    int[] preorder;//保留的先序遍历，方便递归时依据索引查看先序遍历的值

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        //将中序遍历的值及索引放在map中，方便递归时获取左子树与右子树的数量及其根的索引
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        //三个索引分别为
        //当前根的的索引
        //递归树的左边界，即数组左边界
        //递归树的右边界，即数组右边界
        return recur(0, 0, inorder.length - 1);
    }

    TreeNode recur(int pre_root, int in_left, int in_right) {
        if (in_left > in_right) return null;// 相等的话就是自己
        TreeNode root = new TreeNode(preorder[pre_root]);//获取root节点
        int idx = map.get(preorder[pre_root]);//获取在中序遍历中根节点所在索引，以方便获取左子树的数量
        //左子树的根的索引为先序中的根节点+1
        //递归左子树的左边界为原来的中序in_left
        //递归左子树的右边界为中序中的根节点索引-1
        root.left = recur(pre_root + 1, in_left, idx - 1);
        //右子树的根的索引为先序中的 当前根位置 + 左子树的数量 + 1
        //递归右子树的左边界为中序中当前根节点+1
        //递归右子树的右边界为中序中原来右子树的边界
        root.right = recur(pre_root + (idx - in_left) + 1, idx + 1, in_right);
        return root;

    }
}

/**
 * //====================构造树========================
 **/

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
