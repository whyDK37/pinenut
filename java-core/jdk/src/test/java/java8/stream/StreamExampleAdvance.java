package java8.stream;

import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 进阶：自己生成流
 * https://www.ibm.com/developerworks/cn/java/j-lo-java8streamapi/
 */
public class StreamExampleAdvance {

  @Test
  public void generateRandomNumber() {
    Random seed = new Random();
    Supplier<Integer> random = seed::nextInt;
    Stream.generate(random).limit(10).forEach(System.out::println);
//Another way
    IntStream.generate(() -> (int) (System.nanoTime() % 100)).
            limit(10).forEach(System.out::println);


    Stream.generate(new PersonSupplier()).
            limit(10).
            forEach(p -> System.out.println(p.getName() + ", " + p.getAge()));


    Stream.iterate(0, n -> n + 3).limit(10).forEach(x -> System.out.print(x + " "));

  }

  @Test
  public void groupingBy_partitioningBy() {
    System.out.println("清单 25. 按照年龄归组");
    Map<Integer, List<Person>> personGroups = Stream.generate(new PersonSupplier()).
            limit(100).
            collect(Collectors.groupingBy(Person::getAge));
    Iterator it = personGroups.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry<Integer, List<Person>> persons = (Map.Entry) it.next();
      System.out.println("Age " + persons.getKey() + " = " + persons.getValue().size());
    }


    System.out.println("清单 26. 按照未成年人和成年人归组");
    Map<Boolean, List<Person>> children = Stream.generate(new PersonSupplier()).
            limit(100).
            collect(Collectors.partitioningBy(p -> p.getAge() < 18));
    System.out.println("Children number: " + children.get(true).size());
    System.out.println("Adult number: " + children.get(false).size());
  }

  protected static class PersonSupplier implements Supplier<Person> {
    private int index = 0;
    private Random random = new Random();

    @Override
    public Person get() {
      return new Person(index++, "StormTestUser" + index, random.nextInt(100));
    }
  }
}
