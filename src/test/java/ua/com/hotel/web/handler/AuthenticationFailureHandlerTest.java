package ua.com.hotel.web.handler;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.AuthenticationException;
import ua.com.hotel.model.entity.user.User;
import ua.com.hotel.model.entity.user.UserStatus;
import ua.com.hotel.service.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ua.com.hotel.model.entity.user.UserStatus.ACTIVE;
import static ua.com.hotel.model.entity.user.UserStatus.BLOCKED;
import static ua.com.hotel.model.entity.user.UserStatus.DELETED;

/**
 * @author: Андрей
 * @since: 02.01.16
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthenticationFailureHandlerTest {

    private static final String LOGIN = "login";
    private static final String INCORRECT_LOGIN = "incorrect_login";

    @Mock
    private HttpServletRequest requestMock;

    @Mock
    private HttpServletResponse responseMock;

    @Mock
    private HttpSession sessionMock;

    @Mock
    private AuthenticationException authenticationExceptionMock;

    @Mock
    private UserService userService;

    @InjectMocks
    @Spy
    private AuthenticationFailureHandler failureHandler = new AuthenticationFailureHandler();

    @Before
    public void before() {
        setUpHttpSessionForAuthenticationException();
    }

    @Test
    public void shouldSetFailureUrlAsMloginAndErrorCode1000WhenOnAuthenticationFailureIfUserNotExist() throws ServletException, IOException {
        when(requestMock.getParameter("login")).thenReturn(INCORRECT_LOGIN);
        when(userService.selectUserByLogin(INCORRECT_LOGIN)).thenReturn(null);

        failureHandler.onAuthenticationFailure(requestMock, responseMock, authenticationExceptionMock);

        verify(failureHandler).setDefaultFailureUrl("/mlogin?errorCode=1000");
    }

    @Test
    public void shouldSetFailureUrlAsMloginAndErrorCode1001WhenOnAuthenticationFailureIfUserBlocked() throws ServletException, IOException {
        setUpUserService(BLOCKED);

        failureHandler.onAuthenticationFailure(requestMock, responseMock, authenticationExceptionMock);

        verify(failureHandler).setDefaultFailureUrl("/mlogin?errorCode=1001");
    }

    @Test
    public void shouldSetFailureUrlAsMloginAndErrorCode1002WhenOnAuthenticationFailureIfUserDeleted() throws ServletException, IOException {
        setUpUserService(DELETED);

        failureHandler.onAuthenticationFailure(requestMock, responseMock, authenticationExceptionMock);

        verify(failureHandler).setDefaultFailureUrl("/mlogin?errorCode=1002");
    }

    @Test
    public void shouldSetFailureUrlAsMloginAndErrorCodeEmptyWhenOnAuthenticationFailureIfUserActive() throws ServletException, IOException {
        setUpUserService(ACTIVE);

        failureHandler.onAuthenticationFailure(requestMock, responseMock, authenticationExceptionMock);

        verify(failureHandler).setDefaultFailureUrl("/mlogin?errorCode=");
    }

    private void setUpUserService(final UserStatus userStatus) {
        User user = new User();
        user.setStatus(userStatus);

        when(requestMock.getParameter("login")).thenReturn(LOGIN);
        when(userService.selectUserByLogin(LOGIN)).thenReturn(user);
    }

    private void setUpHttpSessionForAuthenticationException() {
        when(requestMock.getSession()).thenReturn(sessionMock);
    }
}
