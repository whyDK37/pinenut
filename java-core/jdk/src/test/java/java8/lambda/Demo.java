package java8.lambda;

import java.io.File;
import java.io.FileFilter;
import java.util.*;
import java.util.concurrent.Callable;

/**
 * Created by whydk on 2015/11/22.
 */
public class Demo {
  public static void main(String[] args) {
    FileFilter java = (File f) -> f.getName().endsWith("*.java");

    File dir = new File("/");
    FileFilter directoryFilter = new FileFilter() {
      public boolean accept(File file) {
        return file.isDirectory();
      }
    };

    dir = new File("/");
    directoryFilter = (File f) -> f.isDirectory();
    File[] dirs = dir.listFiles(directoryFilter);

//
    dir = new File("/");
    dirs = dir.listFiles(f -> f.isDirectory());

    Callable<String> c = () -> "done";

    List<Person> people = new ArrayList<>();
    Collections.sort(people, Comparator.comparing((Person p) -> p.getLastName()));
    //
    Collections.sort(people, Comparator.comparing(p -> p.getLastName()));
    //
    Collections.sort(people, Comparator.comparing(Person::getLastName));


    people.forEach(p -> System.out.println(p));

    //Before Java 8:
    new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("Before Java8, too much code for too little to do");
      }
    }).start();
    //Java 8 way:
//        new Thread(() -> {System.out.println("In Java8, Lambda expression rocks !!");}).start();
    new Thread(() -> System.out.println("In Java8, Lambda expression rocks !!")).start();

    //In Java 8:
    List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
    System.out.println("==================");
    features.forEach(System.out::println);
    System.out.println("==================");
    features.forEach(n -> {
      System.out.println(n.getClass());
      System.out.println(n);
    });
    System.out.println("==================");
    System.out.println("less code do compare");
    Collections.sort(features, (String a, String b) -> {
      return b.compareTo(a);
    });
    Collections.sort(features, (String a, String b) -> b.compareTo(a));
    Collections.sort(features, (a, b) -> b.compareTo(a));
    System.out.println("==================");
    System.out.println("方法引用");
    Converter<String, Integer> converter = Integer::valueOf;
    Integer converted = converter.convert("123");
    System.out.println(converted);   // 123
    System.out.println("==================");
    System.out.println("引用构造函数");
    PersonFactory<Person> personFactory = Person::new;
    Person person = personFactory.create("Peter", "Parker");
    System.out.println("==================");
    System.out.println("formula");
    Formula formula = new Formula() {
      @Override
      public double calculate(int a) {
        return sqrt(a * 100);
      }
    };
    formula.calculate(100);     // 100.0
    formula.sqrt(16);           // 4.0
  }

  interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
  }

  @FunctionalInterface
  interface Converter<F, T> {
    T convert(F from);
  }

  static class Person {
    String firstName;
    String lastName;

    Person() {
    }

    Person(String firstName, String lastName) {
      this.firstName = firstName;
      this.lastName = lastName;
    }

    public String getFirstName() {
      return firstName;
    }

    public String getLastName() {
      return lastName;
    }
  }
}
