package ar.edu.unq.desapp.comprandoencasa.controller.to.mapper;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.AddressTO;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.ObjectConverter;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Address;
import ar.edu.unq.desapp.meta.SpringIntegrationTest;
import com.google.maps.model.LatLng;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class Address2AddressTOConverterTest extends SpringIntegrationTest {

    @Autowired
    private ObjectConverter objectConverter;

    private Address address;
    private LatLng latLng;

    @Before
    public void setUp() {
        long lat = 10L;
        long lng = 20L;
        latLng = new LatLng(lat, lng);
        address = Address.create("a street", latLng);
    }

    @Test
    public void convertingFromAddress2AddressTOMapsAllFields() {
        AddressTO addressTO = objectConverter.convertTo(AddressTO.class, address);

        assertThat(addressTO.getStreet(), is(address.getStreet()));
        assertThat(addressTO.getLatitud().doubleValue(), is(address.getLatLng().lat));
        assertThat(addressTO.getLongitud().doubleValue(), is(address.getLatLng().lng));
    }
}
