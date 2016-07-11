package framework.c7;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;

/**
 * Created by Think on 2016/4/23.
 */
@Aspect
@Order(0)
public class AspectJ4TestBean {
    @Pointcut("execution(* *.test(..))")
    public void test() {}

    @Before("test()")
    public void beforeTest(JoinPoint joinPoint) {
        System.out.println("joinPoint.getKind():"+joinPoint.getKind());
        System.out.println("joinPoint.getArgs():" + joinPoint.getArgs());
        System.out.println("joinPoint.getSignature().getDeclaringType():"+joinPoint.getSignature().getDeclaringType());
        System.out.println("joinPoint.getSignature().getDeclaringTypeName():"+joinPoint.getSignature().getDeclaringTypeName());
        System.out.println("joinPoint.getSignature().getName():"+joinPoint.getSignature().getName());
        System.out.println("joinPoint.getSourceLocation():"+joinPoint.getSourceLocation());
        System.out.println("joinPoint.getTarget():"+joinPoint.getTarget());
        System.out.println("joinPoint.getThis():"+joinPoint.getThis());
        System.out.println("before test 0");
    }

    @After("test()")
    public void afterTest() {
        System.out.println("after test");
    }

    @Around("test()")
    public Object aroundTest(ProceedingJoinPoint point) {
        System.out.println("before around");
        Object o = null;
        try {
            o = point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("after around");
        return o;
    }

    @AfterReturning(pointcut = "test()",returning = "result")
    public void afterReturningTest(JoinPoint joinPoint, Object result){

        System.out.println("the method "+joinPoint.getSignature().getName()+"() end with \""+result+"\"");

    }
}
