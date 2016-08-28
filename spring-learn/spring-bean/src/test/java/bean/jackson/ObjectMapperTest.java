package bean.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import framework.c0.User;
import org.testng.annotations.Test;

/**
 * Created by why on 2016/8/21.
 */
public class ObjectMapperTest {

    @Test
    public void test() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println(objectMapper.writeValueAsString(new User()));
    }
}
