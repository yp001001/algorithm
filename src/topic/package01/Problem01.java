package topic.package01;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

public class Problem01 {

    // 给定一个有序数组，代表坐落在X轴上的点
    // 给定一个正数，代表绳子的长度
    // 返回绳子最多压中几个点
    public static int maxPoint(int[] arr, int k) {
        int l = 0, r = 0;
        int maxPoint = 0;
        while (r < arr.length) {
            while (r < arr.length && arr[r] - arr[l] <= k) {
                r++;
            }
            maxPoint = Math.max(maxPoint, r - l);
            l++;
        }
        return maxPoint;
    }

    // 统计文件夹中文件个数
    public static int getFukeNumbers(String folderPath) {
        File root = new File(folderPath);
        if (!root.exists()) return 0;
        if (!root.isDirectory() && !root.isFile()) {
            return 0;
        }
        if (root.isFile()) {
            return 1;
        }
        Queue<File> queue = new LinkedList<>();
        queue.offer(root);
        int files = 0;
        while (!queue.isEmpty()) {
            File file = queue.poll();
            for (File next : file.listFiles()) {
                if (next.isFile()) files++;
                if (next.isDirectory()) queue.offer(next);
            }
        }
        return files;
    }


    // 给定一个非负整数，返回 >= num，并且离num最近的2的某次方
    public static final int tableSizeFor(int n) {
        n--;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : n + 1;
    }


    // 给定字符串只包含两种字符‘G’和‘B’，只能相邻字符交换，求G全部放在左侧，B全放在右侧的最少交换次数
    public static final int getMinSwap(String s) {
        char[] arr = s.toCharArray();
        int l = 0;
        // 找到第一个B
        while (arr[l] == 'G') {
            l++;
        }
        int r = l + 1;
        int step = 0;
        while (r < arr.length) {
            if (arr[r] == 'G') {
                arr[l++] = 'G';
                arr[r++] = 'B';
                step += r - l;

            } else r++;
        }
        return step;
    }


    // 给定数组，可以在每个数字之前决定+或者-，但必须都参与，在给定一个数，求最后算出target的方法数
    public static final int findTargetSumWays(int[] arr, int target) {
        return process(arr, 0, target);
    }

    private static int process(int[] arr, int index, int target) {
        if (index == arr.length) {
            return target == 0 ? 1 : 0;
        }
        int ways = 0;
        ways += process(arr, index + 1, target + arr[index]);
        ways += process(arr, index + 1, target - arr[index]);
        return ways;
    }


    // 排序数组旋转求val是否存在
    public static int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) return mid;
            // 表示左边有序
            if (nums[l] <= nums[mid]) {
                if (nums[mid] > target && nums[l] <= target) {
                    r = mid - 1;
                } else l = mid + 1;
            } else { // 表示右边有序
                if (nums[mid] < target && target <= nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }


    // 山峰数组的顶部
    public int peakIndexInMountainArray(int[] arr) {
        int l = 1, r = arr.length - 2;
        int ans = l;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] >= arr[mid - 1]) {
                l = mid + 1;
                ans = mid;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }


    // 给定二维数组，任意位置触发，走上下左右四个方向，返回能走出来的最长的递增链长度
    public static int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int longestPath = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                longestPath = Math.max(process(matrix, i, j, dp), longestPath);
            }
        }
        return longestPath;
    }

    private static int process(int[][] matrix, int i, int j, int[][] dp) {
        if (dp[i][j] != 0)
            return dp[i][j];
        int up = i + 1 < matrix.length && matrix[i + 1][j] > matrix[i][j] ? process(matrix, i + 1, j, dp) : 0;
        int down = i - 1 >= 0 && matrix[i - 1][j] > matrix[i][j] ? process(matrix, i - 1, j, dp) : 0;
        int left = j - 1 >= 0 && matrix[i][j - 1] > matrix[i][j] ? process(matrix, i, j - 1, dp) : 0;
        int right = j + 1 < matrix[0].length && matrix[i][j + 1] > matrix[i][j] ? process(matrix, i, j + 1, dp) : 0;
        int ans = Math.max(Math.max(up, down), Math.max(left, right)) + 1;
        dp[i][j] = ans;
        return ans;
    }


    public static void main(String[] args) {
        longestIncreasingPath(new int[][]{{1, 2, 3}});
    }

}
