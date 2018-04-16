package algorithms.search;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by why on 4/29/2017.
 */
public class BiSearchTest {
  @Test
  public void testBiSearch() throws Exception {
    int[] array = new int[20];
    for (int i = 0; i < array.length; i++) {
      array[i] = i;
    }
    assertEquals(BiSearch.biSearch(array, 12), 13);
    assertEquals(BiSearch.biSearch(array, 252), -1);
  }


  @Test
  public void test() {
    int[] iarr = new int[]{1, 3, 5, 7, 9, 12, 45, 78, 98};
    int i = BiSearch.biSearch(iarr, 9);
    System.out.println(i);
  }

}