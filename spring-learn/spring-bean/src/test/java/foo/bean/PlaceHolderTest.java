package foo.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by why on 10/15/2016.
 */
public class PlaceHolderTest {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("foo/bean/placeholder.xml");

        SpringUtils.printBeanNames(ac);
        User user = ac.getBean("user",User.class);
        System.out.println(user);
    }


}
