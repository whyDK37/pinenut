package bean.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by admin on 2015/7/31.
 */
public class JdkProxy implements InvocationHandler{

    private Object target;
    public JdkProxy(Object userService){
        this.target = userService;
    }
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        System.out.println(userService);
        JdkProxy jdkProxy = new JdkProxy(userService);
        userService = (UserService)jdkProxy.getProxy();
        userService.add();
        System.out.println(userService);
    }

    public Object getProxy(){
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),target.getClass().getInterfaces(),this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before ............");

        Object result = method.invoke(target,args);
        System.out.println("after..............");
        return result;
    }
}
