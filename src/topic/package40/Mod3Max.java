package topic.package40;

import java.util.Arrays;
import java.util.TreeSet;

/*
    给定一个arr，里面的数字都是0~9
    你可以随意使用arr中的数字，哪怕打乱顺序也行
    请拼出一个能被3整除的，最大的数字，用str形式返回
 */
public class Mod3Max {

    public static String max1(int[] arr) {
        Arrays.sort(arr);
        for (int l = 0, r = arr.length - 1; l < r; l++, r--) {
            int tmp = arr[l];
            arr[l] = arr[r];
            arr[r] = tmp;
        }
        StringBuilder builder = new StringBuilder();
        TreeSet<String> set = new TreeSet<>((a, b) -> Integer.valueOf(b).compareTo(Integer.valueOf(a)));
        process1(arr, 0, builder, set);
        return set.isEmpty() ? "" : set.first();
    }

    public static void process1(int[] arr, int index, StringBuilder builder, TreeSet<String> set) {
        if (index == arr.length) {
            if (builder.length() != 0 && Integer.valueOf(builder.toString()) % 3 == 0) {
                set.add(builder.toString());
            }
        } else {
            process1(arr, index + 1, builder, set);
            builder.append(arr[index]);
            process1(arr, index + 1, builder, set);
            builder.deleteCharAt(builder.length() - 1);
        }
    }

}
