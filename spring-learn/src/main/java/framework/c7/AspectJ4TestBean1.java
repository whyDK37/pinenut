package framework.c7;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;

/**
 * Created by Think on 2016/4/23.
 */
@Aspect
@Order(1)
public class AspectJ4TestBean1 {


    @Before("AspectJ4TestBean.test()")
    public void beforeTest(JoinPoint joinPoint) {
        System.out.println("before tes 1");
    }
}
