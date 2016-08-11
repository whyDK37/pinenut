package bean.spring.chapt2;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * Created by admin on 2015/7/29.
 */
@Aspect
public class AspectJSample {

    @Pointcut("execution(* *hello(..))")
    public void anyhello(){}

    @Pointcut("execution(* *test(..))")
    public void test(){}

    @Before("test()")
    public void beforeTest(){
        System.out.println("before test.");
    }

    @AfterReturning("anyhello()")
    public void afterTest(){
        System.out.println("after test.");
    }

    @Around("anyhello()")
    public Object aroundTest(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("before");

        Object o = null;
        try {
            o = proceedingJoinPoint.proceed();
        }catch (Throwable e){
            e.printStackTrace();
        }

        System.out.println("after");

        return o;
    }

}
