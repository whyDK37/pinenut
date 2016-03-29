package nio;

import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * Created by drug on 2016/3/29.
 */
public class PipeTest {
    public static void main(String[] args) throws Exception {
        Pipe pipe = Pipe.open();

        Pipe.SinkChannel sinkChannel = pipe.sink();
        Pipe.SourceChannel sourceChannel = pipe.source();
        String newData = "New String to write to file..." + System.currentTimeMillis();
        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        buf.put(newData.getBytes());
        buf.flip();

        ByteBuffer bufread = ByteBuffer.allocate(48);

        while(buf.hasRemaining()) {
            sinkChannel.write(buf);
            int bytesRead = sourceChannel.read(bufread);
            System.out.println(bytesRead);
        }

    }
}
