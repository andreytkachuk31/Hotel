package ua.com.hotel.web.controller.client.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Andrii_Tkachuk
 * @since 4/2/2015
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController {

    private static final String PAGE_HOME = "/client/home/home";

    @RequestMapping(method = RequestMethod.GET)
    public String showHomePage() {
        return PAGE_HOME;
    }
}
