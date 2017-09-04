package java8.stream;

import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * https://www.ibm.com/developerworks/cn/java/j-lo-java8streamapi/
 */
public class StreamTest {

  @Test
  public void test() {


//        清单 4. 构造流的几种常见方法
    // 1. Individual values
    Stream stream = Stream.of("a", "b", "c");
// 2. Arrays
    String[] strArray = new String[]{"a", "b", "c"};
    stream = Stream.of(strArray);
    stream = Arrays.stream(strArray);
// 3. Collections
    List<String> list = Arrays.asList(strArray);
    stream = list.stream();


//        清单 5. 数值流的构造
    IntStream.of(new int[]{1, 2, 3}).forEach(System.out::println);
    IntStream.range(1, 3).forEach(System.out::println);
    IntStream.rangeClosed(1, 3).forEach(System.out::println);


  }

  @Test
  public void test6() {
    Stream stream = Stream.of("a", "b", "c");

//        清单 6. 流转换为其它数据结构
// 1. Array
    String[] strArray1 = (String[]) stream.toArray(String[]::new);
// 2. Collection
    stream = Stream.of("a", "b", "c");
    List<String> list1 = (List<String>) stream.collect(Collectors.toList());
    stream = Stream.of("a", "b", "c");
    List<String> list2 = (List<String>) stream.collect(Collectors.toCollection(ArrayList::new));
    stream = Stream.of("a", "b", "c");
    Set set1 = (Set) stream.collect(Collectors.toSet());
    stream = Stream.of("a", "b", "c");
    Stack stack1 = (Stack) stream.collect(Collectors.toCollection(Stack::new));
// 3. String
    stream = Stream.of("a", "b", "c");
    String str = stream.collect(Collectors.joining()).toString();
  }


}
