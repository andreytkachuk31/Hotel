package ua.com.dog.hotel.web.controller.common.authorization;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.dog.hotel.model.user.User;
import ua.com.dog.hotel.model.user.UserRole;
import ua.com.dog.hotel.service.user.UserService;
import ua.com.dog.hotel.util.Path;
import ua.com.dog.hotel.web.validator.LoginValidator;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    private static final Logger LOG = Logger.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String showLoginPage() {
        return Path.PAGE_LOGIN;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String login(@RequestParam("login") final String login,
                        @RequestParam("password") final String password,
                        final HttpSession session) {

        LOG.trace("Request parameter: login--> " + login);

        String errorMessage = new LoginValidator().validate(login, password);

        if (StringUtils.isNotBlank(errorMessage)) {
            session.setAttribute("error", errorMessage);
            return "redirect:/error";
        } else {
            if (userService.login(login, password)) {
                User user = userService.selectUserByLogin(login);
                session.setAttribute("user", user);
                switch (UserRole.valueOf(user.getRoleId())) {
                    case ADMIN:
                        return "redirect:/admin/home";
                    case MANAGER:
                        return "redirect:/manager/home";
                    default:
                        return "redirect:/home";
                }
            } else {
                session.setAttribute("error", "Incorrect login data");
                return "redirect:/error";
            }
        }
    }
}
