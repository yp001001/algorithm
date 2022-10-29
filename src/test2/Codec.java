package test2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Codec {

    public static void main(String[] args) {
        Codec codec = new Codec();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        System.out.println(codec.serialize(root));
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        reserialize(root, sb);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        LinkedList<String> linkedList = new LinkedList<>();
        String[] split = data.split(",");
        linkedList.addAll(Arrays.asList(split));
        return deserialize(linkedList);
    }

    public void reserialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("None" + ",");
            return;
        }
        sb.append(root.val + ",");
        reserialize(root.left, sb);
        reserialize(root.right, sb);
    }

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