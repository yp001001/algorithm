package zuo.package13;

public class AVLTreeMap<K extends Comparable<K>, V> {

    private AVLNode<K, V> root;
    private int size;

    public AVLTreeMap() {
        root = null;
        size = 0;
    }

    public static class AVLNode<K extends Comparable<K>, V> {
        public K k;
        public V v;
        public AVLNode<K, V> l;
        public AVLNode<K, V> r;
        public int h;

        public AVLNode(K k, V v) {
            this.k = k;
            this.v = v;
            h = 1;
        }
    }

}
