package test2;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 序列化：序列化是将数据结构或对象转换为一系列位的过程，以便它可以存储在文件或内存缓冲区中，或通过网络连接链路传输，以便稍后在同一个或另一个计算机环境中重建。
 * 反序列化：把磁盘文件中的对象数据或网络节点的数据，恢复成对象的过程，（也就是将二进制数据流转换程数据结构或对象的过程）
 */
public class Codec2 {
    // Encodes a tree to a single string.

    public String serialize(TreeNode root) {
        return rserialize(root, "");
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] split = data.split(",");
        LinkedList<String> linkedList = new LinkedList<>(Arrays.asList(split));
        return drserialize(linkedList);
    }

    private TreeNode drserialize(LinkedList<String> linkedList) {
        if (linkedList.size() == 0) return null;
        String num = linkedList.get(0);
        if ("None".equals(num)) {
            linkedList.remove(0);
            return null;
        }
        linkedList.remove(0);
        TreeNode root = new TreeNode(Integer.parseInt(num));
        root.left = drserialize(linkedList);
        root.right = drserialize(linkedList);
        return root;
    }

    public String rserialize(TreeNode root, String res) {
        if (root == null) {
            res += "None,";
        } else {
            res += res.valueOf(root.val) + ",";
            res = rserialize(root.left, res);
            res = rserialize(root.right, res);
        }
        return res;
    }
}
