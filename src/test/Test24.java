package test;

import java.util.*;

/**
 * mysql> explain select * from idx_table where b = 'b10000';
 * +----+-------------+-----------+-------+---------------+-----------+---------+------+-------+--------------------------+
 * | id | select_type | table     | type  | possible_keys | key       | key_len | ref  | rows  | Extra                    |
 * +----+-------------+-----------+-------+---------------+-----------+---------+------+-------+--------------------------+
 * |  1 | SIMPLE      | idx_table | index | NULL          | idx_a_b_c | 189     | NULL | 99974 | Using where; Using index |
 * +----+-------------+-----------+-------+---------------+-----------+---------+------+-------+--------------------------+
 * 1 row in set (0.00 sec)
 * <p>
 * mysql> alter table idx_table add gender char(2);
 * Query OK, 0 rows affected (3.50 sec)
 * Records: 0  Duplicates: 0  Warnings: 0
 * <p>
 * mysql> explain select * from idx_table where b = 'b10000';
 * +----+-------------+-----------+------+---------------+------+---------+------+-------+-------------+
 * | id | select_type | table     | type | possible_keys | key  | key_len | ref  | rows  | Extra       |
 * +----+-------------+-----------+------+---------------+------+---------+------+-------+-------------+
 * |  1 | SIMPLE      | idx_table | ALL  | NULL          | NULL | NULL    | NULL | 99929 | Using where |
 * +----+-------------+-----------+------+---------------+------+---------+------+-------+-------------+
 * 1 row in set (0.00 sec)
 */
public class Test24 {

    public static void main(String[] args) {

    }

    private static void test01() {
        //[10,5,-3,3,2,null,11,3,-2,null,1]
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(11);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        Test24 test24 = new Test24();
        int count = test24.pathSum_2(root, 3);
        System.out.println(count);
    }

    /**
     * 所有大于或等于节点的数之和
     * ***********中序遍历是递增的
     * ***********我们反向中序遍历
     *
     * @param root
     * @return
     */
    int sum = 0;

    public TreeNode convertBST_2(TreeNode root) {
        dfs_5(root);
        return root;
    }

    private void dfs_5(TreeNode root) {
        if (root == null) return;
        dfs_5(root.right);
        sum += root.val;
        root.val = sum;
        dfs_5(root.left);
    }

    /**
     * 所有大于或等于节点的数之和
     *
     * @param root
     * @return
     */
    Map<Integer, Integer> map = new HashMap<>();

    public TreeNode convertBST(TreeNode root) {
        putMap(root, root);
        dfs(root);
        return root;
    }

    private void dfs(TreeNode root) {
        if (root == null) return;
        root.val = map.get(root.val);
        dfs(root.left);
        dfs(root.right);
    }

    // 将数据保存到map中
    private void putMap(TreeNode root, TreeNode now) {
        if (now == null) return;
        if (!map.containsKey(now.val)) {
            map.put(now.val, dfs_4(root, now.val));
            putMap(root, now.left);
            putMap(root, now.right);
        }
    }

    private int dfs_4(TreeNode root, int val) {
        if (root == null) return 0;
        int sum = 0;
        if (root.val >= val) sum += root.val;
        sum += dfs_4(root.left, val);
        sum += dfs_4(root.right, val);
        return sum;
    }


    /**
     * 二叉搜索树的中序后继
     *
     * @param root
     * @param p
     * @return
     */
    TreeNode res;
    boolean flag;

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        flag = false;
        dfs(root, p);
        return res;
    }

    private void dfs(TreeNode root, TreeNode p) {
        if (root == null) return;
        dfs(root.left, p);
        if (flag && res == null) {
            res = root;
            return;
        }
        if (p.val == root.val) {
            flag = true;
        }
        dfs(root.right, p);
    }


    /**
     * 展平二叉搜索树
     *
     * @param root
     * @return
     */
    public TreeNode increasingBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Collections.sort(list);
        TreeNode res = new TreeNode();
        TreeNode temp = res;
        for (int i = 0; i < list.size(); i++) {
            temp.right = new TreeNode(list.get(i));
            temp = temp.right;
        }
        return res.right;
    }

    public void dfs(TreeNode root, List<Integer> list) {
        if (root == null) return;
        list.add(root.val);
        dfs(root.left, list);
        dfs(root.right, list);
    }


    /**
     * 路径总和（使用前缀和的方式）
     *
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum_2(TreeNode root, int targetSum) {
        // 保存前缀和 和 个数
        Map<Long, Integer> map = new HashMap<>();
        map.put(0L, 1);
        return dfs_2(root, map, 0, targetSum);
    }

    private int dfs_2(TreeNode root, Map<Long, Integer> prefix, long curr, int targetSum) {
        if (root == null) {
            return 0;
        }
        int ret = 0;
        curr += root.val;
        ret = prefix.getOrDefault(curr - targetSum, 0);
        prefix.put(curr, prefix.getOrDefault(curr, 0) + 1);
        ret += dfs_2(root.left, prefix, curr, targetSum);
        ret += dfs_2(root.right, prefix, curr, targetSum);
        // 右边的删除？
        prefix.put(curr, prefix.getOrDefault(curr, 0) - 1);
        return ret;
    }

    /**
     * 路径总和
     * 我们求出root节点是否符合要求，然后继续求root.left和root.right（枚举，即可得到所有的情况）
     *
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) return 0;
        int count = dfs(root, targetSum);
        count += pathSum(root.left, targetSum);
        count += pathSum(root.right, targetSum);
        return count;
    }

    /**
     * 这样需要从上到下寻找，从下到上寻找可能重复
     *
     * @param root
     * @param targetSum
     * @return
     */
    private int dfs(TreeNode root, int targetSum) {
        if (root == null) return 0;
        int count = 0;
        if (targetSum - root.val == 0) count++;
        count += dfs(root.left, targetSum - root.val);
        count += dfs(root.right, targetSum - root.val);
        return count;
    }


    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}


