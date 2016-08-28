package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import web.pojo.User;

import javax.servlet.http.HttpServletRequest;

/**
 * curl -X POST http://localhost:8080/hello
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public String index(HttpServletRequest request) {
        request.getAttributeNames();
        return "Greetings from Spring mvc!";
    }

    @RequestMapping("/hello/{name}")
    @ResponseBody
    public User helox(@PathVariable String name){
        User user = new User();
        user.setNAME(name);
        user.setID(1L);
        user.setAGE(12);

        return user;
    }

}