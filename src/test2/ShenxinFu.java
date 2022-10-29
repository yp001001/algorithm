package test2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ShenxinFu {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] params = br.readLine().split(" ");
        int x = Integer.parseInt(params[0]);
        int y = Integer.parseInt(params[1]);
        int z = Integer.parseInt(params[2]);
        System.out.println(dfs(x, y, z, n));
    }

    private static int dfs(int x, int y, int z, int rest) {
        if (rest == 0) {
            return 1;      // rest为0，凑出了一种合法的方案
        }
        if (rest < 0) return 0;       // rest小于0，当前摆放方案不合理
        // 分别尝试x,y,z作为本层的高度
        return dfs(x, y, z, rest - x) + dfs(x, y, z, rest - y) + dfs(x, y, z, rest - z);
    }


    public static void main4(String[] args) {
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int[] cuboid = new int[3];
        for (int i = 0; i < 3; i++) {
            cuboid[i] = sc.nextInt();
        }
        int result = dfs(cuboid, h, 0);
        System.out.println(result);
    }

    private static int dfs(int[] cuboid, int h, int path) {
        if (h < 0 || path >= cuboid.length) {
            return 0;
        }
        if (h == 0) {
            return 1;
        }
        int res = 0;
        res += dfs(cuboid, h - cuboid[path], path);
        res += dfs(cuboid, h, path + 1);
        return res;
    }


    public static void main6(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Map<String, List<Integer>> dict = new HashMap<>();
        for (int i = 0; i < n; i++) {
            sc.nextInt();
            sc.nextLine();
            String content = sc.nextLine();
            List<Integer> tmp = new ArrayList<>();
            if (dict.containsKey(content)) {
                tmp = dict.get(content);
            }
            tmp.add(i);
            dict.put(content, tmp);
        }
        TreeMap<Integer, List<Integer>> tmap = new TreeMap();
        for (String key : dict.keySet()) {
            List<Integer> value = dict.get(key);
            if (value.size() > 1) {
                tmap.put(value.get(0), value);
            }
        }
        for (int key : tmap.keySet()) {
            List<Integer> value = tmap.get(key);
            for (int t : value) {
                System.out.print(t + " ");
            }
            System.out.println();
        }
        if (tmap.size() == 0) {
            System.out.println("no");
        }
    }


    public static void main5(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] nums = new int[n][2];
        for (int i = 0; i < n; i++) {
            nums[i][0] = sc.nextInt();
            nums[i][1] = sc.nextInt();
        }
        Arrays.sort(nums, (x1, x2) -> {
            if (x1[0] == x2[0]) return x1[1] - x2[1];
            return x1[0] - x2[0];
        });
        int left = nums[0][0], right = nums[0][1];
        List<int[]> res = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            int a = nums[i][0], b = nums[i][1];
            if (right < a) {
                res.add(new int[]{left, right});
                left = a;
                right = b;
            } else {
                right = Math.max(right, b);
            }
        }
        res.add(new int[]{left, right});
        for (int[] re : res) {
            System.out.println(re[0] + " " + re[1]);
        }
    }


    static List<String> list = new ArrayList<>();

    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();    // 小球种类
        int n = sc.nextInt();    // 小球个数
        int[] nums = new int[k];
        for (int i = 0; i < k; i++) {
            nums[i] = sc.nextInt();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            sb.append(0);
        }
        dfs(nums, n, sb, 0);
        Collections.sort(list);
        for (String s : list) {
            System.out.println(s);
        }
    }

    static void dfs(int[] nums, int n, StringBuilder sb, int path) {
        if (n == 0) {
            list.add(sb.toString());
            return;
        }
        if (path == nums.length) return;
        if (nums[path] > 0) {
            nums[path] -= 1;
            char value = (char) (sb.charAt(path) + 1);
            sb.setCharAt(path, value);
            dfs(nums, n - 1, sb, path);
            value = (char) (sb.charAt(path) - 1);
            sb.setCharAt(path, value);
            nums[path] += 1;
        }
        dfs(nums, n, sb, path + 1);
    }

    /**
     * 判断是否能够匹配
     *
     * @param s
     * @param sub
     * @return
     */
    public static boolean isSubString(String s, String sub) {
        int left = 0, subLeft = 0;
        while (left < s.length() && subLeft < sub.length()) {
            char c1 = s.charAt(left), c2 = sub.charAt(subLeft);
            if (c1 == c2) {
                left++;
                subLeft++;
            } else if (c2 == '?') {
                return isSubString(s.substring(left + 1), sub.substring(subLeft + 1))
                        || isSubString(s.substring(left + 2), sub.substring(subLeft + 1))
                        || isSubString(s.substring(left + 3), sub.substring(subLeft + 1));
            } else {
                return false;
            }
        }
        return true;
    }
}
