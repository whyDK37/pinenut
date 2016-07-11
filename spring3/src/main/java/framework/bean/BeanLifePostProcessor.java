package framework.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @name BeanPostProcessor实现
 */
public class BeanLifePostProcessor implements BeanPostProcessor {

    /**
     * 实例化、依赖注入完毕，在调用显示的初始化之前完成一些定制的初始化任务
     * @param arg0 Bean对象
     * @param arg1 Bean的ID
     */
    public Object postProcessBeforeInitialization(Object arg0, String arg1)
            throws BeansException {
        System.out.println("BeanPostProcessor---Before "+arg1+"'s Initialization ");
        return arg0;
    }
    
    /**
     * 实例化、依赖注入、初始化完毕时执行
     * @param arg0 Bean对象
     * @param arg1 Bean的ID
     */
    public Object postProcessAfterInitialization(Object arg0, String arg1)
            throws BeansException {

        System.out.println("BeanPostProcessor---After "+arg1+"'s Initialization");
        return arg0;
    }
    
}