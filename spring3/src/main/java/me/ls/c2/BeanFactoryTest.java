package me.ls.c2;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by drug on 2016/3/10.
 */
public class BeanFactoryTest {

    public static void main(String[] args) {
//        BeanFactory bf = new DefaultListableBeanFactory();
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("me/ls/c2/BeanFactoryTest.xml"));
    }
}
