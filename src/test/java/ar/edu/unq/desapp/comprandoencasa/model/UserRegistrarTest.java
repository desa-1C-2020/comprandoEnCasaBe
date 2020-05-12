package ar.edu.unq.desapp.comprandoencasa.model;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.AddressTo;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.RegisterUserTO;
import ar.edu.unq.desapp.comprandoencasa.extensions.ObjectMapper;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserBuyer;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserBuyerRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserSellerRepository;
import ar.edu.unq.desapp.comprandoencasa.service.UserFinder;
import ar.edu.unq.desapp.comprandoencasa.service.UserRegistrar;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
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

    @Mock
    private UserSellerRepository userSellerRepository;

    @Mock
    private UserBuyerRepository userBuyerRepository;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @Captor
    private ArgumentCaptor<UserBuyer> userBuyerCaptor;

    @Before
    public void setUp() {
        ObjectMapper mapper = new ObjectMapper();
        userRegistrar = new UserRegistrar(userFinder, mapper, userRepository, userBuyerRepository, userSellerRepository);
    }

    @Test
    public void whenAUserRegistrarRegistersAUser_thenSaveInTheRepository() {
        AddressTo addressTo = new AddressTo("street", 123L, -123L);
        RegisterUserTO registerUserTO = new RegisterUserTO("carlos", "gonzalez", "carlos@gmail.com", "password", addressTo);

        userRegistrar.registerNewUser(registerUserTO);

        verify(userFinder, times(1)).existsUser(userCaptor.capture());
        verify(userRepository, times(1)).addUser(userCaptor.capture());
        verify(userBuyerRepository, times(1)).save(userBuyerCaptor.capture());
        assertThat(userCaptor.getValue().getEmail(), is(registerUserTO.getEmail()));
        assertThat(userBuyerCaptor.getValue().getUser(), is(userCaptor.getValue()));
    }

    @Test
    public void whenAUserRegistrarRegistersAnExistingUser_thenDoNotAddIt() {
        when(userFinder.existsUser(ArgumentMatchers.any())).thenReturn(true);
        AddressTo addressTo = new AddressTo("street", 123L, -123L);
        RegisterUserTO newRegisterUserTO = new RegisterUserTO("carlos", "gonzalez", "carlos@gmail.com", "password", addressTo);

        String errorMessage = "No se puede registrar debido a que existe en el sistema un usuario con el email: [" + newRegisterUserTO.getEmail() + "].";
        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> userRegistrar.registerNewUser(newRegisterUserTO))
            .withMessage(errorMessage);
        verify(userRepository, never()).addUser(ArgumentMatchers.any());
    }

    @After
    public void clearRegister() {
        userRegistrar = null;
    }
}
