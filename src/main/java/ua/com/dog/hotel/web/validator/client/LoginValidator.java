package ua.com.dog.hotel.web.validator.client;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * @author Andrii_Tkachuk
 * @since 2/23/2015
 */
public class LoginValidator {

    private static final Logger LOG = Logger.getLogger(ReqistrationValidator.class);

    public String validate(String login, String password) {
        if (StringUtils.isBlank(login)) {
            LOG.trace("Login is empty");
            return "Login is empty";
        } else if (StringUtils.isBlank(password)) {
            LOG.trace("Password is empty");
            return "Password is empty";
        }
        return StringUtils.EMPTY;
    }
}