package topic.package03;

import java.util.HashSet;

public class Problem05 {

    public static int types(String[] arr) {
        HashSet<Integer> types = new HashSet<>();
        for (String s : arr) {
            char[] str = s.toCharArray();
            int key = 0;
            for (int i = 0; i < str.length; i++) {
                key |= 1 << (str[i] - 'a');
            }
            types.add(key);
        }
        return types.size();
    }
}
