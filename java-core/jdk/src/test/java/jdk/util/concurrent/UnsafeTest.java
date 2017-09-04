package jdk.util.concurrent;

import org.testng.annotations.Test;
import sun.misc.Unsafe;

/**
 * Created by why on 5/4/2017.
 */
public class UnsafeTest {

  public static void main(String[] args) {
    AtomicInt atomicInt = new AtomicInt();
    System.out.println(atomicInt.getValueOffset());
    System.out.println(atomicInt.incrementAndGet());
  }

  @Test
  public void test() {
    AtomicInt atomicInt = new AtomicInt();
    System.out.println(atomicInt.getValueOffset());
    System.out.println(atomicInt.incrementAndGet());
  }

}

class AtomicInt {

  private static final Unsafe unsafe = Unsafe.getUnsafe();
  private static final long valueOffset;

  static {
    try {
      valueOffset = unsafe.objectFieldOffset
              (AtomicInt.class.getDeclaredField("value"));
    } catch (Exception ex) {
      throw new Error(ex);
    }
  }

  private volatile int value;

  public AtomicInt(int value) {
    this.value = value;
  }

  public AtomicInt() {
    this.value = 0;
  }

  public final long incrementAndGet() {
    return unsafe.getAndAddLong(this, valueOffset, 1L) + 1L;
  }

  public final long get() {
    return value;
  }

  public long getValueOffset() {
    return valueOffset;
  }
}
