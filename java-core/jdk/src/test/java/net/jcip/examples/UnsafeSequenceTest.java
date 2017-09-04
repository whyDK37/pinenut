package net.jcip.examples;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by why on 5/13/2017.
 */
public class UnsafeSequenceTest {
  UnsafeSequence unsafeSequence;

  @BeforeClass
  public void before() {
    unsafeSequence = new UnsafeSequence();
  }

  @Test(invocationCount = 50, threadPoolSize = 3, groups = {"t9"})
  public void testGetNext() throws Exception {
    System.out.println(unsafeSequence.getNext());
  }

}