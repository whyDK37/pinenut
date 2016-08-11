#spring 攻略

## ioc

### 使用依赖检查属性

    在 spring 3 中去掉了dependency-check属性，spring3中替代dependency-check有4条建议：
    Use constructors (constructor injection instead of setter injection) exclusively to ensure the right properties are set.
    使用构造方法（使用构造方法注入替代setter注入）专门用来确认特定属性被初始化
    Create setters with a dedicated init method implemented.
    用init方法初始化setter的属性
    Create setters with @Required annotation when the property is required.
    在需要强制进行初始化的setters上标注@Required，可以参考http://www.mkyong.com/spring/spring-dependency-checking-with-required-annotation/
    Use @Autowired-driven injection which also implies a required property by default.
    使用@Autowired-driven 注入也可以实现