package ua.com.dog.hotel.web.controller.common.authorization;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.dog.hotel.model.entity.user.User;
import ua.com.dog.hotel.service.user.UserService;
import ua.com.dog.hotel.web.validator.client.ReqistrationValidator;
import javax.servlet.http.HttpSession;

import static ua.com.dog.hotel.util.Path.PAGE_REGISTRATION;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private static final Logger LOG = Logger.getLogger(RegistrationController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String showReqistrationPage() {
        return PAGE_REGISTRATION;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String reqistration(@RequestParam("first_name") final String firstName,
                               @RequestParam("last_name") final String lastName,
                               @RequestParam("login") final String login,
                               @RequestParam("password") final String password,
                               @RequestParam("password_conf") final String passwordConf,
                               final HttpSession session) {

        LOG.trace("Request parameter: firstName --> " + firstName);
        LOG.trace("Request parameter: lastName --> " + lastName);
        LOG.trace("Request parameter: login --> " + login);

        String errorMessage = new ReqistrationValidator().validate(firstName, lastName, login, password,  passwordConf, userService.isUserExist(login));

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
