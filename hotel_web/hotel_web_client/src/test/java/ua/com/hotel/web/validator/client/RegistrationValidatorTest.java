package ua.com.hotel.web.validator.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.com.hotel.service.user.UserService;
import ua.com.hotel.web.validator.RegistrationValidator;

import static junit.framework.Assert.assertEquals;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.mockito.Mockito.when;

/**
 * @author: Андрей
 * @since: 02.01.16
 */
@RunWith(MockitoJUnitRunner.class)
public class RegistrationValidatorTest {

    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "Password1";
    private static final String PASSWORD_CONF = "Password1";

    @Mock
    private UserService userServiceMock;

    @InjectMocks
    private RegistrationValidator validator = new RegistrationValidator();

    @Test
    public void shouldReturnEmptyFieldMessageWhenValidateIfFirstNameEmpty() {
        String errorMessage = validator.validate(EMPTY, LAST_NAME, LOGIN, PASSWORD, PASSWORD_CONF);

        assertEquals("Empty field found. All fields must be filled", errorMessage);
    }

    @Test
    public void shouldReturnEmptyFieldMessageWhenValidateIfLastNameEmpty() {
        String errorMessage = validator.validate(FIRST_NAME, EMPTY, LOGIN, PASSWORD, PASSWORD_CONF);

        assertEquals("Empty field found. All fields must be filled", errorMessage);
    }

    @Test
    public void shouldReturnEmptyFieldMessageWhenValidateIfLoginEmpty() {
        String errorMessage = validator.validate(FIRST_NAME, LAST_NAME, EMPTY, PASSWORD, PASSWORD_CONF);

        assertEquals("Empty field found. All fields must be filled", errorMessage);
    }

    @Test
    public void shouldReturnEmptyFieldMessageWhenValidateIfPasswordEmpty() {
        String errorMessage = validator.validate(FIRST_NAME, LAST_NAME, LOGIN, EMPTY, PASSWORD_CONF);

        assertEquals("Empty field found. All fields must be filled", errorMessage);
    }

    @Test
    public void shouldReturnPasswordLengthMessageWhenValidateIfPasswordLengthIncorrect() {
        String errorMessage = validator.validate(FIRST_NAME, LAST_NAME, LOGIN, "pass", PASSWORD_CONF);

        assertEquals("Password length must be between 6 and 10 characters", errorMessage);
    }

    @Test
    public void shouldReturnAtLeastOneDigitMessageWhenValidateIfPasswordNotContainsDigit() {
        String errorMessage = validator.validate(FIRST_NAME, LAST_NAME, LOGIN, "password", PASSWORD_CONF);

        assertEquals("Password should have at least one digit", errorMessage);
    }

    @Test
    public void shouldReturnAtLeastOneUpperCaseMessageWhenValidateIfPasswordNotContainsUpperCaseLetter() {
        String errorMessage = validator.validate(FIRST_NAME, LAST_NAME, LOGIN, "password1", PASSWORD_CONF);

        assertEquals("Password should have at least one upper case character", errorMessage);
    }

    @Test
    public void shouldReturnAtLeastOneLowerCaseMessageWhenValidateIfPasswordNotContainsLowerCaseLetter() {
        String errorMessage = validator.validate(FIRST_NAME, LAST_NAME, LOGIN, "PASSWORD1", PASSWORD_CONF);

        assertEquals("Password should have at least one lower case character", errorMessage);
    }

    @Test
    public void shouldReturnPasswordUnconfirmMessageWhenValidateIfPasswordUnconfirm() {
        String errorMessage = validator.validate(FIRST_NAME, LAST_NAME, LOGIN, PASSWORD, "Password_unconf1");

        assertEquals("Password unconfirmed", errorMessage);
    }

    @Test
    public void shouldReturnLoginExistMessageWhenValidateIfUserExist() {
        when(userServiceMock.isUserExist(LOGIN)).thenReturn(true);

        String errorMessage = validator.validate(FIRST_NAME, LAST_NAME, LOGIN, PASSWORD, PASSWORD_CONF);

        assertEquals("Entered login already exist in system, make up another", errorMessage);
    }

    @Test
    public void shouldReturnEmptyMessageWhenValidateIfCorrectArguments() {
        String errorMessage = validator.validate(FIRST_NAME, LAST_NAME, LOGIN, PASSWORD, PASSWORD_CONF);

        assertEquals(EMPTY, errorMessage);
    }
}
