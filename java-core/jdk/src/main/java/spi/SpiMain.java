package spi;


import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by why on 4/12/2017.
 */
public class SpiMain {
  public static void main(String[] args) {
    ServiceLoader<MySpi> serviceLoader = ServiceLoader.load(MySpi.class);
    Iterator<MySpi> provider = serviceLoader.iterator();
    while (provider.hasNext()) {
      MySpi cc = provider.next();
      cc.hello();
    }
  }
}
