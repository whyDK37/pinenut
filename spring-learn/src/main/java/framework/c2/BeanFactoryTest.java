package framework.c2;

import framework.c0.MyTestBean;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by whydk on 2016/3/10.
 */
public class BeanFactoryTest {
    public static void main(String[] args) {
        @SuppressWarnings("")
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("c2/beanFactory.xml"));

        MyTestBean myTestBean = bf.getBean("myTestBean", MyTestBean.class);
        System.out.println(myTestBean.getTeststr());

    }
}
