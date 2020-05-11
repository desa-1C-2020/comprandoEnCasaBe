package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import ar.com.kfgodel.nary.api.optionals.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserRol.SELLER;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserSellerTest {

    @Test
    public void whenWantCreateUserWithCommerce_thenTheUserIsCreated() {
        Commerce commerce = new Commerce("un nombre de comercio", null, null, null, null, null);
        User user = User.create("carlos", "gonzalez", "carlos@gmail.com");
        UserSeller userSeller = new UserSeller(user, SELLER, commerce);

        Optional<Commerce> userCommerce = userSeller.getCommerce();
        assertThat(userCommerce.equals(Optional.ofNullable(commerce)), is(true));
    }

    @Test
    public void whenWantAddProductToUserWithCommerce_thenItIsAdded() {
        Commerce commerce = new Commerce("un nombre de comercio", null, null, null, null, null);
        User user = User.create("carlos", "gonzalez", "carlos@gmail.com");
        Product product = new Product("un producto", "una marca", 1, Double.MAX_VALUE, "una url");
        UserSeller userSeller = new UserSeller(user, SELLER, commerce);

        userSeller.addProductToCommerce(product);

        assertThat(userSeller.containsProductInCommerceId(product.getId()), is(true));
    }

    @Test
    public void whenWantAddProductToUserWithoutCommerce_thenItIsNotAddedAndThrowsAnError() {
        User user = User.create("carlos", "gonzalez", "carlos@gmail.com");
        Product product = new Product("un producto", "una marca", 1, Double.MAX_VALUE, "una url");
        UserSeller userSeller = new UserSeller(user, SELLER, null);

        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> userSeller.addProductToCommerce(product))
            .withMessage("No posee un comercio registrado. No se puede agregar un producto.");
    }
}
