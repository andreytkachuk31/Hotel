package ua.com.hotel.web.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Andrii_Tkachuk
 * @since 7/14/2015
 */
@Controller
@RequestMapping("/manager/home")
public class ManagerHomeController {

    private static final String PAGE_HOME_MANAGER = "/manager/home/home";

    @RequestMapping(method = RequestMethod.GET)
    public String showManagerHomePage() {
        return PAGE_HOME_MANAGER;
    }
}
