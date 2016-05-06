#与 Spring 框架集成
通过 XMemcachedClientFactoryBean 类，即可与 spring 框架集成



##最简单例子

"""
<bean name="memcachedClient" destroy-method="shutdown"

    class="net.rubyeye.xmemcached.utils.XMemcachedClientFactoryBean">

    <property name="servers">

    <value>host1:port1 host2:port2</value>

    </property>

</bean>
"""

然后在 bean 中就可以使用 memcachedClient 了



##复杂一点儿的例子
"""
<bean name="memcachedClient" destroy-method="shutdown"
                class="net.rubyeye.xmemcached.utils.XMemcachedClientFactoryBean">
    <property name="servers">
        <value>host1:port1 host2:port2 host3:port3</value>
    </property>

    <property name="weights">
        <list>
            <value>1</value>
            <value>2</value>
            <value>3</value>
        </list>
    </property>

    <property name="sessionLocator">
        <bean class="net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator"/>
    </property>

    <property name="transcoder">
        <bean class="net.rubyeye.xmemcached.transcoders.SerializingTranscoder" />
    </property>

    <property name="bufferAllocator">
        <bean class="net.rubyeye.xmemcached.buffer.SimpleBufferAllocator">
    </property>
</bean>
"""