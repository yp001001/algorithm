package everyday;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Test54 {

    public static void main(String[] args) {
        sortColors(new int[]{2, 0, 2, 1, 1, 0});
    }

    public static double trimMean(int[] arr) {
        int n = arr.length;
        Arrays.sort(arr);
        int partialSum = 0;
        for (int i = n / 20; i < 19 * n / 20; i++) {
            partialSum += arr[i];
        }
        return partialSum / (n * 0.9);
    }


    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return process(preorder, 0, 0, preorder.length - 1, map);
    }

    private TreeNode process(int[] preorder, int root, int left, int right, Map<Integer, Integer> map) {
        if (left > right) return null;
        int rootIndex = map.get(preorder[root]);
        TreeNode node = new TreeNode(preorder[root]);
        node.left = process(preorder, root + 1, left, rootIndex - 1, map);
                                            // 根节点下标减去左子树长度
        node.right = process(preorder, root + rootIndex - left + 1, rootIndex + 1, right, map);
        return node;
    }


    public static void sortColors(int[] nums) {
        int ptr = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                swap(nums, ptr, i);
                ptr++;
            }
        }
        for (int i = ptr; i < nums.length; i++) {
            if (nums[i] == 1) {
                swap(nums, ptr, i);
                ptr++;
            }
        }
    }

    public static void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

}
