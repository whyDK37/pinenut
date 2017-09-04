package jdk.thread;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * dead lock
 * <p>
 * This time we don’t get an exception; we get a deadlock. The background thread
 * calls s.removeObserver, which attempts to lock observers, but it can’t acquire
 * the lock, because the main thread already has the lock. All the while, the main
 * thread is waiting for the background thread to finish removing the observer, which
 * explains the deadlock.
 * Created by why on 10/23/2016.
 */
public class ObservableSet2<E> extends HashSet<E> {
  private final List<SetObserver<E>> observers = new ArrayList<SetObserver<E>>();

  public ObservableSet2(Set<E> set) {
    super(set);
  }

  public static void main(String[] args) {
    ObservableSet2<Integer> set = new ObservableSet2<Integer>(new HashSet<Integer>());
    set.addObserver(new SetObserver<Integer>() {
      public void added(ObservableSet2<Integer> s, Integer e) {
        System.out.println(e);
        if (e == 23) {
          ExecutorService executor =
                  Executors.newSingleThreadExecutor();
          final SetObserver<Integer> observer = this;
          try {
            executor.submit(new Runnable() {
              public void run() {
                s.removeObserver(observer);
              }
            }).get();
          } catch (ExecutionException ex) {
            throw new AssertionError(ex.getCause());
          } catch (InterruptedException ex) {
            throw new AssertionError(ex.getCause());
          } finally {
            executor.shutdown();
          }
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
    public void added(ObservableSet2<E> es, E element) {
    }
  }
}
