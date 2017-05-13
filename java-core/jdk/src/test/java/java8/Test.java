package java8;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * @author zhangxu
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Hello, World!");
        strings.add("Welcome to CoderPad.");
        strings.add("This pad is running Java 8.");
        strings.stream().map(s -> s.toLowerCase())
                .sorted((a, b) -> Integer.compare(b.length(), a.length())).collect(Collectors.toList()).forEach(
                System.out::println);

        System.out.println("for each");
        strings.forEach(s -> System.out.println(s));
        // comment here
        for (String string : strings) {
            System.out.println(string);
        }

        for (; ; ) {
            Thread.sleep(1);
        }
    }

}
