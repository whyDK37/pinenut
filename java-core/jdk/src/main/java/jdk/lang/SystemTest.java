package jdk.lang;

/**
 * Created by why on 5/6/2017.
 */
public class SystemTest {
  public static void main(String[] args) {
    System.out.println(System.identityHashCode(new Object()));
    System.out.println(System.identityHashCode(new Object()));
    System.out.println(System.identityHashCode(new Object()));
  }
}
