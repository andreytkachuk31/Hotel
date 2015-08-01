package ua.com.dog.hotel.web.controller.client.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static ua.com.dog.hotel.util.Path.PAGE_HOME;

/**
 * @author Andrii_Tkachuk
 * @since 4/2/2015
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String showHomePage() {
        return PAGE_HOME;
    }
}
