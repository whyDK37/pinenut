package data_structures;

/**
 * Created by why on 4/28/2017.
 */
public class MyLinkedStack<E> {

    Node top;


    int modCount = 0;

    int size;

    public void push(E e) {
        Node<E> eNode = new Node<>(e, top);
        top = eNode;
        modCount++;
        size++;
    }

    public E pop() {
        if (size == 0)
            return null;

        Object item = top.item;
        top = top.next;
        modCount++;
        size--;

        return (E) item;
    }

    public E peek() {
        return (E) top.item;
    }

    public int size() {
        return size;
    }

    private class Node<E> {
        E item;
        Node next;

        Node(E item, Node next) {
            this.item = item;
            this.next = next;
        }
    }
}
