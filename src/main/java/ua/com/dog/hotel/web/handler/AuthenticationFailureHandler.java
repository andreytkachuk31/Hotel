package ua.com.dog.hotel.web.handler;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import ua.com.dog.hotel.model.user.User;
import ua.com.dog.hotel.service.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Andrii_Tkachuk
 * @since 7/17/2015
 */
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String login = request.getParameter("login");
        User user = userService.selectUserByLogin(login);
        String errorCode = StringUtils.EMPTY;

        if (user == null) {
            errorCode = "1000";
        } else if (user.isBlocked()) {
            errorCode = "1001";
        } else if (user.isDeleted()) {
            errorCode = "1002";
        }

        setDefaultFailureUrl("/mlogin?errorCode=" + errorCode);
        super.onAuthenticationFailure(request, response, exception);
    }
}
