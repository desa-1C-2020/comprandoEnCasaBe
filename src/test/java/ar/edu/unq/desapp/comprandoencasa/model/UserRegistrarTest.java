package ar.edu.unq.desapp.comprandoencasa.model;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserRol.SELLER;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrarTest {

    private UserRegistrar userRegistrar;

    @Mock
    private UserFinder userFinder;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userRegistrar = new UserRegistrar(userFinder, userRepository);
    }

    @Test
    public void whenAUserRegistrarRegistersAUser_thenSaveInTheRepository() {
        User user = User.createWithoutCommerce("carlos", "gonzalez", "carlos@gmail.com", SELLER);

        userRegistrar.register(user);

        verify(userFinder, times(1)).existsUser(user);
        verify(userRepository, times(1)).addUser(user);
    }

    @Test
    public void whenAUserRegistrarRegistersAnExistingUser_thenDoNotAddIt() {
        User existingUser = User.createWithoutCommerce("carlos", "gonzalez", "carlos@gmail.com", SELLER);
        User newUSer = User.createWithoutCommerce("carlos", "gonzalez", "carlos@gmail.com", SELLER);
        when(userFinder.existsUser(newUSer)).thenReturn(true);
        userRegistrar.register(existingUser);

        String errorMessage = "No se puede registrar debido a que existe en el sistema un usuario con el email: [" + newUSer.getEmail() + "].";
        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> userRegistrar.register(newUSer))
            .withMessage(errorMessage);
        verify(userRepository, never()).addUser(newUSer);
    }

    @After
    public void clearRegister() {
        userRegistrar = null;
    }
}
