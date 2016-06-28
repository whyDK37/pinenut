package c6;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Think on 2016/4/24.
 */
public class EnvironmentTest {

    public static void main(String[] args) {
        ApplicationContext ac = new MyClassPathXmlApplicationContext("c6/singleton.xml");


    }

    public static class MyClassPathXmlApplicationContext extends ClassPathXmlApplicationContext{
        public MyClassPathXmlApplicationContext(String configLocation) throws BeansException {
            super(new String[]{configLocation}, true, null);
        }

        @Override
        protected void initPropertySources() {
            super.initPropertySources();
//            this.getEnvironment().setRequiredProperties("why");
        }
    }
}
