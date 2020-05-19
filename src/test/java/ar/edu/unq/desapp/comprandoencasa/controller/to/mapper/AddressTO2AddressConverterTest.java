package ar.edu.unq.desapp.comprandoencasa.controller.to.mapper;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.AddressTO;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.ObjectConverter;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Address;
import ar.edu.unq.desapp.meta.SpringIntegrationTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AddressTO2AddressConverterTest extends SpringIntegrationTest {

    @Autowired
    private ObjectConverter objectConverter;

    private AddressTO addressTO;

    @Before
    public void setUp() {
        long lat = 10L;
        long lng = 20L;

        addressTO = new AddressTO();
        addressTO.setStreet("street");
        addressTO.setLatitud(Double.longBitsToDouble(lat));
        addressTO.setLongitud(Double.longBitsToDouble(lng));
    }

    @Test
    public void convertingFromAddressTO2AddressMapsAllFields() {
        Address address = objectConverter.convertTo(Address.class, addressTO);

        assertThat(addressTO.getStreet(), is(address.getStreet()));
        assertThat(addressTO.getLatitud().doubleValue(), is(address.getLatLng().lat));
        assertThat(addressTO.getLongitud().doubleValue(), is(address.getLatLng().lng));
    }
}
