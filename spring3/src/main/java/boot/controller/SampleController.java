package boot.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

/**
 * curl -X POST http://localhost:8080/
 */
@Controller
public class SampleController {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

}
