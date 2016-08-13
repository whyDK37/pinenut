package boot.proto;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proto.UserProto;

@RestController
public class ProtobufController {
    @RequestMapping("/proto/read")
    public ResponseEntity<UserProto.User> protoRead() {
        return ResponseEntity.ok(UserProto.User.newBuilder().setID(1).setUserName("我的名字").setPassword("password").build());
    }

    @RequestMapping("/proto/write")
    public ResponseEntity<UserProto.User> protoWrite(RequestEntity<UserProto.User> requestEntity) {
        System.out.println("server===\n" + requestEntity.getBody());
        return ResponseEntity.ok(requestEntity.getBody());
    }
}  