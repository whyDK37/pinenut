package bean.chapt2;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.NamedThreadLocal;

import java.util.HashSet;

/**
 * Created by wanghuanyu on 2015/7/6.
 */
public class ThreadLocalTest {

    @Test
    public void test(){
        final ThreadLocal<Object> prototypesCurrentlyInCreation =
                new NamedThreadLocal<Object>("Prototype beans currently in creation");

        Assert.assertNull(prototypesCurrentlyInCreation.get());
        prototypesCurrentlyInCreation.set("abc");
        Assert.assertNotNull(prototypesCurrentlyInCreation.get());
        prototypesCurrentlyInCreation.set(new HashSet<String>());
        Assert.assertNotNull(prototypesCurrentlyInCreation.get());
        prototypesCurrentlyInCreation.remove();
        Assert.assertNull(prototypesCurrentlyInCreation.get());
        System.out.println(prototypesCurrentlyInCreation.toString());
    }
}
