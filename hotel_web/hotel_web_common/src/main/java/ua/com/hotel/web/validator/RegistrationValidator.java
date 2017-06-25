package ua.com.hotel.web.validator;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.hotel.service.user.UserService;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * @author Andrii_Tkachuk
 * @since 2/24/2015
 */
@Component
public class RegistrationValidator {

    private static final Logger LOG = Logger.getLogger(RegistrationValidator.class);

    @Autowired
    private UserService userService;

    private PasswordValidator passwordValidator = new PasswordValidator();

    public String validate(String firstName, String lastName, String login, String password, String passwordConf) {
        if (StringUtils.isBlank(firstName) || StringUtils.isBlank(lastName) || StringUtils.isBlank(login) || StringUtils.isBlank(password)) {
            LOG.trace("Empty fields found when registrate");
            return "Empty field found. All fields must be filled";
        }
        if (userService.isUserExist(login)) {
            LOG.trace("Login already exist in system");
            return "Entered login already exist in system, make up another";
        }
        final String passwordErrorMessage = passwordValidator.validate(password, passwordConf);
        if (StringUtils.isNotBlank(passwordErrorMessage)) {
            LOG.trace(passwordErrorMessage);
            return passwordErrorMessage;
        }
        return EMPTY;
    }

    private class PasswordValidator {

        private static final int MIN_PASSWORD_LENGTH = 6;
        private static final int MAX_PASSWORD_LENGTH = 10;

        public String validate(final String password, final String passwordConf) {
            if (StringUtils.isBlank(password)) {
                return "Password is empty";
            } else if (isNotRangeLength(password.length())) {
                return String.format("Password length must be between %s and %s characters", MIN_PASSWORD_LENGTH, MAX_PASSWORD_LENGTH);
            } else if (!containsDigit(password)) {
                return "Password should have at least one digit";
            } else if (!containsUpperCase(password)) {
                return "Password should have at least one upper case character";
            } else if (!containsLowerCase(password)) {
                return "Password should have at least one lower case character";
            } else if (ObjectUtils.notEqual(password, passwordConf)) {
                return "Password unconfirmed";
            }
            return EMPTY;
        }

        private boolean isNotRangeLength(final int length) {
            return length < MIN_PASSWORD_LENGTH || length > MAX_PASSWORD_LENGTH;
        }

        private boolean containsDigit(String password) {
            for (char symbol : password.toCharArray()) {
                if (Character.isDigit(symbol)) {
                    return true;
                }
            }
            return false;
        }

        private boolean containsUpperCase(String password) {
            for (char symbol : password.toCharArray()) {
                if (Character.isUpperCase(symbol)) {
                    return true;
                }
            }
            return false;
        }

        private boolean containsLowerCase(String password) {
            for (char symbol : password.toCharArray()) {
                if (Character.isLowerCase(symbol)) {
                    return true;
                }
            }
            return false;
        }
    }
}
