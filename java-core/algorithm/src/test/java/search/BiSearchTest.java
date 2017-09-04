package search;

import org.junit.Test;

/**
 * Created by why on 3/5/2017.
 */
public class BiSearchTest {

  @Test
  public void test() {
    int[] iarr = new int[]{1, 3, 5, 7, 9, 12, 45, 78, 98};
    int i = BiSearch.biSearch(iarr, 9);
    System.out.println(i);
  }
}