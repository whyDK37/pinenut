package msgPack;


import org.msgpack.core.MessageBufferPacker;
import org.msgpack.core.MessagePack;

import java.io.IOException;

public class MsgPackTest3 {
  public static void main(String[] args) throws IOException {
    // Create serialize objects.
    Son2 son2 = new Son2();
    son2.setName("name");
    son2.setAge(11);

    MessageBufferPacker messageBufferPacker = MessagePack.newDefaultBufferPacker();
// Serialize
//        byte[] raw = msgpack.write(son2);
//
//// Deserialize directly using a template
//        System.out.println(msgpack.read(raw,Son2.class).getName());
//        System.out.println(msgpack.read(raw,Son2.class).getAge());
//
//        System.out.println(msgpack.read(raw,Son.class).getName());
//        System.out.println(msgpack.read(raw,Son.class).getId());

  }
}
