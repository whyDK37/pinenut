package framework.c3;

import framework.c0.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by drug on 2016/3/18.
 */
public class ConstructorTest {
    public static void main(String[] args) {
        //
        DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(bf);
        reader.loadBeanDefinitions(new ClassPathResource("c3/constructor-arg.xml"));

        User user = bf.getBean("user", User.class);
        System.out.println(user.getName());
        System.out.println(user.getAge());


    }
}
