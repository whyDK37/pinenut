package bean.context;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by whydk on 2016/9/19.
 */
@Component
public class SpringUtils implements ApplicationContextAware, InitializingBean{
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtils.applicationContext = applicationContext;
        System.out.println("set application context");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("after property set");
    }
}
