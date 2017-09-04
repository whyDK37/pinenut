package msgPack;

import org.msgpack.MessagePack;

import java.io.IOException;

public class MsgPackTest2 {
  public static void main(String[] args) throws IOException {
    // Create serialize objects.
    Son2 son2 = new Son2();
    son2.setName("name");
    son2.setAge(11);
    son2.setHeight(1.2);
    son2.setId(1);

    MessagePack msgpack = new MessagePack();
    msgpack.register(Son.class);
    msgpack.register(Son2.class);

// Serialize
    byte[] raw = msgpack.write(son2);

// Deserialize directly using a template
    System.out.println(msgpack.read(raw, Son2.class).getName());
    System.out.println(msgpack.read(raw, Son2.class).getAge());

    System.out.println(msgpack.read(raw, Son.class).getName());
    System.out.println(msgpack.read(raw, Son.class).getId());

////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
    System.out.println("test 2  ////////////////////////////////////////////////////////////////////////////");
    Son son = new Son();
    son.setName("name");
    son.setHeight(1.2);
    son.setId(1);

    raw = msgpack.write(son);
    System.out.println(msgpack.read(raw, Son2.class).getName());
    System.out.println(msgpack.read(raw, Son2.class).getAge());

    System.out.println(msgpack.read(raw, Son.class).getName());
    System.out.println(msgpack.read(raw, Son.class).getId());
  }
}
