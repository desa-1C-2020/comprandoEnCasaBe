package ar.edu.unq.desapp.comprandoencasa.service;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.UserLoginTo;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserBuyer;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserSeller;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserLogerTest {
    private UserLoger userLoger;

    @Mock
    private UserFinder userFinder;

    @Before
    public void setUp() {
        userLoger = new UserLoger(userFinder);
    }

    @Test
    public void whenWantLogInWithWrongEmail_thenTrowAnError() {
        UserLoginTo userLoginTo = new UserLoginTo("nonExistent@email.com", "password");
        doThrow(new RuntimeException("Usuario o contraseña incorrectos."))
            .when(userFinder)
            .findByEmail(userLoginTo.getEmail());

        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> userLoger.logIn(userLoginTo))
            .withMessage("Usuario o contraseña incorrectos.");
    }

    @Test
    public void whenWantLogInWithWrongPassword_thenTrowAnError() {
        UserLoginTo userLoginTo = new UserLoginTo("existent@email.com", "WrongPassword");
        User existentUser = User.create("name", "surname", "existent@email.com", "password", null);
        when(userFinder.findByEmail(userLoginTo.getEmail())).thenReturn(existentUser);

        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> userLoger.logIn(userLoginTo))
            .withMessage("Usuario o contraseña incorrectos.");
    }

    @Test
    public void whenWantLogInForBuyer_thenReturnTheUserBuyer() {
        UserLoginTo userLoginTo = new UserLoginTo("existent@email.com", "password");
        User existentUser = User.create("name", "surname", "existent@email.com", "password", null);
        UserBuyer userBuyer = new UserBuyer(existentUser);
        when(userFinder.findByEmail(userLoginTo.getEmail())).thenReturn(existentUser);
        when(userFinder.isBuyer(existentUser)).thenReturn(true);
        when(userFinder.findBuyerByUser(existentUser)).thenReturn(userBuyer);

        Object userLogged = userLoger.logIn(userLoginTo);

        assertThat(userLogged, instanceOf(UserBuyer.class));
        assertThat(((UserBuyer) userLogged).sameUser(existentUser), is(true));
    }

    @Test
    public void whenWantLogInForSeller_thenReturnTheUserSeller() {
        UserLoginTo userLoginTo = new UserLoginTo("existent@email.com", "password");
        User existentUser = User.create("name", "surname", "existent@email.com", "password", null);
        UserSeller userSeller = new UserSeller(existentUser, mock(Commerce.class));
        when(userFinder.findByEmail(userLoginTo.getEmail())).thenReturn(existentUser);
        when(userFinder.isSeller(existentUser)).thenReturn(true);
        when(userFinder.findSellerByUser(existentUser)).thenReturn(userSeller);

        Object userLogged = userLoger.logIn(userLoginTo);

        assertThat(userLogged, instanceOf(UserSeller.class));
        assertThat(((UserSeller) userLogged).sameUser(existentUser), is(true));
        assertThat(((UserSeller) userLogged).getCommerceOrThrow(), notNullValue());
    }
}
