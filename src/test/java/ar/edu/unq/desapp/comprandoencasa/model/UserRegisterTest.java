package ar.edu.unq.desapp.comprandoencasa.model;

import ar.edu.unq.desapp.comprandoencasa.extensions.TestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserRegisterTest {

    private UserRegistrar userRegistrar;

    @Before
    public void setUp() {
        userRegistrar = new UserRegistrar();
    }

    @Test
    public void whenAUserRegistrarRegistersAUser_thenExistsInUserRegistrar() {
        User user = new User("carlos", "gonzalez", "carlos@gmail.com");

        userRegistrar.register(user);

        assertThat(userRegistrar.exists(user), is(true));
    }

    @Test
    public void whenAUserRegistrarRegistersAnExistingUser_thenDoNotAddIt() {
        User existingUser = new User("carlos", "gonzalez", "carlos@gmail.com");
        User newUSer = new User("carlos", "gonzalez", "carlos@gmail.com");
        userRegistrar.register(existingUser);

        Exception thrown = faillingRegisterUser(newUSer);

        assertThat(thrown.getMessage(), is("No se puede registrar con el mail, ya existe en el sistema."));
    }

    @Test
    public void whenAEmptyUserRegistrarCheckIfExistsUser_thenRespondFalse() {
        User user = new User("carlos", "gonzalez", "carlos@gmail.com");

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
