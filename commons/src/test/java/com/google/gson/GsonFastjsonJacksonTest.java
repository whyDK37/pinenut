package com.google.gson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.Person;
import com.google.gson.tutorial.Student;
import org.junit.Before;
import org.testng.annotations.Test;

/**
 * Unit tests for {@link Gson}.
 *
 * @author Ryan Harter
 */
public final class GsonFastjsonJacksonTest {

    Person person;
    int count = 999999;
    int threads = 100;

    String jsonString = "{\"name\":\"Mahesh\", \"age\":21,\"bd\":\"2018-04-11 11:39:11\"}";

    @Before
    public void before() {

        person = new Person("父亲", 42);
        person.setSon(new Person("儿子", 12));
    }

    @Test
    public void gson() {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting()
                .setDateFormat("yyyy-MM-dd hh:mm:ss");
        Gson gson = builder.create();
        for (int i = 0; i < count; i++) {
            gson.toJson(person);
            gson.fromJson(jsonString, Student.class);
        }
    }

    @Test
    public void fastjson() {

        for (int i = 0; i < count; i++) {
            JSON.toJSONString(person);
            JSON.parseObject(jsonString, Student.class);
        }
    }
}