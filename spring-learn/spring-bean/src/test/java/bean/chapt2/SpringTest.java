package bean.chapt2;

import bean.spring.chapt2.TestBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by wanghuanyu on 2015/6/25.
 */
public class SpringTest {

    //@UserProtoTest
    public void testXmlBeanFactory(){
        XmlBeanFactory beanFactory= new XmlBeanFactory(new ClassPathResource("src/main/resources/spring-chapt2.xml"));

        TestBean testBean = beanFactory.getBean("testBean",TestBean.class);
        testBean.hello();
        testBean = beanFactory.getBean("abcbean",TestBean.class);
        testBean.hello();
        testBean = beanFactory.getBean("abean",TestBean.class);
        testBean.hello();
    }

    //@UserProtoTest
    public void testDefaultListableBeanFactory(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("spring-chapt2.xml");
        xmlBeanDefinitionReader.loadBeanDefinitions("spring-chapt3.xml");

        TestBean testBean = beanFactory.getBean("testBean2",TestBean.class);
        testBean.hello();
        testBean = (TestBean) beanFactory.getBean("testBean2");
//        testBean = beanFactory.getBean("abcbean",TestBean.class);
//        testBean.hello();
//        testBean = beanFactory.getBean("abean",TestBean.class);
//        testBean.hello();
//        testBean.getTestBean().hello();
//        testBean.getTestBean().getTestBean().hello();
    }

    //@UserProtoTest
    public void testApplicationContext(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-chapt2.xml");
        applicationContext.getBean("abcbean",TestBean.class);
        TestBean testBean = applicationContext.getBean("testBean",TestBean.class);
        testBean.hello();
        testBean = applicationContext.getBean("abcbean",TestBean.class);
        testBean.hello();
        testBean = applicationContext.getBean("abean",TestBean.class);
        testBean.hello();
        testBean.getTestBean().hello();
        testBean.getTestBean().getTestBean().hello();
    }


}
