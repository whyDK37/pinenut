package c3;

import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.util.ResourceUtils;

import java.net.URISyntaxException;

/**
 * Created by whydk on 2016/3/19.
 */
public class ImportTest {

    public static void main(String[] args) throws URISyntaxException {
        // import 标签的部分解析代码测试

        // 测试
        String location = "classpath:c3/lookpu-method.xml";
        Environment environment = new StandardEnvironment();
        System.out.println(location);
        System.out.println(environment.resolveRequiredPlaceholders(location));

        //
        System.out.println("ResourcePatternUtils.isUrl(location)"+ResourcePatternUtils.isUrl(location));
        System.out.println("ResourceUtils.toURI(location).isAbsolute()"+ResourceUtils.toURI(location).isAbsolute());
    }
}
