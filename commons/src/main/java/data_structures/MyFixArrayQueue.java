package data_structures;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

/**
 * 固定长度的队列
 * Created by why on 4/29/2017.
 */
public class MyFixArrayQueue<E> implements Queue<E> {

  private Object[] elements;
  private int size;
  private int modCount;

  private int head = 0;
  private int tail = 0;
  private int capacity;

  public MyFixArrayQueue(int size) {
    elements = new Object[size];
    capacity = size;
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

    if (size == capacity) return false;

    elements[tail] = o;

//        tail = tail + 1 > capacity - 1 ? 0 : tail + 1;
    if (++tail == elements.length)
      tail = 0;

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
      elements[size++] = o;
    }
    modCount++;
    return true;
  }

  @Override
  public void clear() {

    for (int i = 0; i < size; i++) {
      elements[i] = null;
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

    Object element = elements[head];
    elements[head] = null;

//        head = head + 1 > capacity - 1 ? 0 : head + 1;
    if (++head == elements.length)
      head = 0;
    size--;
    modCount--;
    return (E) element;
  }

  @Override
  public E element() {
    return null;
  }

  @Override
  public E peek() {
    if (size == 0)
      return null;

    Object element = elements[head];

    return (E) element;
  }
}
