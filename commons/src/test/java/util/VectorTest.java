package util;

import org.testng.annotations.Test;

import java.util.Vector;

/**
 * Created by why on 4/28/2017.
 */
public class VectorTest {

  @Test
  public void test() {
    Vector<String> vector = new Vector();

    vector.add("a");
    vector.add(0, "bb");

    for (String s : vector) {
      System.out.print(s + ", ");
    }
  }
}
