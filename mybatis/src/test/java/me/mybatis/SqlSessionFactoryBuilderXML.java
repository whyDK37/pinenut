package me.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.io.VFS;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by admin on 2015/11/5.
 */
public class SqlSessionFactoryBuilderXML {
    @Test
    public void sqlSessionFactoryTest() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //configuration
        Configuration configuration = sqlSessionFactory.getConfiguration();
        configuration.setObjectFactory(new ExampleObjectFactory());
        configuration.addInterceptor(new ExamplePlugin());

        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectByPrimaryKey(1);
        System.out.println(user);
    }

    @Test
    public void testVFS() throws IOException {
        System.out.println(VFS.getInstance().getClass());
        List<String> children = VFS.getInstance().list("me");
        for (String string : children)
            System.out.println(string);

        System.out.println(Byte[].class);
        System.out.println(Byte.class);

    }
}
