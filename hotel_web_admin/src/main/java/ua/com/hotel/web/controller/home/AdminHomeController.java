package ua.com.hotel.web.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Andrii_Tkachuk
 * @since 7/10/2015
 */
@Controller
@RequestMapping(value = "/admin/home")
public class AdminHomeController {

    private static final String PAGE_HOME_ADMIN = "/admin/home/home";

    @RequestMapping(method = RequestMethod.GET)
    public String showAdminHomePage() {
        return PAGE_HOME_ADMIN;
    }
}
