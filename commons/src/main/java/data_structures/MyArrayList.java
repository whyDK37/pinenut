package data_structures;

import java.util.*;

/**
 * 链表的简单实现，代码并不是很严谨，有些地方缺少必要的校验。
 * Created by why on 4/28/2017.
 */
public class MyArrayList<E> implements List<E> {
    private int size;

    protected transient int mod = 0;

    private static int default_size = 10;

    private Object[] elements;

    public MyArrayList() {
        this(default_size);
    }

    public MyArrayList(int size) {
        elements = new Object[size];
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
        if (o == null)
            return false;

        for (Object element : elements) {
            if (element.equals(o)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Iterator iterator() {
        return new MyListIterator(elements, size, mod);
    }

    private class MyListIterator<E> implements Iterator<E> {
        private Object[] elements;
        private int size;
        private int expectedModCount;
        private int current = 0;

        public MyListIterator(Object[] elements, int size, int mod) {
            this.elements = elements;
            this.size = size;
            this.expectedModCount = mod;
        }

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public E next() {
            if (mod != expectedModCount) {
                throw new RuntimeException("Modify happened!");
            }
            return (E) elements[current++];
        }
    }

    protected int getModCount() {
        return mod;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {
        ensureCapacityInternal(size + 1);

        elements[size++] = o;
        mod++;

        return true;
    }

    private void ensureCapacityInternal(int size) {
        if (elements.length < size) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
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

        return true;
    }

    @Override
    public boolean addAll(int index, Collection c) {

        int offset = 0;
        for (Object o : c) {
            add(index + offset++, o);
        }

        return true;
    }

    @Override
    public void clear() {
        mod++;

        for (int i = 0; i < size; i++)
            elements[i] = null;

        size = 0;
    }

    @Override
    public E get(int index) {
        checkIndex(index);

        return (E) elements[index];
    }

    @Override
    public E set(int index, Object element) {
        checkIndex(index);
        Object obj = elements[index];
        elements[index] = element;
        return (E) obj;
    }

    @Override
    public void add(int index, Object element) {
        checkIndex(index);

        ensureCapacityInternal(size + 1);

        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;

        size++;
        mod++;
    }

    private void checkIndex(int index) {
        if (index > size || index < 0)
            throw new RuntimeException("out of range");
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        Object ro = elements[index];

        System.arraycopy(elements, index + 1, elements, index, size - index - 1);

        size--;
        mod++;
        return (E) ro;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null)
            return -1;

        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null)
            return -1;

        for (int i = size - 1; i >= 0; i--) {
            if (elements[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
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
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
