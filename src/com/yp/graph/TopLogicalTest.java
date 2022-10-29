package com.yp.graph;

import java.util.Stack;

public class TopLogicalTest {

    public static void main(String[] args) {
        Digraph digraph = new Digraph(6);
        digraph.addEdge(0, 2);
        digraph.addEdge(0, 3);
        digraph.addEdge(2, 4);
        digraph.addEdge(3, 4);
        digraph.addEdge(4, 5);
        digraph.addEdge(1, 3);

        TopoLogical topoLogical = new TopoLogical(digraph);

        Stack<Integer> order = topoLogical.order();
//        while (!order.isEmpty()) {
//            System.out.println(order.pop());
//        }
        StringBuffer stringBuffer = new StringBuffer();
        for (Integer w : order) {
            stringBuffer.append(w+"->");
        }
        String s = stringBuffer.toString();
        s.lastIndexOf("->");
        System.out.println(s);
    }
}
