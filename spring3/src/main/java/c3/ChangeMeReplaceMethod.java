package c3;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

/**
 * Created by drug on 2016/3/18.
 */
public class ChangeMeReplaceMethod implements MethodReplacer {
    @Override
    public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
        System.out.println("我替换了原来的["+method.getName()+"]方法。");
        return null;
    }
}
