package bean.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by admin on 2015/7/31.
 */
public class CglibEnhancerDemo {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CglibEnhancerDemo.class);
        enhancer.setCallback(new MethodInterceptorImpl());
        
        CglibEnhancerDemo demo = (CglibEnhancerDemo)enhancer.create();
        demo.test();
        System.out.println(demo);
    }

    public void test() {
        System.out.println("test...");
    }

    private static class MethodInterceptorImpl implements MethodInterceptor{
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("before invoke " + method);
            Object result = methodProxy.invokeSuper(o,objects);
            System.out.println("after invoke "+method);
            return result;
        }
    }
}
