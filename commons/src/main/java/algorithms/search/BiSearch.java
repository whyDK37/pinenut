package algorithms.search;

/**
 * 二分查找法
 * Created by why on 3/5/2017.
 */
public class BiSearch {

  public static int biSearch(int[] array, int a) {
    int low = 0;
    int high = array.length - 1;
    int middle;
    while (low <= high) {
      middle = (low + high) / 2;
      if (array[middle] < a) {
        low = middle + 1;
      } else if (array[middle] > a) {
        high = middle - 1;
      } else {
        return middle + 1;
      }
    }
    return -1;
  }
}
