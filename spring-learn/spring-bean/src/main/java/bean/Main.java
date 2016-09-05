package bean;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by whydk on 2016/9/2.
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(("framework/bean/applicationContext.xml"));
        PropertyPlaceholderConfigurer msg = applicationContext.getBean("msg",PropertyPlaceholderConfigurer.class);
    }
}
