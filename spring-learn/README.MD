# spring笔记

# spring 源码深度解析
## c6 容器的功能扩展
   ApplicationContext 是对 BeanFactory的扩展。

## c7 aop
   calc包  spring aop introduction,可以让一个接口实现动态的实现其它接口。
         通过这种方式，我们可以动态的为bean增加状态，如使用 DeclareParents 扩展 bean功能，再通过 aop 切面修改或监控状态。

   如果想通过xml方式配置声名aspectj，<aop:aspectj-autoproxy />是不必要的，应该删除，这样spring 将会忽略 AspectJ注解。
   配置的方式大体就是把注解的信息通过xml配置而已，这里就不再赘述了。

   AspectJ 都会有一个aspectOf 工程方法，如果通过 xml 配置 spring 可以配置 factory-method="aspectOf"获取当前aspect对象。
   aspectOf 这个方法是动态增加了，有时可以你可以会得到一个警告说这个方法不存在。

    spring 中的 AspectJ 加载时织入 aspect?
   将 spring bean 注入领域对象？