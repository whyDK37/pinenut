package c2;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by whydk on 2016/3/10.
 */
public class BeanFactoryTest2 {
    public static void main(String[] args) {
        //
        DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(bf);
        reader.loadBeanDefinitions(new ClassPathResource("c2/beanFactory.xml"));

        MyTestBean myTestBean = bf.getBean("myTestBean", MyTestBean.class);
        System.out.println(myTestBean.getTeststr());

        BeanDefinition beanDefinition = bf.getBeanDefinition("myTestBean");
        System.out.println(beanDefinition.getClass().toGenericString());
        System.out.println(beanDefinition.toString());

        System.out.println(bf.containsLocalBean("myTestBean"));
        System.out.println(bf.isBeanNameInUse("myTestBeanA"));
        System.out.println(bf.getBeanDefinitionCount());
        System.out.println(bf.getBeanDefinitionNames());
    }
}
