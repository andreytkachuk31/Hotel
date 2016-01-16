package ua.com.hotel.web.validator.client;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * @author: Андрей
 * @since: 02.01.16
 */
public class ReqistrationValidatorTest {

    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String PASSWORD_CONF = "password";
    private static final String PASSWORD_UNCONFIRM = "password_unconfirm";

    private ReqistrationValidator validator = new ReqistrationValidator();

    @Test
    public void shouldReturnEmptyFieldMessageWhenValidateIfFirstNameEmpty() {
        String errorMessage = validator.validate(EMPTY, LAST_NAME, LOGIN, PASSWORD, PASSWORD_CONF, false);

        assertEquals("Empty field found. All fields must be filled", errorMessage);
    }

    @Test
    public void shouldReturnEmptyFieldMessageWhenValidateIfLastNameEmpty() {
        String errorMessage = validator.validate(FIRST_NAME, EMPTY, LOGIN, PASSWORD, PASSWORD_CONF, false);

        assertEquals("Empty field found. All fields must be filled", errorMessage);
    }

    @Test
    public void shouldReturnEmptyFieldMessageWhenValidateIfLoginEmpty() {
        String errorMessage = validator.validate(FIRST_NAME, LAST_NAME, EMPTY, PASSWORD, PASSWORD_CONF, false);

        assertEquals("Empty field found. All fields must be filled", errorMessage);
    }

    @Test
    public void shouldReturnEmptyFieldMessageWhenValidateIfPasswordEmpty() {
        String errorMessage = validator.validate(FIRST_NAME, LAST_NAME, LOGIN, EMPTY, PASSWORD_CONF, false);

        assertEquals("Empty field found. All fields must be filled", errorMessage);
    }

    @Test
    public void shouldReturnLoginExistMessageWhenValidateIfUserExistTrue() {
        String errorMessage = validator.validate(FIRST_NAME, LAST_NAME, LOGIN, EMPTY, PASSWORD_CONF, true);

        assertEquals("Entered login already exist in system, make up another", errorMessage);
    }

    @Test
    public void shouldReturnPasswordUnconfirmMessageWhenValidateIfPasswordUnconfirmTrue() {
        String errorMessage = validator.validate(FIRST_NAME, LAST_NAME, LOGIN, PASSWORD_UNCONFIRM, PASSWORD_CONF, false);

        assertEquals("Password unconfirmed", errorMessage);
    }

    @Test
    public void shouldReturnEmptyMessageWhenValidateIfCorrectArguments() {
        String errorMessage = validator.validate(FIRST_NAME, LAST_NAME, LOGIN, PASSWORD, PASSWORD_CONF, false);

        assertEquals(EMPTY, errorMessage);
    }
}
