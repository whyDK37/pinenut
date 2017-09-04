package jdk.util;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Oversimplistic implementation of an object pool
 */
public class WeakObjectPool {
  // Map where the key is an object, and the value is a weak reference
  // to the same object. We use the key to do the lookup, and the value
  // to actually return the object when it is found.
  private Map map = new WeakHashMap();

  public Object replace(Object object) {
    WeakReference reference = (WeakReference) map.get(object);
    if (reference != null) {
      System.out.println("direct return");
      Object result = reference.get();
      // Another null check, since the GC may have kicked in between the
      // two lines above.
      if (result != null) {
        return result;
      }
    }
    // If we got here it is because the map doesn't have the key, add it.
    System.out.println("add new one");
    map.put(object, new WeakReference(object));
    return object;
  }
}  