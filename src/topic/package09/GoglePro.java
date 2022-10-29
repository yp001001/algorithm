package topic.package09;

// 面值为1-10的牌组成一组
// 每次从组里等概率的抽出一张，无限组，求 17 <= x <  21 获胜的概率
public class GoglePro {

    public static double f1() {
        return p1(0);
    }

    private static double p1(int cur) {
        if (cur >= 17 && cur < 21) return 1.0;
        if (cur >= 21) return 0.0;
        double w = 0.0;
        for (int i = 1; i <= 10; i++) {
            w = p1(cur + i);
        }
        return w / 10;
    }

}
