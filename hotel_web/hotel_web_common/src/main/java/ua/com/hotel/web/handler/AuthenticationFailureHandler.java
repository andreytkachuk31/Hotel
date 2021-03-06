package ua.com.hotel.web.handler;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import ua.com.hotel.model.entity.user.User;
import ua.com.hotel.service.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Andrii_Tkachuk
 * @since 7/17/2015
 */
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private static final int MAX_FAIL_LOGIN_ATTEMPT = 5;

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
        } else {
            errorCode = "1003"; // password wrong
            int userId = user.getId();
            if (user.getFailLoginAttempt() >= MAX_FAIL_LOGIN_ATTEMPT) {
                userService.blockUser(userId);
                userService.invalidateFailLoginAttempt(userId);
                errorCode = "1001";
            } else {
                userService.increaseFailLoginAttempt(userId);
            }
        }

        setDefaultFailureUrl("/mlogin?errorCode=" + errorCode);
        super.onAuthenticationFailure(request, response, exception);
    }
}
