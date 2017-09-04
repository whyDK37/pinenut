package java8.stream;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Created by why on 4/19/2017.
 */
public class StreamTest {
  public static void main(String[] args) {
    Stream<String> song = Stream.of("gently", "down", "the", "stream");

    Stream<String> echos = Stream.generate(() -> "Echo");

    Stream<BigInteger> integers
            = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE));

    Stream<String> words
            = Pattern.compile("[\\P{L}]+").splitAsStream("");

    Stream<String> longestFirst =
            words.sorted(Comparator.comparing(String::length).reversed());

//        Stream<String> lowercaseWords = words.map(String::toLowerCase);
//        Stream<Character> firstChars = words.map(s -> s.charAt(0));
//        try (Stream<String> lines = Files.lines("")) {
//            Do something with lines
//        }

//        Stream<Stream<Character>> result = words.map(w -> characterStream(w));
//        Stream<Character> letters = words.flatMap(w -> characterStream(w));


    Stream<Double> randoms = Stream.generate(Math::random).limit(100);
//        randoms.forEach(aDouble -> System.out.println(aDouble));

    Object[] powers = Stream.iterate(1.0, p -> p * 2)
            .peek(e -> System.out.println("Fetching " + e))
            .limit(20).toArray();

    Stream<String> uniqueWords
            = Stream.of("merrily", "merrily", "merrily", "gently").distinct();
    uniqueWords.forEach(System.out::println);


    Stream<Integer> ints = Stream.of(1, 2, 3, 4, 5);
    Optional<Integer> reduce = ints.reduce(Integer::sum);
    reduce.ifPresent(System.out::println);

    Stream<String> strings = Stream.of("1", "2", "31");
//        Integer result = strings.mapToInt(String::length).sum();
    Integer result = strings.reduce(0, (total, word) -> total + word.length(),
            (total1, total2) -> total1 + total2);
    System.out.println(result);


  }

  public static Stream<Character> characterStream(String s) {
    List<Character> result = new ArrayList<>();
    for (char c : s.toCharArray()) result.add(c);
    return result.stream();
  }
}
