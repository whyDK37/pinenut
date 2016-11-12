package java8;

import java8.able.Transformer;
import java8.pojo.Track;
import org.junit.Test;

/**
 * @author zhangxu
 */
public class FunctionalInterfaceTest {

    @Test
    public void testFunctionalInterface() {
        Track track = new Track("abc", 123);
        Transformer<Track, String> transformer = t -> t.getName();
        String res = to(transformer, track);
        System.out.println(res);
    }

    public <F, T> T to(Transformer<F, T> valuable, F f) {
        return valuable.convert(f);
    }

}
