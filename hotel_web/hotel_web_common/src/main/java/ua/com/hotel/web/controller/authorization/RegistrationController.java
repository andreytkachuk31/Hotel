package ua.com.hotel.web.controller.authorization;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.hotel.model.entity.user.User;
import ua.com.hotel.service.user.UserService;
import ua.com.hotel.web.validator.RegistrationValidator;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private static final Logger LOG = Logger.getLogger(RegistrationController.class);

    private static final String PAGE_REGISTRATION = "/authorization/registration";

    @Autowired
    private UserService userService;

    @Autowired
    private RegistrationValidator registrationValidator;

    @RequestMapping(method = RequestMethod.GET)
    public String showRegistrationPage() {
        return PAGE_REGISTRATION;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String registration(@RequestParam("first_name") final String firstName,
                               @RequestParam("last_name") final String lastName,
                               @RequestParam("login") final String login,
                               @RequestParam("password") final String password,
                               @RequestParam("password_conf") final String passwordConf,
                               final HttpSession session) {

        LOG.trace("Request parameter: firstName --> " + firstName);
        LOG.trace("Request parameter: lastName --> " + lastName);
        LOG.trace("Request parameter: login --> " + login);

        final String errorMessage = this.registrationValidator.validate(firstName, lastName, login, password, passwordConf);

        if (StringUtils.isNotBlank(errorMessage)) {
            session.setAttribute("error", errorMessage);
            return "redirect:/error";
        } else {
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setLogin(login);
            user.setPassword(password);

            userService.register(user);
            session.setAttribute("user", user);
            return "redirect:/home";
        }
    }
}
