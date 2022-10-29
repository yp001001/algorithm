package everyday;

// https://leetcode.cn/problems/check-if-a-word-occurs-as-a-prefix-of-any-word-in-a-sentence/
public class Test29 {

    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] strs = sentence.split("\\s+");
        int index = 1;
        for (String str : strs) {
            if (str.startsWith(searchWord)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    private boolean valid(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() < s2.length()) return false;
        for (int i = 0; i < s2.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) return false;
        }
        return true;
    }

}
