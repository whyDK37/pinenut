package c5;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * 非构造函数的循环依赖，spring是可以解决的，但只能解决单例作用域的循环依赖。
 * Created by whydk on 2016/3/10.
 */
public class SingletonTest {

    public static void main(String[] args) {
        //
        DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
        //禁止循环依赖
//        bf.setAllowCircularReferences(false);
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(bf);
        reader.loadBeanDefinitions(new ClassPathResource("c5/singleton.xml"));

        TestA a = bf.getBean("testA", TestA.class);
        System.out.println(a);
        System.out.println(a.getTestB());
        TestB b = bf.getBean("testB", TestB.class);
        System.out.println("--------");
        System.out.println(b);
        System.out.println(b.getTestA());

    }

}
