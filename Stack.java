import java.util.ArrayList;

/**
 * A basic Stack implementation using an ArrayList as its underlying structure.
 *
 * @author Zack Zatkin-Gold
 */
public class Stack<T> {
    private ArrayList<T> stack;

    public Stack() {
        this.stack = new ArrayList<T>();
    }

    public T push(T item) {
        stack.add(item);

        return item;
    }

    public synchronized T pop() {
        return stack.remove(stack.size() - 1);
    }

    public synchronized T peek() {
        return stack.get(stack.size() - 1);
    }

    public boolean empty() {
        return stack.size() == 0;
    }

    public synchronized int search(T item) {
        return stack.indexOf(item);
    }
}
