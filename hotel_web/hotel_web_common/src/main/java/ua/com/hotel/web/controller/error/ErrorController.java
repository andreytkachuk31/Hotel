package ua.com.hotel.web.controller.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Andrii_Tkachuk
 * @since 7/14/2015
 */
@Controller
public class ErrorController {

    private static final String PAGE_ERROR = "/error/error";

    @RequestMapping("/error")
    public String error() {
        return PAGE_ERROR;
    }

    @RequestMapping("/admin/error")
    public String errorAdmin() {
        return PAGE_ERROR;
    }
}
