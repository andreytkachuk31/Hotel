package ua.com.hotel.web.controller.common.authorization;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static ua.com.hotel.web.util.Path.PAGE_LOGIN;

@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
    public String showLoginPage() {
        return PAGE_LOGIN;
    }
}
