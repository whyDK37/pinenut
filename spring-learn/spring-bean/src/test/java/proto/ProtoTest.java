package proto;

import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by whydk on 2016/8/11.
 */
public class ProtoTest {
    private byte[] encode(UserProto.User user) {
        return user.toByteArray();
    }

    private UserProto.User decode(byte[] body)
            throws InvalidProtocolBufferException {
        return UserProto.User.parseFrom(body);
    }

    @Test
    public void protoTest()
            throws InvalidProtocolBufferException {
//        System.out.println("Before encode : " + user.toString());
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            UserProto.User.Builder builder = UserProto.User.newBuilder();
            builder.setID(1);
            builder.setUserName("我的名字");
            builder.setPassword("Netty Book");
            List<String> address = new ArrayList<String>();
            address.add("NanJing YuHuaTai");
//        address.add("BeiJing LiuLiChang");
//        address.add("ShenZhen HongShuLin");
            builder.addAllAddress(address);
            UserProto.User user = builder.build();
            byte[] b = encode(user);
            UserProto.User user2 = decode(b);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
//        System.out.println("After decode : " + user2.toString());

    }

    @Test
    public void testSer() throws IOException, ClassNotFoundException {


        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            User user = new User();
            user.setID(1);
            user.setPassword("Netty Book");
            user.setUserName("我的名字");
            user.setAddress("NanJing YuHuaTai");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(byteArrayOutputStream);
            oos.writeObject(user);
            oos.flush();
            oos.close();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(byteArrayInputStream);
            user = (User) ois.readObject();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
