package bean.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 这是 spring  JdkDynamicAopProxy 的精简版实现，
 * 其实原理很简单，spring也是用Proxy和 InvocationHandler 这两个类创建代理对象。
 *
 * 在整个过程中，InvocationHandler 的 invoke 方法最重要，所有的代理逻辑全部在这里实现。
 */
public class JdkProxyDemo {
    public static void main(String[] args) {
        FooInterface foo = new FooImpl();
        FooHandler fooHandler = new FooHandler(foo);
        foo = (FooInterface)fooHandler.getProxy();
        foo.hello();
    }
}


interface FooInterface{
    void hello();
}

class FooImpl implements  FooInterface{

    @Override
    public void hello() {
        System.out.println("hello.");
    }
}

class FooHandler implements InvocationHandler{

    Object target;
    public FooHandler(Object obj){
        this.target = obj;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before...");
        Object result = method.invoke(target,args);
        System.out.println("after...");
        return result;
    }

    public Object getProxy(){
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                target.getClass().getInterfaces(),this);
    }
}