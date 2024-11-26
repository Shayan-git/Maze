import java.util.ArrayList;
import java.util.List;

public class Stack<T> {
    private List<T> list;
    private int top = -1;

    public Stack() {
        this.list = new ArrayList<>();
    }

    public void push(T item) {
        top++;
        list.add(item);
    }

    public T pop() {
        if (top == -1)
            return null;

        T item = list.get(top);
        top--;
        list.remove(item);
        return item;
    }

    public int size() {
        return top + 1;
    }
}
