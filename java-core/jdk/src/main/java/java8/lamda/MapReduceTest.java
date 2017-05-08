package java8.lamda;

import java.util.Arrays;
import java.util.List;

/**
 * Created by why on 5/7/2017.
 */
public class MapReduceTest {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(23, 4, 5, 66, 45, 3, 3, 22, 2, 2, 21, 4, 4, 6, 7, 9, 98, 8, 78, 34, 99);

        Integer integer1 = integers.parallelStream().filter(integer -> integer > 50).reduce((integer, integer2) -> integer > integer2 ? integer : integer2).get();
        System.out.println(integer1);

        final int max = integers.stream().reduce(0, Math::max);
        System.out.println(max);
    }
}
