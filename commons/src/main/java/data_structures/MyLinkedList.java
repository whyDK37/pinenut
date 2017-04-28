package data_structures;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by why on 4/28/2017.
 */
public class MyLinkedList<E> implements List<E>/*, Deque<E>*/ {

    private int size;

    protected transient int modCount = 0;

    private static int default_size = 10;

    private Node head;

    private Node tail;

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
    public Iterator<E> iterator() {
        return new Itr(head, size, modCount);
    }

    private class Itr<E> implements Iterator<E> {

        private Node head;
        private int size;
        private int modCount;
        private Node currentNode;


        Itr(Node head, int size, int modCount) {
            this.head = head;
            this.currentNode = head;
            this.size = size;
            this.modCount = modCount;
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public E next() {
            Node c = currentNode;
            currentNode = c.next;
            return (E) c.item;
        }
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    void linkFirst(E e) {
        final Node oldHead = head;
        Node newHead = new Node(null, e, oldHead);
        head = newHead;
        if (oldHead == null) {
            tail = newHead;
        } else {
            oldHead.pre = newHead;
        }

        size++;
        modCount++;
    }

    void linkLast(E e) {
        final Node oldTail = tail;
        Node newTail = new Node(oldTail, e, null);
        tail = newTail;

        if (oldTail == null) {
            head = newTail;
        } else {
            oldTail.next = newTail;
        }

        size++;
        modCount++;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        Node node = getNode(index);
        unlink(node);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E e : c) {
            linkLast(e);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

        for (Node<E> x = head; x != null; ) {
            Node next = x.next;
            x.pre = null;
            x.item = null;
            x.next = null;
            x = next;
        }

        head = tail = null;
        size = 0;
        modCount++;
    }

    @Override
    public E get(int index) {
        Node returnNode = head;
        for (int i = 0; i < index; i++) {
            returnNode = returnNode.next;
        }
        return (E) returnNode.item;
    }

    Node getNode(int index) {

        // size/2
        if (index < (size >> 1)) {
            Node<E> x = head;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            Node<E> x = tail;
            for (int i = size - 1; i > index; i--) {
                x = x.pre;
            }
            return x;
        }
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) {

        if (index == size) {
            linkLast(element);
        } else {
            LinkBefore(element, getNode(index));
        }

        size++;
        modCount++;
    }

    private void LinkBefore(E element, Node next) {
        Node pre = next.pre;
        Node newNode = new Node(pre, element, next);

        next.pre = newNode;

        //如果为空，新节点为 head
        if (pre == null)
            head = newNode;
        else
            pre.next = newNode;

        size++;
        modCount++;
    }

    @Override
    public E remove(int index) {
        Node node = getNode(index);

        E item = unlink(node);

        return item;
    }

    E unlink(Node node) {
        Object item = node.item;
        Node pre = node.pre;
        Node next = node.next;

        if (pre == null)
            head = next;
        else
            pre.next = next;


        if (next == null)
            tail = pre;
        else
            next.pre = pre;

        node.item = null;
        size--;
        modCount++;

        return (E) item;
    }

    @Override
    public int indexOf(Object o) {
        if (head == null) {
            return -1;
        }

        Node current = head;
        int index = 0;
        while (current != null) {
            if (current.item.equals(o))
                return index;

            index++;
            current = current.next;
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }


    private class Node<E> {
        E item;
        Node next;
        Node pre;

        public Node(Node pre, E item, Node next) {
            this.pre = pre;
            this.item = item;
            this.next = next;
        }

    }
}
