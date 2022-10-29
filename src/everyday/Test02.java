package everyday;

import java.util.ArrayList;
import java.util.List;

public class Test02 {

    public static void main(String[] args) {
        System.out.println(Integer.bitCount(3));
    }

    public List<String> readBinaryWatch(int turnedOn) {
        List<String> ans = new ArrayList<>();
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                if (Integer.bitCount(h) + Integer.bitCount(m) == turnedOn) {
                    ans.add(h + ":" + (m < 10 ? "0" : "") + m);
                }
            }
        }
        return ans;
    }
}
