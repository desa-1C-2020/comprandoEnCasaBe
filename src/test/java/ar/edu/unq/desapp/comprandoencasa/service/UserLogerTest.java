package ar.edu.unq.desapp.comprandoencasa.service;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.UserLoginTO;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserBuyer;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserSeller;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserLogerTest {
    private UserLogerService userLoger;

    @Mock
    private UserFinderService userFinder;

    @Before
    public void setUp() {
        userLoger = new UserLogerService(userFinder);
    }

    @Test
    public void whenWantLogInForBuyer_thenReturnTheUserBuyer() {
        User existentUser = User.create("name", "surname", "existent@email.com", "password", null);
        UserBuyer userBuyer = new UserBuyer(existentUser);
        when(userFinder.findUserById(1L)).thenReturn(existentUser);
        when(userFinder.isBuyer(existentUser)).thenReturn(true);
        when(userFinder.findBuyerByUser(existentUser)).thenReturn(userBuyer);

        UserLoginTO userLogged = userLoger.logIn(1L);

        assertThat(userLogged.getRol(), instanceOf(UserBuyer.class));
        assertThat(((UserBuyer) userLogged.getRol()).sameUser(existentUser), is(true));
    }

    @Test
    public void whenWantLogInForSeller_thenReturnTheUserSeller() {
        User existentUser = User.create("name", "surname", "existent@email.com", "password", null);
        UserSeller userSeller = new UserSeller(existentUser, mock(Commerce.class));
        when(userFinder.findUserById(1L)).thenReturn(existentUser);
        when(userFinder.findSellerByUser(existentUser)).thenReturn(userSeller);
        when(userFinder.isSeller(existentUser)).thenReturn(true);

        UserLoginTO userLogged = userLoger.logIn(1L);

        assertThat(userLogged.getRol(), instanceOf(UserSeller.class));
        assertThat(((UserSeller) userLogged.getRol()).sameUser(existentUser), is(true));
        assertThat(((UserSeller) userLogged.getRol()).getCommerce(), notNullValue());
    }
}
