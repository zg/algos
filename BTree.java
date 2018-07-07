/**
 * Generic b-tree implementation.
 *
 * @author Zack Zatkin-Gold
 */

public class BTree<k extends Comparable<? super k>,v> {
    private static final int M = 4; // max children per node

    private node root;
    private int height;
    private int n; // number of k/v pairs

    private static final class node {
        private int m; // number of children
        private entry[] children = new entry[M];

        private node(int k) {
            m = k;
        }
    }

    private static class entry {
        private Comparable key;
        private final Object val;
        private node next;
        public entry(Comparable key, Object val, node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public BTree() {
        root = new node(0);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return n;
    }

    public int height() {
        return height;
    }

    public v get(k key) {
        if(key == null)
            throw new IllegalArgumentException("argument to get() is null");
        return search(root, key, height);
    }

    private v search(node x, k key, int ht) {
        entry[] children = x.children;

        if(ht == 0) {
            for(int j = 0; j < x.m; j++) {
                if(eq(key, children[j].key)) return (v) children[j].val;
            }
        } else {
            for(int j = 0; j < x.m; j++) {
                if(j+1 == x.m || less(key, children[j+1].key)) {
                    return search(children[j].next, key, ht - 1);
                }
            }
        }
        return null;
    }

    public void put(k key, v val) {
        if(key == null)
            throw new IllegalArgumentException("argument key to put() is null");
        node u = insert(root, key, val, height);
        n++;
        if(u == null) return;

        // need to split root
        node t = new node(2);
        t.children[0] = new entry(root.children[0].key, null, root);
        t.children[1] = new entry(u.children[0].key, null, u);
        root = t;
        height++;
    }

    private node insert(node h, k key, v val, int ht) {
        int j = 0;
        entry t = new entry(key, val, null);

        if(ht == 0) {
            for(j = 0; j < h.m; j++) {
                if(less(key, h.children[j].key)) break;
            }
        } else {
            for(j = 0; j < h.m; j++) {
                if((j+1 == h.m) || less(key, h.children[j+1].key)) {
                    node u = insert(h.children[j++].next, key, val, ht-1);
                    if(u == null) return null;
                    t.key = u.children[0].key;
                    t.next = u;
                    break;
                }
            }
        }

        for(int i = h.m; i > j; i--)
            h.children[i] = h.children[i-1];
        h.children[j] = t;
        h.m++;
        if(h.m < M) return null;
        else         return split(h);
    }

    private node split(node h) {
        node t = new node(M/2);
        h.m = M/2;
        for(int j = 0; j < M/2; j++)
            t.children[j] = h.children[M/2+j];
        return t;
    }

    public String toString() {
        return toString(root, height, "") + "\n";
    }

    private String toString(node h, int ht, String indent) {
        StringBuilder s = new StringBuilder();
        entry[] children = h.children;

        if(ht == 0) {
            for(int j = 0; j < h.m; j++) {
                s.append(indent + children[j].key + " " + children[j].val + "\n");
            }
        } else {
            for(int j = 0; j < h.m; j++) {
                if(j > 0) s.append(indent + "(" + children[j].key + ")\n");
                s.append(toString(children[j].next, ht-1, indent + "    "));
            }
        }
        return s.toString();
    }

    private boolean less(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) < 0;
    }

    private boolean eq(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) == 0;
    }

    public static void main(String[] args) {
        BTree<String, String> bt = new BTree<>();
        bt.put("foo", "foo");
        bt.put("bar", "bar");
        bt.put("baz", "baz");
        bt.put("qux", "qux");
        bt.put("quux", "quux");
        bt.put("corge", "corge");
        bt.put("uier", "uier");
        bt.put("grault", "grault");
        bt.put("graply", "graply");
        bt.put("waldo", "waldo");
        bt.put("fred", "fred");
        bt.put("plugh", "plugh");
        bt.put("thud", "thud");
        bt.put("mos", "mos");
        bt.put("henk", "henk");
        bt.put("def", "def");

        System.out.println("size: " + bt.size());
        System.out.println("height: " + bt.height());
        System.out.println(bt);
        System.out.println(bt.get("qux"));
    }
}
