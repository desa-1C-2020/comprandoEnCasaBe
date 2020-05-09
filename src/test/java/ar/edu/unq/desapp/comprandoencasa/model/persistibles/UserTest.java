package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserRol.BUYER;
import static ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserRol.SELLER;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
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
        Commerce commerce = new Commerce("un nombre de comercio", null, null, null, null, null);
        User user = User.createWithCommerce("carlos", "gonzalez", "carlos@gmail.com", SELLER, commerce);

        Optional<Commerce> userCommerce = user.getCommerce();
        assertThat(userCommerce.equals(Optional.ofNullable(commerce)), is(true));
    }

    @Test
    public void whenWantAddProductToUserWithCommerce_thenItIsAdded() {
        Commerce commerce = new Commerce("un nombre de comercio", null, null, null, null, null);
        User user = User.createWithCommerce("carlos", "gonzalez", "carlos@gmail.com", SELLER, commerce);
        Product product = new Product("un producto", "una marca", 1, Double.MAX_VALUE, "una url");

        user.addProductToCommerce(product);

        assertThat(user.containsProductInCommerce(product), is(true));
    }

    @Test
    public void whenWantAddProductToUserWithoutCommerce_thenItIsNotAddedAndThrowsAnError() {
        User user = User.createWithCommerce("carlos", "gonzalez", "carlos@gmail.com", SELLER, null);
        Product product = new Product("un producto", "una marca", 1, Double.MAX_VALUE, "una url");

        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> user.addProductToCommerce(product))
            .withMessage("No posee un comercio registrado. No se puede agregar un producto.");
    }

    private void assertInvalidEmail(String email) {
        Exception thrown = TestUtils.assertThrows(() -> User.createWithoutCommerce("carlos", "gonzalez", email, SELLER), RuntimeException.class);

        assertThat(thrown.getMessage(), is("El getEmail '" + email + "' no es válido."));
    }
}
