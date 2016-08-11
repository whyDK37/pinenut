package boot.rest;

import boot.pojo.Greeting;
import org.springframework.web.bind.annotation.*;
import web.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.atomic.AtomicLong;

/**
 * test : curl -X POST http://localhost:8080/greeting?name=User
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
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public User delete(@PathVariable Long id){
        User user = new User();
        user.setID(id);
        return user;
    }
}