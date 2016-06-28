package c3;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by drug on 2016/3/18.
 */
public class ReplaceMethodTest {
    public static void main(String[] args) {
        //
        DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(bf);
        reader.loadBeanDefinitions(new ClassPathResource("c3/replace-method.xml"));

        ChangeMe changeMe = bf.getBean("changeMe", ChangeMe.class);
        System.out.println(changeMe.getClass().toString());
        changeMe.changeMe();
        AbstractBeanDefinition beanDefinition = (AbstractBeanDefinition)bf.getBeanDefinition("changeMe");
        System.out.println(beanDefinition.isSynthetic());
    }
}
