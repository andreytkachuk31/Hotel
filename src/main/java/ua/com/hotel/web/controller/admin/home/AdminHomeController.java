package ua.com.hotel.web.controller.admin.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.hotel.web.util.Path;

/**
 * @author Andrii_Tkachuk
 * @since 7/10/2015
 */
@Controller
@RequestMapping(value = "/admin/home")
public class AdminHomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String showAdminHomePage() {
        return Path.PAGE_HOME_ADMIN;
    }
}
