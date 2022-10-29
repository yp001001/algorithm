package zuo.package03;


import java.util.List;

// 递归就是判断base case，之后一直递归
public class Tree {

    public TreeNode deserialize(List<String> list) {
        if (list.isEmpty()) return null;
        String val = list.get(0);
        if ("None".equals(list.get(0))) {
            list.remove(0);
            return null;
        }
        list.remove(0);
        TreeNode root = new TreeNode(Integer.parseInt(val));
        root.left = deserialize(list);
        root.right = deserialize(list);
        return root;
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

