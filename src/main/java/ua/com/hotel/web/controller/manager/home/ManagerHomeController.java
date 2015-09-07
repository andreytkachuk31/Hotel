package ua.com.hotel.web.controller.manager.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static ua.com.hotel.util.Path.PAGE_HOME_MANAGER;

/**
 * @author Andrii_Tkachuk
 * @since 7/14/2015
 */
@Controller
@RequestMapping("/manager/home")
public class ManagerHomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String showManagerHomePage() {
        return PAGE_HOME_MANAGER;
    }
}
