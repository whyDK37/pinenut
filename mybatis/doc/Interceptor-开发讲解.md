# Mybatis Interceptor ����

## ���

Mybatis���Ժܷ����ͨ�����������չ��

## ʵ��  

��������ȱ���ʵ��`org.apache.ibatis.plugin.Interceptor`�ӿڣ����£�

    @Intercepts(
            //Signature�������صķ�����������һ��������
            @Signature(
                    //���ص�����
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


