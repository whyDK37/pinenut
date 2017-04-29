package HackerRank;

/**
 * Created by why on 4/26/2017.
 */
public class HTest {
    public static void main(String[] args) {
        System.out.println((0 ^ 0) % 2);
        System.out.println((1 ^ 0) % 2);

        long start = System.nanoTime();
        int j1 = 0;
        for (int i = 0; i < 100000; i++) {
            j1++;
        }
        long start2 = System.nanoTime();
        int j2 = 0;
        for (int i = 0; i < 100000; i++) {
            ++j2;
        }
        long start3 = System.nanoTime();
        System.out.println(start3 - start2);
        System.out.println(start2 - start);
    }
}
