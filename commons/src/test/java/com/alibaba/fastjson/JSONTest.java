package com.alibaba.fastjson;

import com.alibaba.fastjson.serializer.SerializerFeature;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JSONTest {

  @Test
  public void test() {
    System.out.println(JSON.toJSONString(new Person("中文", 12), SerializerFeature.BrowserCompatible));
    System.out.println(JSON.toJSONString(new Person(null, 12)));
    System.out.println(JSON.toJSONString(new Person(null, 12), SerializerFeature.WriteNullStringAsEmpty));

    //循环依赖  fastjson 循环依赖使用特殊站位符处理，并不符合标准
    Person person = new Person("父亲", 12);
    person.setSon(person);
    String x = JSON.toJSONString(person);
    System.out.println(x);
    // 循环依赖反序列化抛异常
    Person parse = JSON.parseObject(x, Person.class);
    System.out.println(parse);
  }

  /**
   * 大对象的序列化
   *
   * @throws IOException
   */
  @Test
  public void BigJSON() throws IOException {
    JSONWriter writer = new JSONWriter(new FileWriter("/tmp/huge.json"));
    writer.startArray();
    for (int i = 0; i < 1000; ++i) {
      writer.writeValue(new Person("name" + i, i));
    }
    writer.endArray();
    writer.close();
  }

  @Test
  public void BigJSON2() throws IOException {
    JSONWriter writer = new JSONWriter(new FileWriter("/tmp/huge.json"));
    writer.startObject();
    for (int i = 0; i < 1000; ++i) {
      writer.writeKey("x" + i);
      writer.writeValue(new Person("name" + i, i));
    }
    writer.endObject();
    writer.close();
  }

  @Test
  public void read() throws FileNotFoundException {
    JSONReader reader = new JSONReader(new FileReader("/tmp/huge.json"));
    reader.startArray();
    while (reader.hasNext()) {
      Person vo = reader.readObject(Person.class);
      System.out.println(vo);
    }
    reader.endArray();
    reader.close();
  }

  @Test
  public void read2() throws FileNotFoundException {
    JSONReader reader = new JSONReader(new FileReader("/tmp/huge.json"));
    reader.startObject();
    while (reader.hasNext()) {
      String key = reader.readString();
      Person vo = reader.readObject(Person.class);
      System.out.println(key);
      System.out.println(vo);
    }
    reader.endObject();
    reader.close();
  }


}
