package framework.c2;

import framework.c0.MyTestBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.parsing.*;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

import java.util.Set;

/**
 * Created by whydk on 2016/3/10.
 */
public class BeanFactoryTest2 {
    public static void main(String[] args) {
        //
        DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(bf);
        reader.setEventListener(new ReaderEventListener(){

            @Override
            public void defaultsRegistered(DefaultsDefinition defaultsDefinition) {

            }

            @Override
            public void componentRegistered(ComponentDefinition componentDefinition) {

            }

            @Override
            public void aliasRegistered(AliasDefinition aliasDefinition) {

            }

            @Override
            public void importProcessed(ImportDefinition importDefinition) {

            }
        });
        reader.loadBeanDefinitions(new ClassPathResource("c2/beanFactory.xml"));

        MyTestBean myTestBean = bf.getBean("myTestBean", MyTestBean.class);
        System.out.println(myTestBean.getTeststr());

        BeanDefinition beanDefinition = bf.getBeanDefinition("myTestBean");
        System.out.println(beanDefinition.getClass().getCanonicalName());
        System.out.println(beanDefinition.toString());
        System.out.println("beanDefinition.getAttribute(\"m1\")="+beanDefinition.getAttribute("m1"));
        System.out.println("beanDefinition.isSingleton()="+beanDefinition.isSingleton());

        System.out.println(bf.containsLocalBean("myTestBean"));
        System.out.println(bf.isBeanNameInUse("myTestBeanA"));
        System.out.println(bf.getBeanDefinitionCount());
        System.out.println(bf.getBeanDefinitionNames());


        Set emails = bf.getBean("emails",Set.class);
        System.out.println(emails);
    }
}
