package ua.com.dog.hotel.web.controller.common.authorization;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    private static final Logger LOG = Logger.getLogger(LogoutController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String logout(@CookieValue("JSESSIONID") final String JSESSIONID, final HttpSession session) {
        LOG.trace("JSESSIONID=" + JSESSIONID);
        LOG.trace("User=" + session.getAttribute("user"));
        session.invalidate();
        return "redirect:/login";
    }
}
