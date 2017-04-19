package java8.stream;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by why on 4/19/2017.
 */
public class ListCount {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.add("a");
        words.add("bd");
        words.add("c");

        long count = words.stream().filter(w -> w.length() > 1).count();
        System.out.println(count);
        count = words.parallelStream().filter(w -> w.length() > 1).count();
        System.out.println(count);
    }
}
