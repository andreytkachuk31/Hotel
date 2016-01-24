package ua.com.hotel.web.controller.authorization;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Andrii_Tkachuk
 * @since 7/17/2015
 */
@Controller
@RequestMapping("/mlogin")
public class MLoginController {

    private static final String PAGE_MLOGIN = "/authorization/mlogin";

    @RequestMapping(method = RequestMethod.GET)
    public String showMLoginPage(@RequestParam final Integer errorCode, final Model model) {
        model.addAttribute("errorCode", errorCode);
        return PAGE_MLOGIN;
    }
}
