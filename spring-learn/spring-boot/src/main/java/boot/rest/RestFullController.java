package boot.rest;

import boot.pojo.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * test : curl -X POST http://localhost:8080/user/123
 */
@RestController
public class RestFullController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    /**
     * test : curl -X DELETE http://localhost:8080/user/123
     * @param id
     * @return
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User delete(@PathVariable Long id){
        User user = new User();
        user.setID(id);
        user.setNAME("名字");
        return user;
    }
}