package ua.com.dog.hotel.web.controller.admin.users;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.dog.hotel.model.entity.user.User;
import ua.com.dog.hotel.model.entity.user.UserRole;
import ua.com.dog.hotel.model.entity.user.UserStatus;
import ua.com.dog.hotel.service.user.UserService;
import ua.com.dog.hotel.web.validator.admin.AdminUserValidator;

import javax.servlet.http.HttpSession;
import java.util.List;

import static ua.com.dog.hotel.util.Path.PAGE_USER_ADMIN;
import static ua.com.dog.hotel.util.Path.PAGE_USER_LIST_ADMIN;
import static ua.com.dog.hotel.util.Path.PAGE_USER_ADMIN_SUCCESS;

/**
 * @author Andrii_Tkachuk
 * @since 7/15/2015
 */
@Controller
@RequestMapping(value = "/admin/users")
public class AdminUserController {

    private static final Logger LOG = Logger.getLogger(AdminUserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/showList")
    public String showUsers(final Model model){
        List<User> users = userService.selectAllUsers();
        model.addAttribute("users", users);
        return PAGE_USER_LIST_ADMIN;
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String showUser(@RequestParam(required = false) final Integer id, final Model model) {
        if (id != null) {
            User user = userService.selectUserById(id);
            model.addAttribute("user", user);
        }
        return PAGE_USER_ADMIN;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@RequestParam("first_name") final String firstName,
                               @RequestParam("last_name") final String lastName,
                               @RequestParam("login") final String login,
                               @RequestParam("password") final String password,
                               @RequestParam("password_conf") final String passwordConf,
                               @RequestParam("role_id") final Integer roleId,
                               final HttpSession session) {

        LOG.trace("Request parameter: firstName --> " + firstName);
        LOG.trace("Request parameter: lastName --> " + lastName);
        LOG.trace("Request parameter: login --> " + login);

        String errorMessage = new AdminUserValidator().validate(firstName, lastName, login, password,  passwordConf, userService.isUserExist(login));

        if (StringUtils.isNotBlank(errorMessage)) {
            session.setAttribute("error", errorMessage);
            return "redirect:/admin/error";
        } else {
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setLogin(login);
            user.setPassword(password);
            user.setRole(UserRole.valueOf(roleId));

            userService.insertUser(user);

            return PAGE_USER_ADMIN_SUCCESS;
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editUser(@RequestParam("id") final Integer id,
                           @RequestParam("first_name") final String firstName,
                           @RequestParam("last_name") final String lastName,
                           @RequestParam("role_id") final Integer roleId,
                           final HttpSession session) {

        LOG.trace("Request parameter: firstName --> " + firstName);
        LOG.trace("Request parameter: lastName --> " + lastName);
        LOG.trace("Request parameter: roleId --> " + roleId);

        String errorMessage = new AdminUserValidator().validate(firstName, lastName);

        if (StringUtils.isNotBlank(errorMessage)) {
            session.setAttribute("error", errorMessage);
            return "redirect:/admin/error";
        } else {
            User user = new User();
            user.setId(id);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setStatus(UserStatus.ACTIVE);
            user.setRole(UserRole.valueOf(roleId));

            userService.updateUser(user);
            return PAGE_USER_ADMIN_SUCCESS;
        }
    }

    @RequestMapping(value = "/block", method = RequestMethod.GET)
    public String blockUser(@RequestParam("id") final Integer id) {
        userService.blockUser(id);
        return PAGE_USER_ADMIN_SUCCESS;
    }

    @RequestMapping(value = "/unblock", method = RequestMethod.GET)
    public String unBlockUser(@RequestParam("id") final Integer id) {
        userService.unBlockUser(id);
        return PAGE_USER_ADMIN_SUCCESS;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteUser(@RequestParam("id") final Integer id) {
        userService.deleteUser(id);
        return PAGE_USER_ADMIN_SUCCESS;
    }
}
