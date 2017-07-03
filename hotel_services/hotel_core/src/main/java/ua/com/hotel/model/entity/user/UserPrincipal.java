package ua.com.hotel.model.entity.user;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author Andrii_Tkachuk
 * @since 7/7/2015
 */
public class UserPrincipal extends org.springframework.security.core.userdetails.User {

    private final User user;

    public UserPrincipal(User user, String username, String password, boolean enabled,
                         boolean accountNonExpired, boolean credentialsNonExpired,
                         boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.user=user;
    }

    public User getUser() {
        return user;
    }
}
