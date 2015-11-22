/**
 * A basic linked list implementation that has insert and read for integers.
 *
 * @author Zack Zatkin-Gold
 */
public class LinkedList<T> {
    private class node {
        node prev;
        node next;
        T item;
    }

    private int len = 0;
    private node first;
    private node last;

    LinkedList() {
    }

    void add(T item) {
        node end = last.prev;
        node x = new node();
        x.item = item;
        x.next = last;
        x.prev = last;
        end.prev = x;
        end.next = x;
        len++;
    }

    void addAll(LinkedList<T> from) {
        for(int i = 0; i < from.size(); i++)
            add(from.get(i));
    }

    void clear() {
        for(node x = first; x != null; ) {
            node next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = new node();
        last = new node();
        first.next = last;
        last.prev = first;
        len = 0;
    }

    boolean contains(T item) {
        for(int idx = 0; idx < len; idx++)
            if(get(idx) == item)
                return true;
        return false;
    }

    T get(int idx) {
        if(idx < (len >> 1)) {
            node x = first.next;
            for(int i = 0; i < idx; i++)
                x = x.next;
            return x.item;
        } else {
            node x = last.prev;
            for(int i = len - 1; i > idx; i--)
                x = x.prev;
            return x.item;
        }
    }

    T getFirst() {
        return len > 0 ? first.next.item : null;
    }

    T getLast() {
        return len > 0 ? last.prev.item : null;
    }

    int indexOf(T item) {
        int idx = 0;
        for(node x = first.next; x != null; x = x.next) {
            if(item.equals(x.item))
                return idx;
            idx++;
        }
        return -1;
    }

    int lastIndexOf(T item) {
        int idx = 0;
        for(node x = last.prev; x != null; x = x.prev) {
            if(item.equals(x.item))
                return idx;
            idx++;
        }
        return -1;
    }

    int size() {
        return len;
    }

    T set(int idx, T item) {
        if(idx < 0 || idx >= len) return null;
        if(idx < (len >> 1)) {
            node x = first.next;
            for(int i = 0; i < idx; i++)
                x = x.next;
            T old_item = x.item;
            x.item = item;
            return old_item;
        } else {
            node x = last.prev;
            for(int i = len - 1; i > idx; i--)
                x = x.prev;
            T old_item = x.item;
            x.item = item;
            return old_item;
        }
    }
}
