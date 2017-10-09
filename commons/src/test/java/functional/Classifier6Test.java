package functional;

import org.junit.Before;
import org.testng.annotations.Test;

public class Classifier6Test {
    Classifier6 classifier6;

    @Before
    public void before() {
        classifier6 = new Classifier6(23);
    }

    @Test
    public void testIsPerfect() throws Exception {
        System.out.println(classifier6.isPerfect());
    }

    @Test
    public void testIsAbundant() throws Exception {
        System.out.println(classifier6.isAbundant());
    }

    @Test
    public void testIsDeficient() throws Exception {
        System.out.println(classifier6.isDeficient());
    }

    @Test
    public void testIsPerfect1() throws Exception {
        System.out.println(classifier6.isPerfect());
    }

}