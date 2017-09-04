package net.jcip.examples;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by why on 5/13/2017.
 */
public class SequenceTest {
  Sequence sequence;

  @BeforeClass
  public void before() {
    sequence = new Sequence();
  }

  @Test(threadPoolSize = 10, invocationCount = 10)
  public void testGetNext() throws Exception {
    System.out.println(sequence.getNext());
  }

}