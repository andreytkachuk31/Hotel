package ua.com.dog.hotel.web.validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import ua.com.dog.hotel.model.user.User;

/**
 * @author Andrii_Tkachuk
 * @since 2/24/2015
 */
public class ReqistrationValidator {

    private static final Logger LOG = Logger.getLogger(ReqistrationValidator.class);

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
}
