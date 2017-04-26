package zk;

import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;

import java.io.UnsupportedEncodingException;

public class StringSerializer implements ZkSerializer {

    @Override
    public Object deserialize(byte[] abyte0) throws ZkMarshallingError {
        try {
            return new String(abyte0, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public byte[] serialize(Object obj) throws ZkMarshallingError {
        try {
            return ((String) obj).getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
