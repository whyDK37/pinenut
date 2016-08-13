package proto;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.Test;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by whydk on 2016/8/11.
 */
public class ProtoTest {
    private byte[] encode(UserProto.User user) {
        return user.toByteArray();
    }

    private int runtime = 10000*10000;
    private UserProto.User decode(byte[] body)
            throws InvalidProtocolBufferException {
        return UserProto.User.parseFrom(body);
    }

    @Test
    public void protoTest()
            throws InvalidProtocolBufferException {
//        System.out.println("Before encode : " + user.toString());
        long start = System.currentTimeMillis();
        for (int i = 0; i < runtime; i++) {
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
        System.out.println("protobuf:"+(end - start));
//        System.out.println("After decode : " + user2.toString());

    }

//    @Test
    public void testSerializable() throws IOException, ClassNotFoundException {


        long start = System.currentTimeMillis();
        for (int i = 0; i < runtime; i++) {
            User user = getUser();
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
        System.out.println("testSerializable:"+(end - start));
    }


    @Test
    public void testFastJSON() throws IOException, ClassNotFoundException {

        long start = System.currentTimeMillis();
        for (int i = 0; i < runtime; i++) {
            User user = getUser();
            String jsonString = JSON.toJSONString(user);
            user = JSON.parseObject(jsonString, User.class);
        }
        long end = System.currentTimeMillis();
        System.out.println("fast json:"+(end - start));
    }


    /**
     * 格式化时间的string
     */
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    @Test
    public void testJackson() throws IOException {
        ObjectMapper customMapper = new ObjectMapper();
        // 所有日期格式都统一为以下样式
        customMapper.setDateFormat(new SimpleDateFormat(DATE_TIME_FORMAT));


        long start = System.currentTimeMillis();
        for (int i = 0; i < runtime; i++) {
            User user = getUser();
            String jsonString = customMapper.writeValueAsString(user);
            user = customMapper.readValue(jsonString, User.class);
        }
        long end = System.currentTimeMillis();
        System.out.println("jackson :"+(end - start));
    }

    private User getUser() {
        User user = new User();
        user.setID(1);
        user.setPassword("Netty Book");
        user.setUserName("我的名字");
        user.setAddress("NanJing YuHuaTai");
        return user;
    }

}
