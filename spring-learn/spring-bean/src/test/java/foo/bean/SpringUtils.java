package foo.bean;

import org.springframework.context.ApplicationContext;

/**
 * Created by why on 10/15/2016.
 */
public class SpringUtils {
    public static void printBeanNames(ApplicationContext ac) {
        String[] names = ac.getBeanDefinitionNames();
        for(String name:names){
            System.out.println(name);
        }
    }
}
