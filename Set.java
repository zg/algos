/**
 * A basic set implementation that has insert, read, and insert from other
 * sets for integers.
 *
 * @author Zack Zatkin-Gold
 */
public class Set {
    int idx;
    int length;
    int[] items;

    Set(int length) {
        this.idx = -1;
        this.length = length;
        this.items = new int[length];
    }

    int get(int idx) {
        return items[idx];
    }

    void add(int item) {
        for(int i = 0; i <= idx; i++)
            if(items[i] == item)
                return;
        items[++idx] = item;
    }

    void addAll(Set from) {
        for(int i = 0; i <= from.idx; i++)
            add(from.get(i));
    }
}
