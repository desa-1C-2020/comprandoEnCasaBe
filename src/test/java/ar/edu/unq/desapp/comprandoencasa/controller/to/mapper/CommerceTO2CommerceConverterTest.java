package ar.edu.unq.desapp.comprandoencasa.controller.to.mapper;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.AddressTO;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.CommerceTO;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.PaymentMethodTO;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.ObjectConverter;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.meta.SpringIntegrationTest;
import com.google.maps.model.LatLng;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CommerceTO2CommerceConverterTest extends SpringIntegrationTest {

    @Autowired
    private ObjectConverter objectConverter;

    private CommerceTO commerceTO;
    private LatLng latLng;
    private AddressTO addressTO;

    @Before
    public void setUp() {

        long lat = 10L;
        long lng = 20L;
        latLng = new LatLng(lat, lng);
        addressTO = new AddressTO();
        addressTO.setStreet("street");
        addressTO.setLatitud(Double.longBitsToDouble(lat));
        addressTO.setLongitud(Double.longBitsToDouble(lng));
        commerceTO = new CommerceTO();
        commerceTO.setArrivalRange("20km");
        commerceTO.setAddress(addressTO);
        commerceTO.setBusinessSector("sector");
        commerceTO.setName("a commerce");
        List<String> daysAndHours = new ArrayList<>();
        daysAndHours.add("an hour");
        commerceTO.setDaysAndHoursOpen(daysAndHours);
        List<PaymentMethodTO> paymentMethods = new ArrayList<>();
        PaymentMethodTO paymentMethodTO = new PaymentMethodTO();
        paymentMethodTO.setAccept("Efectivo");
        paymentMethodTO.setType("Efectivo");
        paymentMethods.add(paymentMethodTO);
        commerceTO.setPaymentMethods(paymentMethods);
    }

    @Test
    public void converting_fromSellerTO_to_Commerce_mapsAllFields() {
        Commerce commerce = objectConverter.convertTo(Commerce.class, commerceTO);

        assertThat(commerce.getName(), is(commerceTO.getName()));
        assertThat(commerce.getDaysAndHoursOpen(), is(commerceTO.getDaysAndHoursOpen()));
        assertThat(commerce.getAddress().getStreet(), is(commerceTO.getAddress().getStreet()));
        assertThat(commerce.getBusinessSector(), is(commerceTO.getBusinessSector()));
        assertThat(commerce.getAddress().getLatitud(), is(addressTO.getLatitud()));
    }
}
