package mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(value = "/")
    public String mainPage(ModelMap model) {
//		System.out.println("Index mvc.controller");
        return "index";
    }
}