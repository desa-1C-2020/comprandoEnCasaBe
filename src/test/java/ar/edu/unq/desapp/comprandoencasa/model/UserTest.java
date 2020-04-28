package ar.edu.unq.desapp.comprandoencasa.model;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static ar.edu.unq.desapp.comprandoencasa.model.UserRol.BUYER;
import static ar.edu.unq.desapp.comprandoencasa.model.UserRol.SELLER;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {

    @Test
    public void whenWantCreateUserWithWellformedMail_thenTheUserIsCreated() {
        User user = User.createWithoutCommerce("carlos", "gonzalez", "carlos@gmail.com", SELLER);

        assertThat(user.getName(), is("carlos"));
        assertThat(user.getSurname(), is("gonzalez"));
        assertThat(user.getEmail(), is("carlos@gmail.com"));
    }

    @Test
    public void whenWantCreateUserWithoutEmail_thenTheUserIsNotCreated() {
        assertInvalidEmail(null);
    }

    @Test
    public void whenWantCreateUserWithMalformedEmail_thenTheUserIsNotCreated() {
        assertInvalidEmail("carlosgmail.com");
    }

    @Test
    public void whenWantCreateUserWithSellerRol_thenTheUserIsSeller() {
        User user = User.createWithoutCommerce("carlos", "gonzalez", "carlos@gmail.com", SELLER);

        assertThat(user.isSeller(), is(true));
    }

    @Test
    public void whenWantCreateUserWithBuyerRol_thenTheUserIsNotSeller() {
        User user = User.createWithoutCommerce("carlos", "gonzalez", "carlos@gmail.com", BUYER);

        assertThat(user.isSeller(), is(false));
    }

    @Test
    public void whenWantCreateUserWithCommerce_thenTheUserIsCreated() {
        Commerce commerce = new Commerce(null, null, null, null, null);
        User user = User.createWithCommerce("carlos", "gonzalez", "carlos@gmail.com", SELLER, commerce);

        Optional<Commerce> userCommerce = user.getCommerce();
        assertThat(userCommerce.equals(Optional.ofNullable(commerce)), is(true));
    }

    private void assertInvalidEmail(String email) {
        Exception thrown = TestUtils.assertThrows(() -> User.createWithoutCommerce("carlos", "gonzalez", email, SELLER), RuntimeException.class);

        assertThat(thrown.getMessage(), is("El getEmail '" + email + "' no es válido."));
    }
}
