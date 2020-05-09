package ar.edu.unq.desapp.comprandoencasa.model;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.utils.TestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserRol.SELLER;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrarTest {

    private UserRegistrar userRegistrar;

    @Before
    public void setUp() {
        userRegistrar = new UserRegistrar();
    }

    @Test
    public void whenAUserRegistrarRegistersAUser_thenExistsInUserRegistrar() {
        User user = User.createWithoutCommerce("carlos", "gonzalez", "carlos@gmail.com", SELLER );

        userRegistrar.register(user);

        assertThat(userRegistrar.exists(user), is(true));
    }

    @Test
    public void whenAUserRegistrarRegistersAnExistingUser_thenDoNotAddIt() {
        User existingUser = User.createWithoutCommerce("carlos", "gonzalez", "carlos@gmail.com", SELLER );
        User newUSer = User.createWithoutCommerce("carlos", "gonzalez", "carlos@gmail.com", SELLER);
        userRegistrar.register(existingUser);

        Exception thrown = faillingRegisterUser(newUSer);

        assertThat(thrown.getMessage(), is("No se puede registrar con el mail, ya existe en el sistema."));
    }

    @Test
    public void whenAEmptyUserRegistrarCheckIfExistsUser_thenRespondFalse() {
        User user = User.createWithoutCommerce("carlos", "gonzalez", "carlos@gmail.com", SELLER);

        assertThat(userRegistrar.exists(user), is(false));
    }

    @After
    public void clearRegister() {
        userRegistrar = null;
    }

    private RuntimeException faillingRegisterUser(User user) {
        return TestUtils.assertThrows(() -> userRegistrar.register(user), RuntimeException.class);
    }
}
