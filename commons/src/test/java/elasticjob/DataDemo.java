package elasticjob;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whydk on 10/21/2016.
 */
public class DataDemo {

  static List<String> l1 = new ArrayList<>();
  static List<String> l2 = new ArrayList<>();
  static List<String> l3 = new ArrayList<>();

  static {
    l1.add("l1");
    l2.add("l2");
    l3.add("l3");
  }

  public static List<String> getL1() {
    return l1;
  }

  public static List<String> getL2() {
    return l2;
  }

  public static List<String> getL3() {
    return l3;
  }

}
