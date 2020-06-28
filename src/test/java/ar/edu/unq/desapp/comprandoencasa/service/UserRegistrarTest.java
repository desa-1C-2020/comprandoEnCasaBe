package ar.edu.unq.desapp.comprandoencasa.service;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.AddressTO;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.RegisterUserTO;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.ObjectConverter;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserBuyer;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserBuyerRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserSellerRepository;
import ar.edu.unq.desapp.meta.SpringIntegrationTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserRegistrarTest extends SpringIntegrationTest {

    private UserRegistrar userRegistrar;

    @Mock
    private UserFinder userFinder;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserSellerRepository userSellerRepository;

    @Mock
    private UserBuyerRepository userBuyerRepository;

    @Autowired
    private ObjectConverter converter;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @Captor
    private ArgumentCaptor<UserBuyer> userBuyerCaptor;

    @Before
    public void setUp() {
        userRegistrar = new UserRegistrar(userFinder, userRepository, userBuyerRepository,
            userSellerRepository, converter);
    }

    @Test
    public void whenAUserRegistrarRegistersAUser_thenSaveInTheRepository() {
        AddressTO addressTo = new AddressTO();
        addressTo.setStreet("street");
        addressTo.setLatitud(Double.parseDouble("123"));
        addressTo.setLongitud(Double.parseDouble("-123"));
        RegisterUserTO registerUserTO = new RegisterUserTO("carlos", "gonzalez", "carlos@gmail.com", "password", addressTo);

        userRegistrar.registerBuyerUser(registerUserTO);

        verify(userFinder, times(1)).existsUser(userCaptor.capture());
        verify(userRepository, times(1)).addUser(userCaptor.capture());
        verify(userBuyerRepository, times(1)).save(userBuyerCaptor.capture());
        assertThat(userCaptor.getValue().getEmail(), is(registerUserTO.getEmail()));
        assertThat(userBuyerCaptor.getValue().getUser(), is(userCaptor.getValue()));
    }

    @Test
    public void whenAUserRegistrarRegistersAnExistingUser_thenDoNotAddIt() {
        when(userFinder.existsUser(ArgumentMatchers.any())).thenReturn(true);
        AddressTO addressTo = new AddressTO();
        addressTo.setStreet("street");
        addressTo.setLatitud(Double.parseDouble("123"));
        addressTo.setLongitud(Double.parseDouble("-123"));
        RegisterUserTO newRegisterUserTO = new RegisterUserTO("carlos", "gonzalez", "carlos@gmail.com", "password", addressTo);

        String errorMessage = "No se puede registrar debido a que existe en el sistema un usuario con el email: [" + newRegisterUserTO.getEmail() + "].";
        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> userRegistrar.registerBuyerUser(newRegisterUserTO))
            .withMessage(errorMessage);
        verify(userRepository, never()).addUser(ArgumentMatchers.any());
    }

    @After
    public void clearRegister() {
        userRegistrar = null;
    }
}
