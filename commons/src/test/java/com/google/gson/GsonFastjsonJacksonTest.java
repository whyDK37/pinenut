package com.google.gson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.Person;
import org.junit.Before;
import org.testng.annotations.Test;

/**
 * Unit tests for {@link Gson}.
 *
 * @author Ryan Harter
 */
public final class GsonFastjsonJacksonTest {

  Person person;
  int count = 99999999;
  int threads = 100;

  @Before
  public void before() {

    person = new Person("父亲", 42);
    person.setSon(new Person("儿子", 12));
  }

  @Test
  public void gson() {
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();

    for (int i = 0; i < count; i++) {
      String s = gson.toJson(person);
    }
  }

  @Test
  public void fastjson() {
    for (int i = 0; i < count; i++) {
      String s = JSON.toJSONString(person);
    }
  }
}