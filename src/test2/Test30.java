package test2;


import java.util.*;

public class Test30 {

    public static void main(String[] args) {
//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(0);
//        root.right = new TreeNode(1);
//        root.left.left = new TreeNode(0);
//        root.left.right = new TreeNode(1);
//        root.right.left = new TreeNode(0);
//        root.right.right = new TreeNode(1);
//        Test30 test30 = new Test30();
//        int symmetric = test30.sumRootToLeaf(root);
//        System.out.println(symmetric);

        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(6);
        root.left.right.left = new TreeNode(5);
        root.left.right.right = new TreeNode(7);
        Test30 test30 = new Test30();
        test30.deleteNode(root, 4);
    }


    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res, 0);
        return res;
    }

    int high = 0;

    private void dfs(TreeNode root, List<Integer> res, int h) {
        if (root == null) return;
        if (high <= h) {
            res.add(root.val);
            high++;
        }
        dfs(root.right, res, h + 1);
        dfs(root.left, res, h + 1);
    }

    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        int minDepth = Integer.MAX_VALUE;
        if (root.left != null) {
            minDepth = Math.min(minDepth, minDepth(root.left));
        }
        if (root.right != null) {
            minDepth = Math.min(minDepth, minDepth(root.right));
        }
        return minDepth + 1;
    }


    // 二叉树的最近公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return dfs(root, p, q);
    }

    TreeNode dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == q || root == p) return root;
        TreeNode left = dfs(root.left, p, q);
        TreeNode right = dfs(root.right, p, q);
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

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetric(root.left, root.right);
    }

    boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        return left.val == right.val && isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }


    // 从根到叶的二进制之和
    List<String> list = new ArrayList<>();

    public int sumRootToLeaf_low(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        dfs(root, sb);
        System.out.println(list);
        return mergeBinaryNum(list);
    }

    private int mergeBinaryNum(List<String> list) {
        int sum = 0;
        for (String s : list) {
            sum += binaryToTen(s);
        }
        return sum;
    }

    private int binaryToTen(String num) {
        int n = num.length();
        int sum = 0;
        int pow = 1;
        while (n > 0) {
            sum += (num.charAt(n - 1) - '0') * pow;
            n--;
            pow *= 2;
        }
        return sum;
    }

    private void dfs(TreeNode root, StringBuilder sb) {
        if (root == null) return;
        sb.append(root.val);
        if (root.left == null && root.right == null) {
            list.add(sb.toString());
        }
        dfs(root.left, sb);
        dfs(root.right, sb);
        sb.deleteCharAt(sb.length() - 1);
    }

    //=============================================== 使用 递归 + 位运算 ==========================
    public int sumRootToLeaf_dfs(TreeNode root) {
        return dfs(root, 0);
    }

    int dfs(TreeNode root, int val) {
        if (root == null) return 0;
        val = val << 1 | root.val;
        if (root.left == null && root.right == null) {
            return val;
        }
        return dfs(root.left, val) + dfs(root.right, val);
    }

    //=============================================== BFS ==========================
    public int sumRootToLeaf(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        int val = 0, ret = 0;
        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                val = (val << 1) | root.val;
                stack.push(root);
                root = root.left;
            }
            root = stack.peek();
            if (root.right == null || root.right == prev) {
                if (root.left == null && root.right == null) {
                    ret += val;
                }
                val >>= 1;
                stack.pop();
                prev = root;
                root = null;
            } else {
                root = root.right;
            }
        }
        return ret;
    }


    //==================================二叉树的前序遍历（BFS）=============================
    public void prevTree(TreeNode root) {
        if (root == null) return;
        List<TreeNode> list = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                list.add(node);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
    }

    //===================================将树转换成链表================================
    public void flatten(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode next = curr.left;
                TreeNode predecessor = next;
                // 找到节点的子树的最右子节点
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                predecessor.right = curr.right;
                curr.left = null;
                curr.right = next;
            }
            curr = curr.right;
        }
    }

    //===================================验证二叉搜索树================================
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode root, long minValue, long maxValue) {
        if (root == null) return true;
        if (root.val <= minValue || root.val >= maxValue) return false;
        return isValidBST(root.left, minValue, root.val) && isValidBST(root.right, root.val, maxValue);
    }


    //===================================二叉搜索树与双向链表================================
    Node prev, head;

    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        dfs(root);
        head.left = prev;
        prev.right = head;
        return head;
    }

    void dfs(Node root) {
        if (root == null) return;
        dfs(root.left);
        if (prev == null) {
            head = root;
        } else {
            prev.right = root;
            root.left = prev;
        }
        prev = root;
        dfs(root.right);
    }


    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }


    //===================================把二叉搜索树转换成累加树================================
    public TreeNode convertBST(TreeNode root) {
        if (root == null) return null;
        dfs(root);
        return root;
    }

    int sum = 0;

    void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.right);
        sum += root.val;
        dfs(root.left);
        root.val = sum;
    }

    //===================================二叉树中的最大路径和（题目意思不明白...）================================

    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // 递归计算左右子节点的最大贡献值
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
        int priceNewpath = node.val + leftGain + rightGain;
        maxSum = Math.max(maxSum, priceNewpath);
        return node.val + Math.max(leftGain, rightGain);
    }


    //===================================删除二叉树中的节点，保持二叉搜索树的特点================================

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        }
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
        }
        if (root.val == key) {
            if (root.left == null && root.right == null) return null;
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            TreeNode dummy = root.right;
            while (dummy.left != null) {
                dummy = dummy.left;
            }
            root.right = deleteNode(root.right, dummy.val);
            dummy.left = root.left;
            dummy.right = root.right;
            return dummy;
        }
        return root;
    }
}
