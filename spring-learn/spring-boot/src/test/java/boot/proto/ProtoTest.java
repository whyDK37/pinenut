package boot.proto;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import proto.UserProto;

import java.net.URI;

/**
 * Created by whydk on 2016/8/2.
 */
public class ProtoTest {
    private String baseUri = "http://localhost:8080";

    @Test
    public void testRead() {

        HttpHeaders headers = new HttpHeaders();
        RequestEntity<UserProto.User> requestEntity =
                new RequestEntity<UserProto.User>(headers, HttpMethod.POST, URI.create(baseUri + "/proto/read"));

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserProto.User> responseEntity =
                restTemplate.exchange(requestEntity, UserProto.User.class);

        System.out.println(responseEntity.getBody());
    }

    @Test
    public void testWrite() {
        UserProto.User user = UserProto.User.newBuilder().setID(1).setUserName("zhangsan").build();
        HttpHeaders headers = new HttpHeaders();
        RequestEntity<UserProto.User> requestEntity =
                new RequestEntity<UserProto.User>(user, headers, HttpMethod.POST, URI.create(baseUri + "/proto/write"));

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserProto.User> responseEntity =
                restTemplate.exchange(requestEntity, UserProto.User.class);
        System.out.println(responseEntity.getBody());
    }
}
