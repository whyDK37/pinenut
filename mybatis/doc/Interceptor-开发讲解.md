# Mybatis Interceptor 讲解

## 简介

Mybatis可以很方便的通过插件进行扩展。

## 实现  

插件类首先必须实现`org.apache.ibatis.plugin.Interceptor`接口，如下：

    @Intercepts(
            //Signature定义拦截的方法，可以有一个或多个。
            @Signature(
                    //拦截的类型
                    type = Executor.class,
                    method = "query",
                    args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
            ))
    public class ExamplePlugin implements Interceptor {
        @Override
        public Object intercept(Invocation invocation) throws Throwable {
            System.out.println("example plugin ..." + invocation);
            return invocation.proceed();
        }
        @Override
        public Object plugin(Object target) {
            if (target instanceof Executor) {
                return Plugin.wrap(target, this);
            } else {
                return target;
            }
        }
        @Override
        public void setProperties(Properties properties) {
        }
    }


