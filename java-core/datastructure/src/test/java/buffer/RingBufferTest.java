package buffer;

import org.testng.annotations.Test;

/**
 * Created by whydk on 10/25/2016.
 */
public class RingBufferTest {

    @Test
    public void test(){
        RingBuffer<String> ringBuffer = new RingBuffer<String>();

        ringBuffer.getAll();
        ringBuffer.put("a");
        ringBuffer.put("b");

        ringBuffer.get();
        ringBuffer.getAll();

        System.out.println(1<<10);

    }
}
