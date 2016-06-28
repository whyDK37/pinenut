package c7;

import c0.MyTestBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by Think on 2016/4/23.
 */
public class AspectJTest {
    public static void main(String[] args) {

//        DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
//        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(bf);
//        reader.loadBeanDefinitions(new ClassPathResource("c7/aspect.xml"));

        ApplicationContext ac = new ClassPathXmlApplicationContext("c7/aspect.xml");

        MyTestBean myTestBean = ac.getBean("testBean", MyTestBean.class);
        System.out.println(myTestBean);
        myTestBean.test();
        myTestBean.logging();

    }
}
