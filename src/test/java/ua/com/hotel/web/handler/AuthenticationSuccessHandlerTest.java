package ua.com.hotel.web.handler;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import ua.com.hotel.model.entity.user.User;
import ua.com.hotel.model.entity.user.UserPrincipal;
import ua.com.hotel.model.entity.user.UserRole;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ua.com.hotel.model.entity.user.UserRole.ROLE_ADMIN;
import static ua.com.hotel.model.entity.user.UserRole.ROLE_CLIENT;
import static ua.com.hotel.model.entity.user.UserRole.ROLE_MANAGER;

/**
 * @author: Андрей
 * @since: 27.12.15
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthenticationSuccessHandlerTest {

    @Mock
    private HttpServletRequest requestMock;

    @Mock
    private HttpServletResponse responseMock;

    @Mock
    private Authentication authenticationMock;

    @InjectMocks
    @Spy
    private AuthenticationSuccessHandler successHandler = new AuthenticationSuccessHandler();

    @Test
    public void shouldSetTargetUrlAsAdminHomeWhenOnAuthenticationSuccessIfUserRoleEqualsAdmin() throws ServletException, IOException {
        setUpUserMock(ROLE_ADMIN);

        successHandler.onAuthenticationSuccess(requestMock, responseMock, authenticationMock);

        verify(successHandler).setDefaultTargetUrl("/admin/home");
    }

    @Test
    public void shouldSetTargetUrlAsManagerHomeWhenOnAuthenticationSuccessIfUserRoleEqualsManager() throws ServletException, IOException {
        setUpUserMock(ROLE_MANAGER);

        successHandler.onAuthenticationSuccess(requestMock, responseMock, authenticationMock);

        verify(successHandler).setDefaultTargetUrl("/manager/home");
    }

    @Test
    public void shouldSetTargetUrlAsHomeWhenOnAuthenticationSuccessIfUserRoleEqualsClient() throws ServletException, IOException {
        setUpUserMock(ROLE_CLIENT);

        successHandler.onAuthenticationSuccess(requestMock, responseMock, authenticationMock);

        verify(successHandler).setDefaultTargetUrl("/home");
    }

    @Test(expected = NullPointerException.class)
         public void shouldThrowNPEWhenOnAuthenticationSuccessIfUserRoleNotExist() throws ServletException, IOException {
        setUpUserMock(null);

        successHandler.onAuthenticationSuccess(requestMock, responseMock, authenticationMock);
    }

    @Test
    public void shouldReturnCurrentUserWhenGetCurrentUser() throws ServletException, IOException {
        User userExpected = setUpUserMock(ROLE_ADMIN);

        User userActual = successHandler.getCurrentUser(authenticationMock);

        assertEquals(userExpected, userActual);
    }

    private User setUpUserMock(UserRole userRole) {
        User user = new User();
        user.setRole(userRole);

        UserPrincipal userPrincipal = mock(UserPrincipal.class);
        when(userPrincipal.getUser()).thenReturn(user);

        when(authenticationMock.getPrincipal()).thenReturn(userPrincipal);

        return user;
    }
}