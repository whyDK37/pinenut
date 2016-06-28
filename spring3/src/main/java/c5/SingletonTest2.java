package c5;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * 这个例子演示了构造函数的循环依赖，这种情况是无解的。
 * Created by whydk on 2016/3/10.
 */
public class SingletonTest2 {

    public static void main(String[] args) {
        //
        DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(bf);
        reader.loadBeanDefinitions(new ClassPathResource("c5/singleton2.xml"));

        TestA a = bf.getBean("testA", TestA.class);

    }

}
