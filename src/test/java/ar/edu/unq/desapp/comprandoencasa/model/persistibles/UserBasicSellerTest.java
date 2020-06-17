package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserBasicSellerTest {

    @Test
    public void whenWantCreateUserWithCommerce_thenTheUserIsCreated() {
        Commerce commerce = new Commerce("un nombre de comercio", null, null, null, null, null);
        UserBasic userBasic = userCarlos();
        UserSeller userSeller = new UserSeller(userBasic, commerce);

        Commerce userCommerce = userSeller.getCommerceOrThrow();
        assertThat(userCommerce.equals(commerce), is(true));
    }

    @Test
    public void whenWantGetCommerceAndNotExist_thenThrowsAnException() {
        UserBasic userBasic = userCarlos();
        UserSeller userSeller = new UserSeller(userBasic, null);

        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> userSeller.getCommerceOrThrow())
            .withMessage("No posee un comercio registrado.");
    }

    @Test
    public void sameUserWithTheUserOfTheUserSeller_returnsTrue() {
        UserBasic userBasic = userCarlos();
        UserSeller userSeller = new UserSeller(userBasic, null);

        boolean isSameUser = userSeller.sameUser(userBasic);

        assertThat(isSameUser, is(true));
    }

    @Test
    public void sameUserWithDifferentUserOfTheUserSeller_returnsFalse() {
        UserBasic userBasic = userCarlos();
        UserBasic otherUserBasic = UserBasic.create("martin", "gonzalez", "martins@gmail.com", "password", null);
        UserSeller userSeller = new UserSeller(userBasic, null);

        boolean isSameUser = userSeller.sameUser(otherUserBasic);

        assertThat(isSameUser, is(false));
    }

    private UserBasic userCarlos() {
        return UserBasic.create("carlos", "gonzalez", "carlos@gmail.com", "password", null);
    }
}
