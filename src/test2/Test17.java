package test2;


//"13994398560757036973 82482479389609"
//"13994398560757036973 82478249389609"
public class Test17 {

    public static void main(String[] args) {
        String s = new Test17().minNumber(new int[]{824, 938, 1399, 5607, 6973, 5703, 9609, 4398, 8247});
        System.out.println(s);
    }

    // 模拟竖式计算
    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        String ans = "0";
        int n = num1.length(), m = num2.length();
        for (int i = m - 1; i >= 0; i--) {
            StringBuilder sb = new StringBuilder();
            for (int j = i + 1; j < m; j++) {
                sb.append(0);
            }
            int add = 0;
            int y = num2.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int x = num1.charAt(j) - '0';
                int product = x * y + add;
                sb.append(product % 10);
                add = product / 10;
            }
            if (add > 0) {
                sb.append(add);
            }
            ans = addStrings(sb.reverse().toString(), ans);
        }
        return ans;
    }

    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int l1 = num1.length() - 1, l2 = num2.length() - 1;
        int prev = 0;
        while (l1 >= 0 || l2 >= 0) {
            int n = l1 >= 0 ? num1.charAt(l1--) - '0' : 0;
            int m = l2 >= 0 ? num2.charAt(l2--) - '0' : 0;
            sb.append((n + m + prev) % 10);
            prev = (n + m + prev) / 10;
        }
        if (prev > 0) {
            sb.append(prev);
        }
        return sb.reverse().toString();
    }

    // 把数组排成最小的数
    boolean[] marked;
    String ans = "";

    /**
     * 递归超时
     *
     * @param nums
     * @return
     */
    public String minNumber(int[] nums) {
        int n = nums.length;
        marked = new boolean[n];
        int size = 0;
        for (int num : nums) {
            size += String.valueOf(num).length();
        }
        StringBuilder sb = new StringBuilder();
        dfs(nums, 0, size, sb);
        return ans;
    }

    private void dfs(int[] nums, int path, int size, StringBuilder sb) {
        if (path > nums.length) return;
        if (sb.length() == size) {
            String s = sb.toString();
            if (isValid(ans, s)) {
                ans = s;
            }
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (marked[i]) continue;
            marked[i] = true;
            sb.append(nums[i]);
            dfs(nums, i + 1, size, sb);
            marked[i] = false;
            sb.delete(sb.length() - String.valueOf(nums[i]).length(), sb.length());
        }
    }

    // 返回值为true，表示 s1 > s2
    boolean isValid(String s1, String s2) {
        if ("".equals(s1)) return true;
        for (int i = 0; i < s1.length(); i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if (s1.charAt(i) > s2.charAt(i)) return true;
            if (s1.charAt(i) < s2.charAt(i)) return false;
        }
        return false;
    }


}
