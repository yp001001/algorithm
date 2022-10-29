package com.test.tree;

import java.util.*;

/*
返回二叉树的前/后序遍历
 */
public class PreList {


    /*
    判断是否是有效的二叉搜索树
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode root, Long lower, Long upper) {
        if (root == null) {
            return true;
        }
        if (root.val <= lower || root.val >= upper) {
            return false;
        }
        return isValidBST(root.left, lower, (long) root.val) && isValidBST(root.right,  (long)root.val, upper);
    }


    /*
    将节点插入搜索二叉树，且插入后符合二叉搜索树的规范
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }


    /*
        给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。
        返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
    */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) return root;
        return root.val > val ? searchBST(root.left, val) : searchBST(root.right, val);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        invertTree(root);
    }

    /*
    给定一个二叉树，检查它是否是镜像对称的。
     */
    public boolean isSymmetric(TreeNode root) {
        return check(root, root);
    }


    public boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }

    /*
    使用队列判断是否为镜像对称
     */
    public boolean isSymmetric2(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        Queue<TreeNode> p = new LinkedList<>();
        q.offer(root);
        p.offer(root);
        while (!q.isEmpty() && !p.isEmpty()) {
            TreeNode n1 = q.poll();
            TreeNode n2 = p.poll();
            if (n1 == null && n2 == null) {
                continue;
            }
            if (n1 == null || n2 == null || n1.val != n2.val) {
                return false;
            }
            q.offer(n1.left);
            q.offer(n1.right);
            p.offer(n2.right);
            p.offer(n2.left);
        }
        return true;
    }


    /*
    求二叉树的最大深度
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = 0, r = 0, max;
        if (root.left != null) {
            l = maxDepth(root.left);
        }
        if (root.right != null) {
            r = maxDepth(root.right);
        }
        max = l > r ? l + 1 : r + 1;
        return max;
    }


    /*
    二叉树的层序遍历
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        ArrayList<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        ArrayList<Integer> arrayList;
        int size;
        while (!queue.isEmpty()) {
            size = queue.size();
            arrayList = new ArrayList<Integer>();
            TreeNode node;
            for (int i = 0; i < size; i++) {
                node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                arrayList.add(node.val);
            }
            list.add(arrayList);
        }
        return list;
    }


    /*
    使用迭代进行后序遍历
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // 判断该节点的右子节点是否为空/是否已经遍历了
            if (root.right == null || root.right == prev) {
                list.add(root.val);
                prev = root;
                root = null;
            } else {
                // 将抛出的节点放回
                stack.push(root);
                root = root.right;
            }
        }
        return list;
    }


    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        ArrayList<Integer> list = new ArrayList<>();
        preList(root, list);
        return list;
    }


    public void preList(TreeNode root, List<Integer> list) {
        list.add(root.val);
        if (root.left != null) {
            preList(root.left, list);
        }
        if (root.right != null) {
            preList(root.right, list);
        }
    }


    /*
    翻转二叉树
     */
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode right = root.right;
        root.right = invertTree(root.left);
        root.left = invertTree(right);
        return root;
    }

    /*
    给你二叉树的根节点 root 和一个表示目标和的整数 targetSum
    判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum。

    思路：
       递归
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }
}
