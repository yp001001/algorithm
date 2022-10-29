package zuo.dp.other;


/**
 * 线段树
 */
public class SegmentTree {
    // arr[]为原序列的信息从0开始，但在arr里是从1开始
    // sum[]模拟线段树维护区间和
    // lazy[]为累加和懒惰标记
    // change[]为更新的值
    // update[]为更新懒惰标记
    private int MAXN;
    private int[] arr;
    private int[] sum;
    private int[] lazy;
    private int[] change;
    private boolean[] update;

    public SegmentTree(int[] origin) {
        MAXN = origin.length + 1;
        arr = new int[MAXN];
        for (int i = 1; i < MAXN; i++) {
            arr[i] = origin[i - 1];
        }
        sum = new int[MAXN << 2];           // 某一个返回的累加和信息
        lazy = new int[MAXN << 2];          // 某一个范围没有往下传递的累加任务
        change = new int[MAXN << 2];        // 某一个范围没有更新操作的任务
        update = new boolean[MAXN << 2];    // 某一个返回更新任务更新成什么
    }

    public void pushUp(int rt) {
        sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
    }

    public void build(int l, int r, int rt) {
        if (l == r) {
            sum[rt] = arr[l];
            return;
        }
        int mid = (l + r) >> 1;
        build(l, mid, rt << 1);
        build(mid + 1, r, rt << 1 | 1);
        pushUp(rt);
    }
}
