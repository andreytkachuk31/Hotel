package ua.com.hotel.service.user.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ua.com.hotel.dao.user.UserDAO;
import ua.com.hotel.model.entity.user.User;

import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.*;
import static ua.com.hotel.model.entity.user.UserRole.ROLE_CLIENT;
import static ua.com.hotel.model.entity.user.UserStatus.ACTIVE;
import static ua.com.hotel.model.entity.user.UserStatus.BLOCKED;

/**
 * @author: Андрей
 * @since: 06.12.15
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    private static final int ID = 1;
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String LOGIN = "login";
    private static final String INCORRECT_LOGIN = "incorrectLogin";
    private static final String PASSWORD = "password";
    private static final String INCORRECT_PASSWORD = "incorrectPassword";

    private User user;

    @Mock
    private UserDAO userDAO;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @InjectMocks
    private UserServiceImpl userService = new UserServiceImpl();

    @Before
    public void before() {
        user = createActiveClientUser();
    }

    @Test
    public void shouldReturnFalseWhenLoginIfLoginIncorrect() {
        when(userDAO.selectUserByLogin(LOGIN)).thenThrow(new EmptyResultDataAccessException(1));

        boolean isLogin = userService.login(LOGIN, PASSWORD);

        assertFalse(isLogin);
    }

    @Test
    public void shouldReturnFalseWhenLoginIfPasswordIncorrect() {
        when(userDAO.selectUserByLogin(LOGIN)).thenReturn(user);

        boolean isLogin = userService.login(LOGIN, INCORRECT_PASSWORD);

        assertFalse(isLogin);
    }

    @Test
    public void shouldReturnFalseWhenLoginIfUserInactive() {
        user.setStatus(BLOCKED);
        when(userDAO.selectUserByLogin(LOGIN)).thenReturn(user);

        boolean isLogin = userService.login(LOGIN, PASSWORD);

        assertFalse(isLogin);
    }

    @Test
    public void shouldReturnFalseWhenLoginIfLoginAndPasswordCorrect() {
        when(userDAO.selectUserByLogin(LOGIN)).thenReturn(user);

        boolean isLogin = userService.login(LOGIN, PASSWORD);

        assertTrue(isLogin);
    }

    @Test
    public void shouldRegisterUserWhenRegister() {
        userService.register(user);

        verify(userDAO).insertUser(userArgumentCaptor.capture());
        assertEquals(ROLE_CLIENT, userArgumentCaptor.getValue().getRole());
    }

    @Test
    public void shouldReturnUserWhenSelectUserByLoginWithCorrectLogin() {
        when(userDAO.selectUserByLogin(LOGIN)).thenReturn(user);

        User userActual = userService.selectUserByLogin(LOGIN);

        assertEquals(user, userActual);
    }

    @Test
    public void shouldNotReturnUserWhenSelectUserByLoginWitIncorrectLogin() {
        when(userDAO.selectUserByLogin(INCORRECT_LOGIN)).thenThrow(new EmptyResultDataAccessException(1));

        User userActual = userService.selectUserByLogin(INCORRECT_LOGIN);

        assertNull(userActual);
    }

    @Test
    public void shouldReturnUserWhenSelectUserById() {
        when(userDAO.selectUserById(ID)).thenReturn(user);

        User userActual = userService.selectUserById(ID);

        assertEquals(user, userActual);
    }

    @Test
    public void shouldReturnUsersWhenSelectAllUsers() {
        List<User> usersExpected = Arrays.asList(user);
        when(userDAO.selectAllUsers()).thenReturn(usersExpected);

        List<User> usersActual = userService.selectAllUsers();

        assertEquals(usersExpected, usersActual);
    }

    @Test
    public void shouldInsertUserWhenInsertUser() {
        userService.insertUser(user);

        verify(userDAO).insertUser(user);
    }

    @Test
    public void shouldUpdateUserWhenUpdateUser() {
        userService.updateUser(user);

        verify(userDAO).updateUser(user);
    }

    @Test
    public void shouldDeleteUserWhenDeleteUser() {
        userService.deleteUser(ID);

        verify(userDAO).deleteUser(ID);
    }

    @Test
    public void shouldBlockUserWhenBlockUserIfUserWasUnblock() {
        when(userDAO.selectUserById(ID)).thenReturn(user);

        userService.blockUser(ID);

        verifyBlockedUser();
    }

    @Test
    public void shouldDoNothingWhenBlockUserIfUserWasBlock() {
        user.setStatus(BLOCKED);
        when(userDAO.selectUserById(ID)).thenReturn(user);

        userService.blockUser(ID);

        verifyBlockedUser();
    }

    @Test
    public void shouldUnBlockUserWhenUnBlockUserIfUserWasBlock() {
        user.setStatus(BLOCKED);
        when(userDAO.selectUserById(ID)).thenReturn(user);

        userService.unBlockUser(ID);

        verifyUnBlockedUser();
    }

    @Test
    public void shouldDoNothingWhenUnBlockUserIfUserWasUnBlock() {
        when(userDAO.selectUserById(ID)).thenReturn(user);

        userService.unBlockUser(ID);

        verifyUnBlockedUser();
    }

    @Test
    public void shouldReturnTrueWhenIsUserExistIfLoginCorrect() {
        when(userDAO.selectUserByLogin(LOGIN)).thenReturn(user);

        boolean isUserExist = userService.isUserExist(LOGIN);

        assertTrue(isUserExist);
    }

    @Test
    public void shouldReturnFalseWhenIsUserExistIfLoginIncorrect() {
        when(userDAO.selectUserByLogin(LOGIN)).thenThrow(new EmptyResultDataAccessException(1));

        boolean isUserExist = userService.isUserExist(LOGIN);

        assertFalse(isUserExist);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void shouldThrowUsernameNotFoundExceptionWhenLoadUserByUsernameIfUserInactive() {
        user.setStatus(BLOCKED);
        when(userDAO.selectUserByLogin(LOGIN)).thenReturn(user);

        userService.loadUserByUsername(LOGIN);
    }

    private void verifyBlockedUser() {
        verify(userDAO).updateUser(userArgumentCaptor.capture());
        assertEquals(BLOCKED, userArgumentCaptor.getValue().getStatus());
    }

    private void verifyUnBlockedUser() {
        verify(userDAO).updateUser(userArgumentCaptor.capture());
        assertEquals(ACTIVE, userArgumentCaptor.getValue().getStatus());
    }

    private User createActiveClientUser() {
        User user = new User();

        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setLogin(LOGIN);
        user.setPassword(PASSWORD);
        user.setStatus(ACTIVE);
        user.setRole(ROLE_CLIENT);

        return user;
    }
}
