package everyday;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Test33 {

    public int widthOfBinaryTree(TreeNode root) {
        int res = 1;
        List<Pair<TreeNode, Integer>> arr = new ArrayList<>();
        arr.add(new Pair<>(root, 1));
        while (!arr.isEmpty()) {
            List<Pair<TreeNode, Integer>> tmp = new ArrayList<>();
            for (Pair<TreeNode, Integer> pair : arr) {
                if (pair.getKey().left != null) {
                    tmp.add(new Pair<>(pair.getKey().left, pair.getValue() * 2));
                }
                if (pair.getKey().right != null) {
                    tmp.add(new Pair<>(pair.getKey().right, pair.getValue() * 2 + 1));
                }
            }
            res = Math.max(res, arr.get(arr.size() - 1).getValue() - arr.get(0).getValue() + 1);
            arr = tmp;
        }
        return res;
    }

}
