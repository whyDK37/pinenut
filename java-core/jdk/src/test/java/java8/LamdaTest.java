package java8;

import java8.pojo.Album;
import java8.pojo.Track;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;
import static java8.AlbumBuilder.getAlbums;

/**
 * Java8 lambda simple tests
 * <p>
 * TODO http://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
 * <p>
 * http://yananay.iteye.com/blog/2092524
 *
 * @author zhangxu
 */
public class LamdaTest {

  static int sum(int[] numbers) {
    return IntStream.of(numbers).reduce(0, Integer::sum);
  }

  private static void filter(List<String> names, Predicate<String> condition) {
    for (String name : names) {
      if (condition.test(name)) {
        System.out.println(name + " ");
      }
    }
  }

  @Test
  public void testInterfaceDefaultMethod() {
    List<Album> albums = getAlbums();
    for (Album album : albums) {
      album.welcom();
    }
  }

  @Test
  public void testSimpleMap() {
    List<String> res = Stream.of("abc", "xyz", "hh").map(str -> str.toUpperCase()).collect(toList());
    System.out.println(res);

    List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API",
            "Date and Time API");
    res = features.stream().map(str -> str.toUpperCase()).collect(toList());
    System.out.println(res);
  }

  @Test
  public void testMapAndJoiningCollector() {
    // Convert String to Uppercase and join them using coma
    List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany",
            "Italy", "U.K.", "Canada");
    String G7Countries = G7.stream().map(x -> x.toUpperCase())
            .collect(Collectors.joining(", "));
    System.out.println(G7Countries);

    // MethodReference way
    G7Countries = G7.stream().map(String::toLowerCase)
            .collect(Collectors.joining(", "));
    System.out.println(G7Countries);
  }

  @Test
  public void testMapForEach() {
    // applying 12% VAT on each purchase
    // Without lambda expressions:
    List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
    for (Integer cost : costBeforeTax) {
      double price = cost + .12 * cost;
      System.out.println(price);
    }

    // With Lambda expression:
    costBeforeTax.stream().map((cost) -> cost + .12 * cost)
            .forEach(System.out::println);
  }

  @Test
  public void testForEachDoubleColon() {
    List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API",
            "Date and Time API");
    features.forEach(n -> System.out.println(n));

    // Even better use Method reference feature of Java 8
    // method reference is denoted by :: (double colon) operator
    // looks similar to score resolution operator of C++
    features.forEach(System.out::println);
  }

  @Test
  public void testFilter() {
    List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API",
            "Date and Time API", "X");

    // Create a List with String more than 2 characters
    List<String> filtered = features.stream().filter(x -> x.length() > 2)
            .collect(Collectors.toList());
    System.out.printf("Original List : %s, filtered list : %s %n",
            features, filtered);
  }

  @Test
  public void testSort() {
    List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API",
            "Date and Time API");
    List<String> res = features.stream().sorted().collect(toList());
    System.out.println(res);

    Comparator<String> comp = (first, second) -> Integer.compare(first.length(), second.length());
    res = features.stream().sorted(comp).collect(toList());
    System.out.println(res);
  }

  @Test
  public void testLambdaStyleNewThread() {
    //Before Java 8:
    new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("Before Java8 ");
      }
    }).start();

    //Java 8 way:
    new Thread(() -> System.out.println("In Java8")).start();
  }

  @Test
  public void testPredicate() {
    // We can even combine Predicate using and(), or() And xor() logical functions
    // for example to find names, which starts with J and four letters long, you
    // can pass combination of two Predicate
    Predicate<String> startsWithJ = (n) -> n.startsWith("J");
    Predicate<String> fourLetterLong = (n) -> n.length() == 4;

    List<String> names = Arrays.asList("Java", "c++", "Scala", "JDK8");

    names.stream()
            .filter(startsWithJ.and(fourLetterLong))
            .forEach((n) -> System.out.print("\nName which starts with 'J' and four letter long is: " + n));
  }

  @Test
  public void testMapReduce() {
    int count = Stream.of(1, 2, 3).reduce(10, (acc, element) -> acc + element);
    int count2 = Stream.of(1, 2, 3).reduce(10, Integer::sum);
    System.out.println(count);
    System.out.println(count2);
  }

  @Test
  public void testMapReduceOptional() {
    // Applying 12% VAT on each purchase
    // Old way:
    List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
    double total = 0;
    for (Integer cost : costBeforeTax) {
      double price = cost + .12 * cost;
      total = total + price;

    }
    System.out.println("Total : " + total);

    // New way:
    double bill = costBeforeTax.stream().map((cost) -> cost + .12 * cost)
            .reduce((sum, cost) -> sum + cost)
            .get();
    System.out.println("Total : " + bill);
  }

  @Test
  public void testCount() {
    List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API",
            "Date and Time API", "X");

    // Create a List with String more than 2 characters
    long count = features.stream().filter(x -> x.length() > 2).count();
    System.out.println(count);
  }

  @Test
  public void testSkipAndLimit() {
    List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API",
            "Date and Time API");
    List<String> res = features.stream().skip(1).limit(2).collect(toList());
    System.out.println(res);
  }

  @Test
  public void testCollectorsAveragingInt() {
    List<Integer> ids = Arrays.asList(100, 200, 300, 400, 500);
    double avgLen = ids.stream().collect(averagingInt(id -> id));
    System.out.println(avgLen);
  }

  @Test
  public void testCollectorsMaxBy() {
    List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API",
            "Date and Time API");
    Function<String, Integer> getMaxLen = str -> str.length();
    Optional<String> maxLen = features.stream().collect(Collectors.maxBy(Comparator.comparing(getMaxLen)));
    System.out.println(maxLen.get());
  }

  @Test
  public void testCollectorsGroupBy() {
    List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API",
            "Date and Time API", "X", "Lambdas", "Stream API");

    Map<String, Integer> map = features.stream()
            .collect(Collectors.groupingBy(p -> p, Collectors.summingInt(p -> 1)));
    System.out.println(map);
  }

  @Test
  public void testSimpleGroupBy() {
    //3 apple, 2 banana, others 1
    List<String> items = Arrays.asList("apple", "apple", "banana",
            "apple", "orange", "banana", "papaya");

    Map<String, Long> result = items.stream().collect(
            Collectors.groupingBy(Function.identity(), Collectors.counting()));

    System.out.println(result);

    Map<Integer, List<String>> result2 = items.stream().collect(
            Collectors.groupingBy(String::length, Collectors.toList()));

    System.out.println(result2);
  }

  @Test
  public void testSimpleGroupByListObjects() {
    //3 apple, 2 banana, others 1
    List<Item> items = Arrays.asList(
            new Item("apple", 10, new BigDecimal("9.99")),
            new Item("banana", 20, new BigDecimal("19.99")),
            new Item("orang", 10, new BigDecimal("29.99")),
            new Item("watermelon", 10, new BigDecimal("29.99")),
            new Item("papaya", 20, new BigDecimal("9.99")),
            new Item("apple", 10, new BigDecimal("9.99")),
            new Item("banana", 10, new BigDecimal("19.99")),
            new Item("apple", 20, new BigDecimal("9.99"))
    );

    Map<String, Long> counting = items.stream().collect(
            Collectors.groupingBy(Item::getName, Collectors.counting()));

    System.out.println(counting);

    Map<String, Integer> sum = items.stream().collect(
            Collectors.groupingBy(Item::getName, Collectors.summingInt(Item::getQty)));

    System.out.println(sum);
  }

  @Test
  public void testSimpleGroupByPrices() {
    //3 apple, 2 banana, others 1
    List<Item> items = Arrays.asList(
            new Item("apple", 10, new BigDecimal("9.99")),
            new Item("banana", 20, new BigDecimal("19.99")),
            new Item("orang", 10, new BigDecimal("29.99")),
            new Item("watermelon", 10, new BigDecimal("29.99")),
            new Item("papaya", 20, new BigDecimal("9.99")),
            new Item("apple", 10, new BigDecimal("9.99")),
            new Item("banana", 10, new BigDecimal("19.99")),
            new Item("apple", 20, new BigDecimal("9.99"))
    );

    //group by price
    Map<BigDecimal, List<Item>> groupByPriceMap =
            items.stream().collect(Collectors.groupingBy(Item::getPrice));

    System.out.println(groupByPriceMap);

    // group by price, uses 'mapping' to convert List<Item> to Set<String>
    Map<BigDecimal, Set<String>> result =
            items.stream().collect(
                    Collectors.groupingBy(Item::getPrice,
                            Collectors.mapping(Item::getName, Collectors.toSet())
                    )
            );

    System.out.println(result);
  }

  /**
   * 你可能会觉得在这个例子里，List l被迭代了好多次，map，filter，distinct都分别是一次循环，效率会不好。实际并非如此。这些返回另一个Stream的方法都是“懒（lazy
   * ）”的，而最后返回最终结果的collect方法则是“急（eager）”的。在遇到eager方法之前，lazy的方法不会执行。
   * <p>
   * 除collect外其它的eager操作还有forEach，toArray，reduce等。
   */
  @Test
  public void testDistinct() {
    // Create List of square of all distinct numbers
    List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
    List<Integer> distinct = numbers.stream().map(i -> i * i).distinct()
            .collect(Collectors.toList());
    System.out.printf("Original List : %s,  Square Without duplicates : %s %n", numbers, distinct);
  }

  @Test
  public void testAllMatch() {
    List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
    boolean allStartsWithJ = languages.stream().anyMatch((s) -> s.startsWith("J"));
    System.out.println(allStartsWithJ);
  }

  @Test
  public void testSummaryStatistics() {
    //Get count, min, max, sum, and average for numbers
    List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
    IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x)
            .summaryStatistics();
    System.out.println("Highest prime number in List : " + stats.getMax());
    System.out.println("Lowest prime number in List : " + stats.getMin());
    System.out.println("Sum of all prime numbers : " + stats.getSum());
    System.out.println("Average of all prime numbers : " + stats.getAverage());
  }

  @Test
  public void testFunction() {
    List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

    System.out.println("Languages which starts with J :");
    filter(languages, (str) -> str.startsWith("J"));

    System.out.println("Languages which ends with a ");
    filter(languages, (str) -> str.endsWith("a"));

    System.out.println("Print all languages :");
    filter(languages, (str) -> true);

    System.out.println("Print no language : ");
    filter(languages, (str) -> false);

    System.out.println("Print language whose length greater than 4:");
    filter(languages, (str) -> str.length() > 4);
  }

  @Test
  public void testMethodReferenceCountAllTrackLength() {
    long allTrackLength = getAlbums().stream()
            .flatMap(album -> album.getTracks().stream())
            .mapToInt(Track::getLength)
            .sum();
    System.out.println(allTrackLength);
  }

  @Test
  public void testFlatMapFindLongTracks() {
    Set<String> longTrackNames = getAlbums().stream()
            .flatMap(album -> album.getTracks().stream())
            .filter(track -> track.getLength() > 60)
            .map(track -> track.getName())
            .limit(2)
            .collect(toSet());
    System.out.println(longTrackNames);
  }

  @Test
  public void testFlatMapFindLongestTracks() {
    Track longestTrack = getAlbums().stream()
            .flatMap(album -> album.getTracks().stream())
            //.max(comparing(track -> track.getLength()))
            .max(comparing(Track::getLength))
            //.max(Comparator.comparing(track -> track.getLength()))
            .get();
    System.out.println(longestTrack);
  }

  @Test
  public void testGenerateRandom() {
    Stream.generate(Math::random).limit(5).forEach(System.out::println);
  }

  @Test
  public void testGenerate() {
    Stream.generate(() -> "hello world")
            .limit(3)
            .forEach(System.out::println);
  }

  @Test
  public void testIterate() {
    Stream.iterate(0, x -> x + 1)
            .limit(5)
            .forEach(System.out::println);
  }

  /**
   * 使用并行流
   * <p>
   * 流操作可以是顺序的，也可以是并行的。顺序操作通过单线程执行，而并行操作则通过多线程执行. 可使用并行流进行操作来提高运行效率
   */
  @Test
  public void useParallelStreams() {
    // 初始化一个字符串集合
    int max = 1000000;
    List<String> values = new ArrayList<>();

    for (int i = 0; i < max; i++) {
      UUID uuid = UUID.randomUUID();
      values.add(uuid.toString());
    }

    // 使用顺序流排序
    long sequenceT0 = System.nanoTime();
    values.stream().sorted();
    long sequenceT1 = System.nanoTime();

    // 输出:sequential sort took: 51921 ms.
    System.out.format("sequential sort took: %d ms.", sequenceT1 - sequenceT0).println();

    // 使用并行流排序
    long parallelT0 = System.nanoTime();
    // default Stream<E> parallelStream() {
    // parallelStream为Collection接口的一个默认方法
    values.parallelStream().sorted();
    long parallelT1 = System.nanoTime();

    // 输出:parallel sort took: 21432 ms.
    System.out.format("parallel sort took: %d ms.", parallelT1 - parallelT0).println();

    // 从输出可以看出：并行排序快了很多
  }

  public class Item {

    private String name;
    private int qty;
    private BigDecimal price;

    public Item(String name, int qty, BigDecimal price) {
      this.name = name;
      this.qty = qty;
      this.price = price;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getQty() {
      return qty;
    }

    public void setQty(int qty) {
      this.qty = qty;
    }

    public BigDecimal getPrice() {
      return price;
    }

    public void setPrice(BigDecimal price) {
      this.price = price;
    }

    @Override
    public String toString() {
      return "Item{" +
              "name='" + name + '\'' +
              ", qty=" + qty +
              ", price=" + price +
              '}';
    }
  }

}