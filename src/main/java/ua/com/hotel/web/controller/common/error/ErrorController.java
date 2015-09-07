package ua.com.hotel.web.controller.common.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.hotel.util.Path;

/**
 * @author Andrii_Tkachuk
 * @since 7/14/2015
 */
@Controller
public class ErrorController {

    @RequestMapping("/error")
    public String error() {
        return Path.PAGE_ERROR;
    }

    @RequestMapping("/admin/error")
    public String errorAdmin() {
        return Path.PAGE_ERROR;
    }
}
