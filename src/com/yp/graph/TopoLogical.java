package com.yp.graph;

import java.util.Stack;

/*
拓扑排序API设计
 */
public class TopoLogical {
    // 顶点的拓扑排序
    private Stack<Integer> order;

    // 构造拓扑排序对象
    /*
    1. 判断是否有环
    2. 构造顶点序列
     */
    public TopoLogical(Digraph G) {
        DirectedCycle cycle = new DirectedCycle(G);
        if (!cycle.hasCycle()) {
            DepthFirstOrder depthFirstOrder = new DepthFirstOrder(G);
            order = depthFirstOrder.reversePost();
        }
    }

    // 判断图G中是否有环
    public boolean isCycle() {
        return order == null;
    }

    // 获取拓扑排序中的所有顶点
    public Stack<Integer> order() {
        return order;
    }
}
