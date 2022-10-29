package topic.package36;

import java.util.*;


// https://leetcode.cn/problems/substring-with-concatenation-of-all-words/
public class FindSubstring {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            String s = randomStr(40);
            if (minOperations(s) != minOperations_2(s)) {
                System.out.println(s);
                System.out.println("oops");
                System.out.println(minOperations(s));
                System.out.println(minOperations_2(s));
                return;
            }
        }

        System.out.println(minOperations("laknerbdzcjfcgaejttfyknxlkxeimqaltvypbxa\n"));
        System.out.println(minOperations_2("laknerbdzcjfcgaejttfyknxlkxeimqaltvypbxa\n"));
    }

    public static String randomStr(int count) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        char[] str = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        for (int i = 0; i < count; i++) {
            int index = random.nextInt(26);
            sb.append(str[index]);
        }
        return sb.toString();
    }

    public static int minOperations_2(String str) {
        int[] nums = new int[26];
        for (int i = 0; i < str.length(); i++) {
            nums[str.charAt(i) - 'a']++;
        }
        int cnt = 0, kinds = 0;
        for (int i = 0; i < 26; i++) {
            cnt += nums[i] / 2;
            kinds += nums[i] % 2;
        }
        if (cnt + kinds <= 26) {
            return cnt;
        } else {
            int a = 26 - kinds;
            return 2 * cnt - a;
        }
    }

    public static int minOperations(String str) {
        // write code here
        int[] nums = new int[26];
        for (int i = 0; i < str.length(); i++) {
            nums[str.charAt(i) - 'a']++;
        }
        int cnt = 0, kinds = 0;
        for (int i = 0; i < 26; i++) {
            cnt += nums[i] / 2;     // 任意字符cnt
            kinds += nums[i] % 2;   // 保留字符kinds
        }
        if (cnt + kinds <= 26) return cnt;
        // 任意字符有多少个，就会执行多少次消除操作
        int a = 26 - kinds;
        return 2 * cnt - a;
    }


    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<Integer>();
        int m = words.length, n = words[0].length(), ls = s.length();
        for (int i = 0; i < n; i++) {
            if (i + m * n > ls) {
                break;
            }
            Map<String, Integer> differ = new HashMap<>();
            for (int j = 0; j < m; j++) {
                String word = s.substring(i + j * n, i + (j + 1) * n);
                differ.put(word, differ.getOrDefault(word, 0) + 1);
            }
            for (String word : words) {
                differ.put(word, differ.getOrDefault(word, 0) - 1);
                if (differ.get(word) == 0) {
                    differ.remove(word);
                }
            }
            for (int start = i; start < ls - m * n + 1; start += n) {
                if (start != i) {
                    String word = s.substring(start + (m - 1) * n, start + m * n);
                    differ.put(word, differ.getOrDefault(word, 0) + 1);
                    if (differ.get(word) == 0) {
                        differ.remove(word);
                    }
                    word = s.substring(start - n, start);
                    differ.put(word, differ.getOrDefault(word, 0) - 1);
                    if (differ.get(word) == 0) {
                        differ.remove(word);
                    }
                }
                if (differ.isEmpty()) {
                    res.add(start);
                }
            }
        }
        return res;
    }

}
