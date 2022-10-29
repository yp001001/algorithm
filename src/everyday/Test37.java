package everyday;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.cn/problems/pascals-triangle/
public class Test37 {

    public static void main(String[] args) {
        List<Integer> generate = generate(2);
        for (Integer integer : generate) {
            System.out.print(integer + " ");
        }
    }

    public static List<Integer> generate(int rowIndex) {
        if (rowIndex == 0) return null;
        List<Integer> res = new ArrayList<>(rowIndex);
        for (int i = 0; i <= rowIndex; i++) {
            for (int j = i - 1; j > 0; j--) {
                res.set(j, res.get(j - 1) + res.get(j));
            }
            res.add(1);
        }
        return res;
    }

}
