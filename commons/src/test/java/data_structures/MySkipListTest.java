package data_structures;

import org.testng.annotations.Test;

/**
 * Created by why on 5/5/2017.
 */
public class MySkipListTest {
  @Test
  public void testPut() throws Exception {
    MySkipList<String> list = new MySkipList<>();
    System.out.println(list);
    list.put(2, "yan");
    list.put(1, "cao");
    list.put(3, "feng");
    list.put(1, "cao");//测试同一个key值
    list.put(4, "曹");
    list.put(6, "丰");
    list.put(5, "艳");
    System.out.println(list);
    System.out.println(list.size());

    System.out.println(list.search(2));

  }

}