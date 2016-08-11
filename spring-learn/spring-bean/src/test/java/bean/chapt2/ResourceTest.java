package bean.chapt2;

import org.junit.Test;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * Created by wanghuanyu on 2015/6/28.
 */
public class ResourceTest {
    @Test
    public void testProperties() throws IOException {
        String resourceName = "META-INF/spring.handlers";
        System.out.println("read all jar`s "+resourceName+"");
        ClassLoader classLoaderToUse = ClassUtils.getDefaultClassLoader();
        Enumeration<URL> urls = (classLoaderToUse != null ? classLoaderToUse.getResources(resourceName) :
                ClassLoader.getSystemResources(resourceName));
        while(urls.hasMoreElements()){
            URL url = urls.nextElement();
            System.out.println(url.toString());
        }
    }
}
