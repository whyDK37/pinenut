package jdk.thread;

import java.util.*;

/**
 * Created by why on 10/23/2016.
 */
public class ObservableSet<E> extends HashSet<E> {
  private final List<SetObserver<E>> observers = new ArrayList<SetObserver<E>>();

  public ObservableSet(Set<E> set) {
    super(set);
  }

  public static void main(String[] args) {
    ObservableSet<Integer> set = new ObservableSet<Integer>(new HashSet<Integer>());
    set.addObserver(new SetObserver<Integer>() {
      public void added(ObservableSet<Integer> s, Integer e) {
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
    List<SetObserver<E>> snapshot = null;
    synchronized (observers) {
      snapshot = new ArrayList<SetObserver<E>>(observers);
    }
    for (SetObserver<E> observer : snapshot)
      observer.added(this, element);
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
    public void added(ObservableSet<E> es, E element) {
    }
  }
}
