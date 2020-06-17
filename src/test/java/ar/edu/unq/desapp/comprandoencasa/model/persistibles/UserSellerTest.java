package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserSellerTest {

    @Test
    public void whenWantCreateUserWithCommerce_thenTheUserIsCreated() {
        Commerce commerce = new Commerce("un nombre de comercio", null, null, null, null, null);
        User user = userCarlos();
        UserSeller userSeller = new UserSeller(user, commerce);

        Commerce userCommerce = userSeller.getCommerceOrThrow();
        assertThat(userCommerce.equals(commerce), is(true));
    }

    @Test
    public void whenWantGetCommerceAndNotExist_thenThrowsAnException() {
        User user = userCarlos();
        UserSeller userSeller = new UserSeller(user, null);

        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> userSeller.getCommerceOrThrow())
            .withMessage("No posee un comercio registrado.");
    }

    @Test
    public void sameUserWithTheUserOfTheUserSeller_returnsTrue() {
        User user = userCarlos();
        UserSeller userSeller = new UserSeller(user, null);

        boolean isSameUser = userSeller.sameUser(user);

        assertThat(isSameUser, is(true));
    }

    @Test
    public void sameUserWithDifferentUserOfTheUserSeller_returnsFalse() {
        User user = userCarlos();
        User otherUser = User.create("martin", "gonzalez", "martins@gmail.com", "password", null);
        UserSeller userSeller = new UserSeller(user, null);

        boolean isSameUser = userSeller.sameUser(otherUser);

        assertThat(isSameUser, is(false));
    }

    private User userCarlos() {
        return User.create("carlos", "gonzalez", "carlos@gmail.com", "password", null);
    }
}
