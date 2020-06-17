package ar.edu.unq.desapp.comprandoencasa.controller.to.mapper;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.AddressTO;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.RegisterUserTO;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.ObjectConverter;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.meta.SpringIntegrationTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RegisterUserTO2UserConverterTest extends SpringIntegrationTest {

    @Autowired
    private ObjectConverter objectConverter;

    private RegisterUserTO registerUserTO;
    private AddressTO addressTO;

    @Before
    public void setUp() {
        long lat = 10L;
        long lng = 20L;
        addressTO = new AddressTO();
        addressTO.setStreet("street");
        addressTO.setLatitud(Double.longBitsToDouble(lat));
        addressTO.setLongitud(Double.longBitsToDouble(lng));
        registerUserTO = new RegisterUserTO("nombre", "apellido", "mail@mail.com", "password", addressTO);
    }

    @Test
    public void convertingFromRegisterUserTO2UserMapsAllFields() {
        User user = objectConverter.convertTo(User.class, registerUserTO);

        assertThat(user.getName(), is(registerUserTO.getName()));
        assertThat(user.getSurname(), is(registerUserTO.getSurname()));
        assertThat(user.getEmail(), is(registerUserTO.getEmail()));
        assertThat(user.getPassword(), is(registerUserTO.getPassword()));

        assertThat(user.getAddress().getStreet(), is(addressTO.getStreet()));
        assertThat(user.getAddress().getLatitud(), is(addressTO.getLatitud()));
        assertThat(user.getAddress().getLongitud(), is(addressTO.getLongitud()));
    }
}
