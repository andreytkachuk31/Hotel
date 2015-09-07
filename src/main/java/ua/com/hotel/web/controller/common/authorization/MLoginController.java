package ua.com.hotel.web.controller.common.authorization;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static ua.com.hotel.util.Path.PAGE_MLOGIN;

/**
 * @author Andrii_Tkachuk
 * @since 7/17/2015
 */
@Controller
@RequestMapping("/mlogin")
public class MLoginController {

    @RequestMapping(method = RequestMethod.GET)
    public String showMLoginPage(@RequestParam final Integer errorCode, final Model model) {
        model.addAttribute("errorCode", errorCode);
        return PAGE_MLOGIN;
    }
}
