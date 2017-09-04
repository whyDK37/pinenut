package algorithms.string;

import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * Created by why on 5/11/2017.
 */
public class KMPAlgorithmTest {
  @Test
  public void testMatchString() throws Exception {
    String str = "abcabcabcabe";
    String mode = "ababaaaba";

    int[] ints = KMPAlgorithm.calculateK(mode);
    System.out.println(Arrays.toString(ints));

    boolean b = KMPAlgorithm.matchString(str, mode);
    System.out.println(b);

    str.contains(mode);
  }

}