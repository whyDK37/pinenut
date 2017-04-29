package data_structures;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by why on 4/29/2017.
 */
public class MyLinkedQueueTest {
    @Test
    public void testSize() throws Exception {

        MyLinkedQueue<String> queue = new MyLinkedQueue();
        queue.add("a");
        queue.add("b");
        queue.add("c");

        Assert.assertEquals(queue.peek(), "a");
        Assert.assertEquals(queue.poll(), "a");
        Assert.assertEquals(queue.poll(), "b");
        for (int i = 0; i < 20; i++) {
            queue.add("" + i);
            queue.poll();
        }

        Assert.assertEquals(queue.size(), 1);
    }

}