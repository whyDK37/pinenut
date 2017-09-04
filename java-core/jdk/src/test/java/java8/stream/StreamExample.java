package java8.stream;

import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * 典型用法
 * https://www.ibm.com/developerworks/cn/java/j-lo-java8streamapi/
 */
public class StreamExample {

    @Test
    public void map_flatMap() {
//
        System.out.println("清单 7. 转换大写");
        List<String> wordList = new ArrayList<>();
        wordList.add("wefDfsdf");
        wordList.add("hello");
        wordList.add("map");

        List<String> output = wordList.stream().
                map(String::toUpperCase).
                collect(Collectors.toList());

        output.stream().forEach(System.out::println);


        System.out.println("清单 8. 平方数");
        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        List<Integer> squareNums = nums.stream().
                map(n -> n * n).
                collect(Collectors.toList());
        squareNums.stream().forEach(System.out::println);


        System.out.println("清单 9. 一对多");
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        Stream<Integer> outputStream = inputStream.
                flatMap((childList) -> childList.stream());
        outputStream.forEach(System.out::println);
    }


    @Test
    public void filter() {
//        filter 对原始 Stream 进行某项测试，通过测试的元素被留下来生成一个新 Stream。
        System.out.println("清单 10. 留下偶数");
        Integer[] sixNums = {1, 2, 3, 4, 5, 6};
        Integer[] evens =
                Stream.of(sixNums).filter(n -> n % 2 == 0).toArray(Integer[]::new);
        for (Integer even : evens) {
            System.out.println(even);
        }
//        经过条件“被 2 整除”的 filter，剩下的数字为 {2, 4, 6}。

        System.out.println("清单 11. 把单词挑出来\n");
//        List<String> output = reader.lines().
//                flatMap(line -> Stream.of(line.split(REGEXP))).
//                filter(word -> word.length() > 0).
//                collect(Collectors.toList());
//        这段代码首先把每行的单词用 flatMap 整理到新的 Stream，然后保留长度不为 0 的，就是整篇文章中的全部单词了。
    }

    @Test
    public void forEach() {
        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
    }

    @Test
    public void findFirst() {
//        这是一个 termimal 兼 short-circuiting 操作，它总是返回 Stream 的第一个元素，或者空。
//        这里比较重点的是它的返回值类型：Optional。这也是一个模仿 Scala 语言中的概念，作为一个容器，它可能含有某值，或者不包含。使用它的目的是尽可能避免 NullPointerException。
        System.out.println("清单 14. Optional 的两个用例");
        String strA = " abcd ", strB = null;
        print(strA);
        print("");
        print(strB);


        getLength(strA);
        getLength("");
        getLength(strB);

//        在更复杂的 if (xx != null) 的情况中，使用 Optional 代码的可读性更好，而且它提供的是编译时检查，能极大的降低 NPE 这种 Runtime Exception 对程序的影响，或者迫使程序员更早的在编码阶段处理空值问题，而不是留到运行时再发现和调试。
//        Stream 中的 findAny、max/min、reduce 等方法等返回 Optional 值。还有例如 IntStream.average() 返回 OptionalDouble 等等。
    }

    private int getLength(String text) {
        // Java 8
        return Optional.ofNullable(text).map(String::length).orElse(-1);
        // Pre-Java 8
        // return if (text != null) ? text.length() : -1;
    }

    ;

    private void print(String text) {
        // Java 8
        Optional.ofNullable(text).ifPresent(System.out::println);
        // Pre-Java 8
        if (text != null) {
            System.out.println(text);
        }
    }


    @Test
    public void reduce() {

        // 字符串连接，concat = "ABCD"
        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        System.out.println(concat);
// 求最小值，minValue = -3.0
        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        System.out.println(minValue);
// 求和，sumValue = 10, 有起始值
        int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        System.out.println(sumValue);
// 求和，sumValue = 10, 无起始值
        sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        System.out.println(sumValue);
// 过滤，字符串连接，concat = "ace"
        concat = Stream.of("a", "B", "c", "D", "e", "F").
                filter(x -> x.compareTo("Z") > 0).
                reduce("", String::concat);
        System.out.println(concat);
    }

    @Test
    public void limit_skip() {
        System.out.println("清单 16. limit 和 skip 对运行次数的影响");
        List<Person> persons = new ArrayList();
        for (int i = 1; i <= 10000; i++) {
            Person person = new Person(i, "name" + i);
            persons.add(person);
        }
        List<String> personList2 = persons.stream().
                map(Person::getName).limit(10).skip(3).collect(Collectors.toList());
        System.out.println(personList2);

        System.out.println("清单 17. limit 和 skip 对 sorted 后的运行次数无影响");
        persons = new ArrayList();
        for (int i = 10; i > 0; i--) {
            Person person = new Person(i, "name" + i);
            persons.add(person);
        }
        List<Person> personList3 = persons.stream().sorted(Comparator.comparing(Person::getName)).limit(2).collect(Collectors.toList());
        System.out.println(personList3);
        personList3 = persons.stream().limit(2).sorted(Comparator.comparing(Person::getName)).collect(Collectors.toList());
        System.out.println(personList3);

    }

    @Test
    public void limit_skip2() {
        System.out.println("清单 17. limit 和 skip 对 sorted 后的运行次数无影响");
        ArrayList<Person> persons = new ArrayList();
        for (int i = 10; i > 0; i--) {
            Person person = new Person(i, "name" + i);
            persons.add(person);
        }
        System.out.println("limit -> sorted  会过滤数据后再排序");
        List<Person> personList3 = persons.stream().limit(3).sorted(Comparator.comparing(Person::getName)).collect(Collectors.toList());
        System.out.println(personList3);

        System.out.println("sorted -> limited");
        personList3 = persons.stream().sorted(Comparator.comparing(Person::getName)).limit(3).collect(Collectors.toList());
        System.out.println(personList3);

    }


    @Test
    public void sorted() {
//        对 Stream 的排序通过 sorted 进行，它比数组的排序更强之处在于你可以首先对 Stream 进行各类 map、filter、limit、skip
// 甚至 distinct 来减少元素数量后，再排序，这能帮助程序明显缩短执行时间。我们对清单 14 进行优化：
        System.out.println("清单 18. 优化：排序前进行 limit 和 skip");
        List<Person> persons = new ArrayList();
        for (int i = 1; i <= 5; i++) {
            Person person = new Person(i, "name" + i);
            persons.add(person);
        }
        List<Person> personList2 = persons.stream().limit(2).sorted(Comparator.comparing(Person::getName)).collect(Collectors.toList());
        System.out.println(personList2);
    }

    @Test
    public void Match() {
        List<Person> persons = new ArrayList();
        persons.add(new Person(1, "name" + 1, 10));
        persons.add(new Person(2, "name" + 2, 21));
        persons.add(new Person(3, "name" + 3, 34));
        persons.add(new Person(4, "name" + 4, 6));
        persons.add(new Person(5, "name" + 5, 55));
        boolean isAllAdult = persons.stream().
                allMatch(p -> p.getAge() > 18);
        System.out.println("All are adult? " + isAllAdult);
        boolean isThereAnyChild = persons.stream().
                anyMatch(p -> p.getAge() < 12);
        System.out.println("Any child? " + isThereAnyChild);

    }


    @Test
    public void parallelStream() {
        System.out.println("parallelStream  使用ForkJoinPool.commonPool处理元素");
        List<Person> collect = Stream.generate(new StreamExampleAdvance.PersonSupplier()).
                limit(100).collect(toList());

        collect.parallelStream().forEach(person -> System.out.println(Thread.currentThread().getName()));
    }
}
