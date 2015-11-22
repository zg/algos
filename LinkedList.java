/**
 * A basic linked list implementation that has insert and read for integers.
 *
 * @author Zack Zatkin-Gold
 */
public class LinkedList<T> {
    private int len;
    private node pre;
    private node post;

    LinkedList() {
        pre = new node();
        post = new node();
        pre.next = post;
        post.prev = pre;
    }

    int size() {
        return len;
    }

    void add(T item) {
        node last = post.prev;
        node x = new node();
        x.val = item;
        x.next = post;
        x.prev = last;
        post.prev = x;
        last.next = x;
        len++;
    }

    T get(int idx) {
        if(idx < (len >> 1)) {
            node x = pre.next;
            for(int i = 0; i < idx; i++)
                x = x.next;
            return x.val;
        } else {
            node x = post.prev;
            for(int i = len - 1; i > idx; i--)
                x = x.prev;
            return x.val;
        }
    }

    void addAll(LinkedList<T> from) {
        for(int i = 0; i < from.size(); i++)
            add(from.get(i));
    }

    private class node {
        node prev;
        node next;
        T val;
    }
}
