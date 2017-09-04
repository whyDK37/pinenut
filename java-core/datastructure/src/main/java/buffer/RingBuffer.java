package buffer;

/**
 * 怎样以无锁的方式进行线程安全的buffer的读写操作。基本原理是这样的。在进行读操作的时候，我们只修改head的值，
 * 而在写操作的时候我们只修改tail的值。在写操作时，我们在写入内容到buffer之后才修改tail的值；而在进行读操作的时候，
 * 我们会读取tail的值并将其赋值给copyTail。赋值操作是原子操作。所以在读到copyTail之后，从head到copyTail之间一定
 * 是有数据可以读的，不会出现数据没有写入就进行读操作的情况。同样的，读操作完成之后，才会修改head的数值；而在写操作
 * 之前会读取head的值判断是否有空间可以用来写数据。所以，这时候tail到head - 1之间一定是有空间可以写数据的，
 * 而不会出现一个位置的数据还没有读出就被写操作覆盖的情况。这样就保证了RingBuffer的线程安全性。
 * Created by whydk on 10/25/2016.
 */
public class RingBuffer<T> {
  private final static int bufferSize = 1 << 10;//1024
  private Object[] buffer = new Object[bufferSize];
  private int head = 0;
  private int tail = 0;

  public Boolean isEmpty() {
    return head == tail;
  }

  private Boolean full() {
    return (tail + 1) % bufferSize == head;
  }

  public Boolean put(T v) {
    if (full()) {
      return false;
    }
    buffer[tail] = v;
    tail = (tail + 1) % bufferSize;
    return true;
  }

  public T get() {
    if (isEmpty()) {
      return null;
    }
    T result = (T) buffer[head];
    head = (head + 1) % bufferSize;
    return result;
  }

  public T[] getAll() {
    if (isEmpty()) {
      return (T[]) new Object[0];
    }
    int copyTail = tail;
    int cnt = head < copyTail ? copyTail - head : bufferSize - head + copyTail;
    Object[] result = new Object[cnt];
    if (head < copyTail) {
      for (int i = head; i < copyTail; i++) {
        result[i - head] = buffer[i];
      }
    } else {
      for (int i = head; i < bufferSize; i++) {
        result[i - head] = buffer[i];
      }
      for (int i = 0; i < copyTail; i++) {
        result[bufferSize - head + i] = buffer[i];
      }
    }
    head = copyTail;
    return (T[]) result;
  }
}