package data_structures;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

/**
 * 固定长度的队列
 * Created by why on 4/29/2017.
 */
public class MyLinkedQueue<E> implements Queue<E> {

    private int size;
    private int modCount;

    private Node head;
    private Node tail;

    public MyLinkedQueue() {
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {

        Node node = new Node(o);
        Node oldTail = tail;
        tail = node;
        if (oldTail == null)
            head = node;
        else {
            oldTail.next = node;
        }

        size++;
        modCount++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        for (Object o : c) {
            add(o);
        }
        modCount++;
        return true;
    }

    @Override
    public void clear() {

        Node x = head;
        while (x != null) {
            Node node = x.next;
            x.item = null;
            x = node;
        }
        size = 0;
        modCount++;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public boolean offer(Object o) {
        return false;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E poll() {
        if (size == 0) return null;

        Node next = head.next;
        Object item = head.item;
        head.item = null;
        head = next;

        size--;
        modCount--;
        return (E) item;
    }

    @Override
    public E element() {
        return null;
    }

    @Override
    public E peek() {
        if (size == 0)
            return null;

        return (E) head.item;
    }

    private class Node<E> {
        E item;
        Node next;

        Node(E item, Node next) {
            this.item = item;
            this.next = next;
        }

        Node(E item) {
            this.item = item;
            this.next = next;
        }
    }
}
