## concurrency 同步
### avoid excessive synchronization 避免过度使用synchronization
Here is an example,which implements an observable set wrapper.It allow clients to
subscribe to notifications when elements are added to the set.this is the *Observer* pattern.
for brevity`s, the class does not provide notifications when elements are removed from the set.

这有一个观察着封装的set，它允许客户端订阅添加元素的通知，为了简介，没有提供移出元素的通知。

Observers subscribe to notifications by invoking the addObserver method and unsubscribe by invoking
the removeObserver method. In both cases, an instance of the *callback* interface is passed to the method.

观察者订阅调用addObserver，取消订阅调用removeObserver，两种操作的参数都回调接口。

```

import java.util.*;

/**
 * Created by why on 10/23/2016.
 */
public class ObservableSet0<E> extends HashSet<E> {
    public ObservableSet0(Set<E> set) {
        super(set);
    }

    private final List<SetObserver<E>> observers = new ArrayList<SetObserver<E>>();

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

    public static void main(String[] args) {
        ObservableSet0<Integer> set = new ObservableSet0<Integer>(new HashSet<Integer>());
        set.addObserver(new SetObserver<Integer>() {
            public void added(ObservableSet0<Integer> s, Integer e) {
                System.out.println(e);
            }
        });
        for (int i = 0; i < 100; i++)
            set.add(i);
    }

    static class SetObserver<E> {
        public void added(ObservableSet0<E> es, E element) {
        }
    }
}

```
The result will print 0 through 99.Now let`s try something els.Suppose we replace the addObserver call with one that
passes an observer that prints the Integer value that was added to the set and removes itself if the value is 23.

结果是打印0-99。我们试着做一些其他事，添加一个订阅者，打印数字，并在数字是23的时候移出自己。
```
import java.util.*;

/**
 * caes an  java.util.ConcurrentModificationException
 * Changing ArrayList to CopyOnWriteArrayList can solve this problem
 * <p>
 * Created by why on 10/23/2016.
 */
public class ObservableSet1<E> extends HashSet<E> {
    public ObservableSet1(Set<E> set) {
        super(set);
    }

    // fixme 2016-10-23 CopyOnWriteArrayList can solve this problem
    private final List<SetObserver<E>> observers = new ArrayList<SetObserver<E>>();

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

    static class SetObserver<E> {
        public void added(ObservableSet1<E> es, E element) {
        }
    }
}

```
You might expect the program to print the numbers 0 through 23.But you get an unexpected *ConcurrentModificationException*.
The problem is that notifyElementAdded is in the process of iterating over the observers list when it invokes the observer`s
added method.he iteration in the notifyElementAddded method is in a synchronized block to prevent concurrent modification,
but is does not prevent the iterating thread itself from calling back into the observable set and modifying its observers list.

Now let`s try something odd:Write an observer that attempts to unsubscribe, but instead of calling removeObserver directly,
it engages the services of another thread to do the deed.This observer uses an executor service.
```

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
    public ObservableSet2(Set<E> set) {
        super(set);
    }

    private final List<SetObserver<E>> observers = new ArrayList<SetObserver<E>>();

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

    static class SetObserver<E> {
        public void added(ObservableSet2<E> es, E element) {
        }
    }
}

```
This time we don`t get an exception,we get a deadlock.The background thread calls s.removeObserve,which attempts to lock
 observers,bug it can not acquire the lock,because the main thread already has the lock.All the while,the main thread is
 waiting for the background thread to finish removing the observer,which explains the deadlock.

这是一个人为的例子，因为我们没有必要再观察者中时候线程池。

解决上面两种情况引起的问题，我们可以把方法调用移出同步块。就上面的例子，我们可以在notifyElementAdded方法中生产observer的快照，然后可以
安全的去掉锁。这样上面两个例子都会完美运行。

```
private void notifyElementAdded(E element) {
    List<SetObserver<E>> snapshot = null;
    synchronized(observers) {
        snapshot = new ArrayList<SetObserver<E>>(observers);
    }
    for (SetObserver<E> observer : snapshot)
        observer.added(this, element);
}
```
事实上有更好的方法，java 1.5提供的concurrent collection中的CopyOnWriteArrayList，就是为这个目的而生。当元素改变时，他会新复制一份
完整的数据，对其他情况而言这种操作可能非常粗暴，但是非常适合保存observer，因为他改变的情况比较少。
```
private final List<SetObserver<E>> observers = new CopyOnWriteArrayList<SetObserver<E>>();
public void addObserver(SetObserver<E> observer) {
    observers.add(observer);
}
public boolean removeObserver(SetObserver<E> observer) {
    return observers.remove(observer);
}
private void notifyElementAdded(E element) {
    for (SetObserver<E> observer : observers)
        observer.added(this, element);
}
```
所有的外部调用都在同步块之外，也就是开放式调用（open call）。除了避免故障的产生，还可以大大的提高并发。
#### 在同步块中尽可能的少操作
   如果需要进行大量操作，可以考虑把这些操作移出同步块。

