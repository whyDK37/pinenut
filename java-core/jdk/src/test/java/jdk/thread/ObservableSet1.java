package jdk.thread;

import java.util.*;

/**
 * caes an  java.util.ConcurrentModificationException
 * Changing ArrayList to CopyOnWriteArrayList can solve this problem
 * <p>
 * Created by why on 10/23/2016.
 */
public class ObservableSet1<E> extends HashSet<E> {
  // fixme 2016-10-23 CopyOnWriteArrayList can solve this problem
  private final List<SetObserver<E>> observers = new ArrayList<SetObserver<E>>();

  public ObservableSet1(Set<E> set) {
    super(set);
  }

  public static void main(String[] args) {
    ObservableSet1<Integer> set = new ObservableSet1<Integer>(new HashSet<Integer>());
    set.addObserver(new SetObserver<Integer>() {
      public void added(ObservableSet1<Integer> s, Integer e) {
        System.out.println(e);
        if (e == 23) {
          set.removeObserver(this);
        }
      }
    });
    for (int i = 0; i < 100; i++)
      set.add(i);
  }

  public void addObserver(SetObserver<E> observer) {
    synchronized (observers) {
      observers.add(observer);
    }
  }

  public boolean removeObserver(SetObserver<E> observer) {
    synchronized (observers) {
      return observers.remove(observer);
    }
  }

  private void notifyElementAdded(E element) {
    synchronized (observers) {
      for (SetObserver<E> observer : observers)
        observer.added(this, element);
    }
  }

  @Override
  public boolean add(E element) {
    boolean added = super.add(element);
    if (added)
      notifyElementAdded(element);
    return added;
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    boolean result = false;
    for (E element : c)
      result |= add(element); // calls notifyElementAdded
    return result;
  }

  static class SetObserver<E> {
    public void added(ObservableSet1<E> es, E element) {
    }
  }
}
