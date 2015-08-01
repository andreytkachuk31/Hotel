package ua.com.dog.hotel.web.controller.common.inform;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static ua.com.dog.hotel.util.Path.PAGE_SUCCESS;
import static ua.com.dog.hotel.util.Path.PAGE_ERROR;

/**
 * @author Andrii_Tkachuk
 * @since 4/10/2015
 */
@Controller
public class InformController {

    @RequestMapping("/success")
    public String success() {
        return PAGE_SUCCESS;
    }

    @RequestMapping("/error")
    public String error() {
        return PAGE_ERROR;
    }
}
