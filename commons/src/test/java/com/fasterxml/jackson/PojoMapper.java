package com.fasterxml.jackson;

import com.alibaba.fastjson.Person;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import org.testng.annotations.Test;

import java.io.CharArrayReader;
import java.io.CharArrayWriter;

import static org.junit.Assert.assertEquals;

public class PojoMapper {

  @Test
  public void test() {
    Gson gson = new Gson();
    Person person = new Person("父亲", 12);
    person.setSon(person);
//        person.setSon(new Person("儿子",12));

    String s = gson.toJson(person);
    System.out.println(s);

    CharArrayReader reader = new CharArrayReader(s.toCharArray());
    JsonReader parser = new JsonReader(reader);
    parser.setLenient(true);
    JsonElement element1 = Streams.parse(parser);
    Person actualOne = gson.fromJson(element1, Person.class);
    System.out.println(actualOne);
  }

  @Test
  public void testAddingAndRemovingObjectProperties() throws Exception {
    Gson gson = new Gson();
    CharArrayWriter writer = new CharArrayWriter();
    Person person = new Person("父亲", 12);
    writer.write(gson.toJson(person).toCharArray());

    writer.write(gson.toJson(person).toCharArray());
    CharArrayReader reader = new CharArrayReader(writer.toCharArray());

    JsonReader parser = new JsonReader(reader);
    parser.setLenient(true);
    JsonElement element1 = Streams.parse(parser);
    JsonElement element2 = Streams.parse(parser);
    Person actualOne = gson.fromJson(element1, Person.class);
    assertEquals("父亲", actualOne.getName());
    Person actualTwo = gson.fromJson(element2, Person.class);
    assertEquals("父亲", actualTwo.getName());
  }
}