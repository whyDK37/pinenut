package redis.util;

import java.io.Closeable;


/**
 * @author Josh Wang(Sheng)
 * @email josh_wang23@hotmail.com
 */
public abstract class SerializeTranscoder {


    public abstract byte[] serialize(Object value);

    public abstract Object deserialize(byte[] in);

    public void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
//         logger.info("Unable to close " + closeable, e);
            }
        }
    }
}