package ua.com.hotel.web.validator.admin;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * @author Andrii_Tkachuk
 * @since 7/17/2015
 */
public class AdminUserValidator {

    private static final Logger LOG = Logger.getLogger(AdminUserValidator.class);

    public String validate(String firstName, String lastName, String login, String password, String passwordConf, boolean userExist) {
        if (userExist) {
            LOG.trace("Login already exist in system");
            return "Entered login already exist in system, make up another";
        } else if (StringUtils.isBlank(firstName) || StringUtils.isBlank(lastName) || StringUtils.isBlank(login) || StringUtils.isBlank(password)) {
            LOG.trace("Empty fields found when registrate");
            return "Empty field found. All fields must be filled";
        } else if (!password.equals(passwordConf)) {
            LOG.trace("Password unconfirmed");
            return "Password unconfirmed";
        }
        return StringUtils.EMPTY;
    }

    public String validate(String firstName, String lastName) {
        if (StringUtils.isBlank(firstName) || StringUtils.isBlank(lastName)) {
            LOG.trace("Empty fields found when registrate");
            return "Empty field found. All fields must be filled";
        }
        return StringUtils.EMPTY;
    }
}
