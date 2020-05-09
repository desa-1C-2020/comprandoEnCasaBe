package ar.edu.unq.desapp.comprandoencasa.model;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.utils.TestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserRol.BUYER;
import static ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserRol.SELLER;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CommerceRegistrarTest {
    private CommerceRegistrar commerceRegistrar;

    @Before
    public void setUp() {
        commerceRegistrar = CommerceRegistrar.create();
    }

    @Test
    public void whenASellerRegisterACommerce_thenItHasAssociated() {
        User seller = User.createWithoutCommerce("carlos", "gonzalez", "carlos@gmail.com", SELLER);
        Commerce commerce = commerce();

        commerceRegistrar.register(seller, commerce);

        assertThat(commerceRegistrar.hasCommerceRegistered(seller), is(true));
    }

    @Test
    public void whenANonSellerUserWantRegisterACommerce_thenItFails() {
        User nonSeller = User.createWithoutCommerce("carlos", "gonzalez", "carlos@gmail.com", BUYER);
        Commerce commerce = commerce();

        Exception thrown = faillingRegisterCommerce(nonSeller, commerce);

        assertThat(thrown.getMessage(), is("No puede agregar el comercio por no ser vendedor."));
    }

    private RuntimeException faillingRegisterCommerce(User nonSeller, Commerce commerce) {
        return TestUtils.assertThrows(() -> commerceRegistrar.register(nonSeller, commerce), RuntimeException.class);
    }

    private Commerce commerce() {
        List<String> paymentMethods = new ArrayList<>();
        List<String> daysAndHoursOpen = new ArrayList<>();
        return new Commerce("un nombre de comercio", "un rubro", "un domicilio", paymentMethods, daysAndHoursOpen, "2km alcance");
    }

    @After
    public void clearRegister() {
        commerceRegistrar = null;
    }
}
