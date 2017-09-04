package net.jcip.examples;

/**
 * Created by why on 5/16/2017.
 */
public interface Computable<A, V> {
  V compute(A arg);
}
