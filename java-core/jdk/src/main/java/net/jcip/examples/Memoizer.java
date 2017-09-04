package net.jcip.examples;

import java.util.concurrent.*;

/**
 * Created by why on 5/16/2017.
 */
public class Memoizer<A, V> implements Computable<A, V> {
  private final ConcurrentMap<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
  private final Computable<A, V> c;

  public Memoizer(Computable<A, V> c) {
    this.c = c;
  }

  public V compute(final A arg) {
    for (; ; ) {
      Future<V> f = cache.get(arg);
      if (f == null) {
        Callable<V> eval = () -> c.compute(arg);
        FutureTask<V> ft = new FutureTask<>(eval);
        f = cache.putIfAbsent(arg, ft);
        if (f == null) {
          f = ft;
          ft.run();
        }
      }
      try {
        return f.get();
      } catch (CancellationException e) {
        cache.remove(arg);
      } catch (ExecutionException e) {

      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
