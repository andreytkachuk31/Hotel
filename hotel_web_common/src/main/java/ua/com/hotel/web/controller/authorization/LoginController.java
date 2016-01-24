package ua.com.hotel.web.controller.authorization;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")
public class LoginController {

    private static final String PAGE_LOGIN = "/authorization/login";

    @RequestMapping(method = RequestMethod.GET)
    public String showLoginPage() {
        return PAGE_LOGIN;
    }
}
