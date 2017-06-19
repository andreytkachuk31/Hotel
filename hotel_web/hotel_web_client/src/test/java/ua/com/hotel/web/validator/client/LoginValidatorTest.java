package ua.com.hotel.web.validator.client;

import org.junit.Test;
import ua.com.hotel.web.validator.LoginValidator;

import static junit.framework.Assert.assertEquals;
import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * @author: Андрей
 * @since: 02.01.16
 */
public class LoginValidatorTest {

    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";

    private LoginValidator validator = new LoginValidator();

   @Test
    public void shouldReturnLoginEmptyMessageWhenValidateIfLoginEmpty() {
       String errorMessage = validator.validate(EMPTY, PASSWORD);

       assertEquals("Login is empty", errorMessage);
   }

    @Test
    public void shouldReturnPasswordEmptyMessageWhenValidateIfPasswordEmpty() {
        String errorMessage = validator.validate(LOGIN, EMPTY);

        assertEquals("Password is empty", errorMessage);
    }

    @Test
    public void shouldReturnLoginEmptyMessageWhenValidateIfLoginAndPasswordEmpty() {
        String errorMessage = validator.validate(EMPTY, EMPTY);

        assertEquals("Login is empty", errorMessage);
    }

    @Test
    public void shouldReturnEmptyWhenValidateIfLoginAndPasswordCorrect() {
        String errorMessage = validator.validate(LOGIN, PASSWORD);

        assertEquals(EMPTY, errorMessage);
    }
}
