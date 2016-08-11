package boot.hello;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

/**
 * Created by whydk on 2016/7/19.
 */
public class RestfullClient {
    @Test
    public void test() {
        final String uri = "http://localhost:8080/seq/seq/topic?fc=20";

        RestTemplate restTemplate = new RestTemplate();
//        Greeting result = restTemplate.getForObject(uri, Greeting.class);
        String result = restTemplate.getForObject(uri, String .class);

        System.out.println(result);
    }
}
