package ua.com.dog.hotel.web.controller.common.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static ua.com.dog.hotel.util.Path.PAGE_ERROR;

/**
 * @author Andrii_Tkachuk
 * @since 7/14/2015
 */
@Controller
public class ErrorController {

    @RequestMapping("/error")
    public String error() {
        return PAGE_ERROR;
    }

    @RequestMapping("/admin/error")
    public String errorAdmin() {
        return PAGE_ERROR;
    }
}
