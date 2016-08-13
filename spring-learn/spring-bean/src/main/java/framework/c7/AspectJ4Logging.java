package framework.c7;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * Created by Think on 2016/4/23.
 */
@Aspect
public class AspectJ4Logging {

    @Pointcut("execution(* *.*(..)) && @annotation(framework.c0.LoggingRequired)")
    public void logging() {}

    /**
     * spring 管理的所有的类，如果方法有 LoggingRequired 就会被aop拦截。
     * 这是一种基于自定义注解的拦截方式
     */
//    @Before("logging() && @annotation(logging)")
//    @Before("logging() && @annotation(c0.LoggingRequired)")
    @Before("logging()")
    public void beforeTest(JoinPoint joinPoint) {
        System.out.println("before test do logging");
    }

    @Around("logging()")
    public void aroundTest(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("Around test do logging...");
        try {
            proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("Around test do logging... done");
    }

    @After("logging()")
    public void afterTest(JoinPoint joinPoint) {
        System.out.println("After test do logging");
    }

    @AfterReturning("logging()")
    public void afterReturnTest(){
        System.out.println("after return ");
    }

    @AfterThrowing("logging()")
    public void doRecoveryActions() {
        // ...
    }
}
