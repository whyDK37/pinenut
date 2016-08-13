package web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.pojo.User;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by hOward on 2015/5/31.
 */

@Controller
@RequestMapping("/rest")
public class RestfullController {
    public final Log logger = LogFactory.getFactory().getInstance(this.getClass());

    @RequestMapping("/{id}")
    public ModelAndView view(@PathVariable("id") Long id) {
        User user = new User();
        user.setID(id);
        user.setNAME("SSM");
        user.setAGE(23);

        ModelAndView mv = new ModelAndView();
        mv.addObject("user", user);
        mv.setViewName("sample/helloword");
        return mv;
    }

}
