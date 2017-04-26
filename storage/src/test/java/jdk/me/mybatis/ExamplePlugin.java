package jdk.me.mybatis;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * Created by admin on 2015/11/5.
 */
@Intercepts(
        //Signature定义被拦截的接口方法，可以有一个或多个。
        @Signature(
                //拦截的接口类型，支持 Executor,ParameterHandler,ResultSetHandler,StatementHandler
                //这里以Executor为例
                type = Executor.class,
                //Executor中的方法名
                method = "query",
                //Executor中query方法的参数类型
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
        ))
public class ExamplePlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //我们可以在这里做一些扩展工作，但一定要在了解mybatis运行原理之后才能开发出你所期望的效果。
        System.out.println("example plugin ..." + invocation);
        //因为mybatis的使用责任链方式，这里一定要显示的调用proceed方法便调用能传递下去。
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        //判断是否是本拦截器需要拦截的接口类型，如果是增加代理
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        }
        //如果不是返回源对象。
        else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {
        //可以设置拦截器的属性，在这里我先忽略。
    }
}


