package framework.c7;

import framework.c0.MyTestBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Think on 2016/4/23.
 */
public class AspectJSchemaTest {
    public static void main(String[] args) {

        //
//        DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
//        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(bf);
//        reader.loadBeanDefinitions(new ClassPathResource("framework.c7/aspect.xml"));

        ApplicationContext ac = new ClassPathXmlApplicationContext("c7/aspect-schema.xml");

        MyTestBean myTestBean = ac.getBean("testBean", MyTestBean.class);
        myTestBean.test();
        myTestBean.logging();

    }
}
