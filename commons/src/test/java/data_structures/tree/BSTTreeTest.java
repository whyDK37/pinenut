package data_structures.tree;

import org.testng.annotations.Test;

/**
 * Created by why on 5/2/2017.
 */
public class BSTTreeTest {
  @Test
  public void testInsertBST() throws Exception {

    BSTTree<Integer> bstTree = new BSTTree<>();
    int[] a = {62, 88, 58, 47, 35, 73, 51, 99, 37, 93};
    for (int i : a) {
      bstTree.insertBST(i);
    }
    System.out.println(bstTree);
  }

}