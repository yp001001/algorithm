package zuo.package11;

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
        int mid = l + (r - l) / 2;
        build(l, mid, rt << 1);
        build(mid + 1, r, rt << 1 | 1);
        pushUp(rt);
    }


    public void add(int L, int R, int C, int l, int r, int rt) {
        if (l <= L && r <= R) {
            sum[rt] += C * (r - l + 1);
            lazy[rt] += C;
            return;
        }
        int mid = (l + r) >> 1;
        pushDown(rt, mid - l + 1, r - mid);
        if (L <= mid) {
            add(L, R, C, l, mid, rt << 1);
        }
        if (R > mid) {
            add(L, R, C, mid + 1, r, rt << 1 | 1);
        }
        pushUp(rt);
    }

    public void update(int L, int R, int C, int l, int r, int rt) {
        if (l <= L && r <= R) {
            update[rt] = true;
            change[rt] = C;
            sum[rt] = (r - l + 1) * C;
            lazy[rt] = 0;
            return;
        }
        int mid = (l + r) >> 1;
        pushDown(rt, mid - l + 1, r - mid);
        if (L <= mid) {
            update(L, R, C, l, mid, rt << 1);
        }
        if (mid < R) {
            update(L, R, C, mid + 1, r, rt << 1 | 1);
        }
        pushUp(rt);
    }


    public int query(int L, int R, int l, int r, int rt) {
        if (L <= l && r <= R) {
            return sum[rt];
        }
        int mid = (l + r) >> 1;
        pushDown(rt, mid - l + 1, r - mid);
        int ans = 0;
        if (L <= mid) {
            ans += query(L, R, l, mid, rt << 1);
        }
        if (mid < R) {
            ans += query(L, R, mid + 1, r, rt << 1 | 1);
        }
        return ans;
    }

    private void pushDown(int rt, int ln, int rn) {
        if (update[rt]) {
            update[rt >> 1] = true;
            update[rt >> 1 | 1] = true;
            change[rt >> 1] = change[rt];
            change[rt >> 1 | 1] = change[rt];
            lazy[rt >> 1] = 0;
            lazy[rt >> 1 | 1] = 0;
            sum[rt >> 1] = change[rt] * ln;
            sum[rt >> 1 | 1] = change[rt] * rn;
            update[rt] = false;
        }
        if (lazy[rt] != 0) {
            sum[rt >> 1] += ln * lazy[rt];
            sum[rt >> 1 | 1] += rn * lazy[rt];
            lazy[rt >> 1] += lazy[rt];
            lazy[rt >> 1 | 1] += lazy[rt];
            lazy[rt] = 0;
        }
    }
}
