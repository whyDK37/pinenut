package framework.c3;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by whydk on 2016/3/10.
 */
public class LoolupMethodTest {
    public static void main(String[] args) {
        //
        DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(bf);
        reader.loadBeanDefinitions(new ClassPathResource("c3/lookpu-method.xml"));

        ShowMeTest  showMeTest = bf.getBean("showMeTest", ShowMeTest.class);
        showMeTest.showMe();


    }
}
