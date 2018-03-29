package bytebuddy;

import com.alibaba.fastjson.JSON;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.TargetType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;


public class BytebuddySample {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        new BytebuddySample().sample();
    }

    void sample() throws IllegalAccessException, InstantiationException {
        DynamicType.Builder<Object> builder = new ByteBuddy()
                .subclass(Object.class)
                .name("DynamicClass");
        Class<?> dynamicType = builder
                .method(ElementMatchers.named("toString"))
                .intercept(FixedValue.value("Hello World!"))
                // add two field
                .defineField("traceId", String.class, Modifier.PUBLIC)
                .defineField("serverTime", Date.class, Modifier.PUBLIC)
                .make()
                .load(getClass().getClassLoader())
                .getLoaded();

        System.out.println(dynamicType);
        Object obj = dynamicType.newInstance();
        System.out.println(( obj).toString());
        System.out.println(JSON.toJSONString(obj));
        for (Field field : obj.getClass().getFields()) {
            System.out.println(field.getName());
        }
        System.out.println();
        for (Method method : obj.getClass().getMethods()) {
            System.out.println(method.getName());
        }
    }
}
