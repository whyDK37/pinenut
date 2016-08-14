package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * curl -X POST http://localhost:8080/hello
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public String index() {
        return "Greetings from Spring Boot!";
    }

}