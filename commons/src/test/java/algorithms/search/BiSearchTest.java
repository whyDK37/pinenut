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

}