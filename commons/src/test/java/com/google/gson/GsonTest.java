
package com.google.gson;

import com.alibaba.fastjson.Person;
import com.google.gson.internal.Excluder;
import junit.framework.TestCase;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Unit tests for {@link Gson}.
 *
 * @author Ryan Harter
 */
public final class GsonTest extends TestCase {

  private static final Excluder CUSTOM_EXCLUDER = Excluder.DEFAULT
          .excludeFieldsWithoutExposeAnnotation()
          .disableInnerClassSerialization();

  private static final FieldNamingStrategy CUSTOM_FIELD_NAMING_STRATEGY = new FieldNamingStrategy() {
    @Override
    public String translateName(Field f) {
      return "foo";
    }
  };

  public void testOverridesDefaultExcluder() {
    Gson gson = new Gson(CUSTOM_EXCLUDER, CUSTOM_FIELD_NAMING_STRATEGY,
            new HashMap<>(), true, false, true, false,
            true, true, false, LongSerializationPolicy.DEFAULT,
            new ArrayList<TypeAdapterFactory>());

    assertEquals(CUSTOM_EXCLUDER, gson.excluder());
    assertEquals(CUSTOM_FIELD_NAMING_STRATEGY, gson.fieldNamingStrategy());
    assertEquals(true, gson.serializeNulls());
    assertEquals(false, gson.htmlSafe());
  }

  @Test
  public void test() {
    Person person = new Person("父亲", 42);
    person.setSon(person);
//        person.setSon(new Person("儿子",12));

    Gson gson = new Gson();
    String s = gson.toJson(person);
    System.out.println(s);
    Person person1 = gson.fromJson(s, Person.class);
    System.out.println(person1);

    System.out.println(gson.toJson(null));
  }
}