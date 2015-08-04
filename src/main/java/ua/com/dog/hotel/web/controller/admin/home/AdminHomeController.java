package ua.com.dog.hotel.web.controller.admin.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static ua.com.dog.hotel.util.Path.PAGE_HOME;
import static ua.com.dog.hotel.util.Path.PAGE_HOME_ADMIN;

/**
 * @author Andrii_Tkachuk
 * @since 7/10/2015
 */
@Controller
@RequestMapping(value = "/admin/home")
public class AdminHomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String showAdminHomePage() {
        return PAGE_HOME_ADMIN;
    }
}
