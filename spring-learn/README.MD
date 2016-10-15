# spring笔记
## spring 的那些 processors
**BeanFactoryPostProcessor**
Allows for custom modification of an application context's bean definitions,
adapting the bean property values of the context's underlying bean factory.

Application contexts can auto-detect BeanFactoryPostProcessor beans in
their bean definitions and apply them before any other beans get created.

Useful for custom config files targeted at system administrators that
override bean properties configured in the application context.

eg.
`PropertyPlaceholderConfigurer`
`PropertySourcesPlaceholderConfigurer`
This class is designed as a general replacement for {@code
PropertyPlaceholderConfigurer} in Spring 3.1 applications. It is used by default to
support the {@code property-placeholder} element in working against the
spring-context-3.1 XSD, whereas spring-context versions &lt;= 3.0 default to
{@code PropertyPlaceholderConfigurer} to ensure backward compatibility. See
spring-context XSD documentation for complete details.

As of Spring 3.1, PropertySourcesPlaceholderConfigurer should be used preferentially over this implementation;

**BeanPostProcessor**
Factory hook that allows for custom modification of new **bean instances**,
e.g. checking for marker interfaces or wrapping them with proxies.

ApplicationContexts can autodetect BeanPostProcessor beans in their
bean definitions and apply them to any beans subsequently created.
Plain bean factories allow for programmatic registration of post-processors,
applying to all beans created through this factory.

<p>Typically, post-processors that populate beans via marker interfaces
or the like will implement {@link #postProcessBeforeInitialization},
while post-processors that wrap beans with proxies will normally
implement {@link #postProcessAfterInitialization}.

**DestructionAwareBeanPostProcessor**
Subinterface of {@link BeanPostProcessor} that adds a before-destruction callback.

he typical usage will be to invoke custom destruction callbacks on
specific bean types, matching corresponding initialization callbacks.

**InstantiationAwareBeanPostProcessor**
Subinterface of {@link BeanPostProcessor} that adds a before-instantiation callback,
and a callback after instantiation but before explicit properties are set or
autowiring occurs.

Typically used to suppress default instantiation for specific target beans,
for example to create proxies with special TargetSources (pooling targets,
lazily initializing targets, etc), or to implement additional injection strategies
such as field injection.

**BeanDefinitionRegistryPostProcessor**
```
public interface BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor
```
Extension to the standard {@link BeanFactoryPostProcessor} SPI, allowing for
the registration of further bean definitions <i>before</i> regular
BeanFactoryPostProcessor detection kicks in. In particular,
BeanDefinitionRegistryPostProcessor may register further bean definitions
which in turn define BeanFactoryPostProcessor instances.

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