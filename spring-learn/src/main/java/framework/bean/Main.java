package framework.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by whydk on 2016/6/29.
 */
public class Main {
    public static void main( String[] args ) throws Exception{

        ApplicationContext context = new ClassPathXmlApplicationContext("bean/applicationContext.xml");
        BeanLife beanlife = (BeanLife) context.getBean("beanlife");
        beanlife.doSomething();
        //销毁bean
        ((ClassPathXmlApplicationContext)context).registerShutdownHook();
    }
}
