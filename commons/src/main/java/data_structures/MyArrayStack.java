package data_structures;

/**
 * Created by why on 4/28/2017.
 */
public class MyArrayStack<E> {
    Object[] elements;

    int maxSize;
    int top = -1;
    int size = 0;
    int modCount = 0;

    public MyArrayStack() {
        this(10);
    }

    public MyArrayStack(int maxSize) {
        this.maxSize = maxSize;
        elements = new Object[maxSize];
    }

    public void push(E e) {
        elements[++top] = e;

        size++;
        modCount++;
    }

    public E pop() {
        if (size == 0) {
            return null;
        }
        Object element = elements[top];
        elements[top--] = null;

        size--;
        modCount++;
        return (E) element;
    }

    public E peek() {
        return (E) elements[top];
    }

    public int size() {
        return size;
    }
}
