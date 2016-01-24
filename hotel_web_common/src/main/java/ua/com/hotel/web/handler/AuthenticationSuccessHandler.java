package ua.com.hotel.web.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import ua.com.hotel.model.entity.user.User;
import ua.com.hotel.model.entity.user.UserPrincipal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Andrii_Tkachuk
 * @since 7/14/2015
 */
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        User user = getCurrentUser(authentication);

        switch (user.getRole()) {
            case ROLE_ADMIN:
                setDefaultTargetUrl("/admin/home");
                break;
            case ROLE_MANAGER:
                setDefaultTargetUrl("/manager/home");
                break;
            case ROLE_CLIENT:
                setDefaultTargetUrl("/home");
                break;
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }

    public User getCurrentUser(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return userPrincipal.getUser();
    }
}
