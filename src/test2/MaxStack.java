package test2;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class MaxStack {

    public static void main(String[] args) {
        MaxStack maxStack = new MaxStack();
        maxStack.push(5);
        maxStack.push(1);
        maxStack.push(5);
        System.out.println(maxStack.top());
        System.out.println(maxStack.popMax());
        System.out.println(maxStack.top());
        System.out.println(maxStack.peekMax());
        System.out.println(maxStack.pop());
        System.out.println(maxStack.top());
    }

    Stack<Item> stack;
    PriorityQueue<Item> heap;
    private int globalId;
    private Set<Item> poppedSet;

    public MaxStack() {
        this.globalId = 0;
        this.heap = new PriorityQueue<>();
        this.stack = new Stack<>();
        this.poppedSet = new HashSet<>();
    }

    /*
     * @param number: An integer
     * @return: nothing
     */
    public void push(int x) {
        Item item = new Item(x, globalId);
        stack.push(item);
        heap.offer(item);
        globalId++;
    }

    public int pop() {
        // write your code here
        clearPoppedInStack();
        Item item = stack.pop();
        poppedSet.add(item);
        return item.val;
    }

    /*
     * @return: An integer
     */
    public int top() {
        clearPoppedInStack();
        Item item = stack.peek();
        return item.val;
    }

    /*
     * @return: An integer
     */
    public int peekMax() {
        // write your code here
        clearPoppedInHeap();
        Item item = heap.peek();
        return item.val;
    }

    /*
     * @return: An integer
     */
    public int popMax() {
        // write your code here
        clearPoppedInHeap();
        Item item = heap.poll();
        poppedSet.add(item);
        return item.val;
    }


    private void clearPoppedInStack() {
        while (!stack.isEmpty() && poppedSet.contains(stack.peek())) {
            Item item = stack.pop();
            poppedSet.remove(item);
        }
    }

    private void clearPoppedInHeap() {
        while (!heap.isEmpty() && poppedSet.contains(heap.peek())) {
            Item item = heap.poll();
            poppedSet.remove(item);
        }
    }
}

class Item implements Comparable<Item> {
    public int val, id;

    public Item(int val, int id) {
        this.val = val;
        this.id = id;
    }

    @Override
    public int compareTo(Item another) {
        if (this.val != another.val) {
            return another.val - this.val;
        }
        return another.id - this.id;
    }
}
