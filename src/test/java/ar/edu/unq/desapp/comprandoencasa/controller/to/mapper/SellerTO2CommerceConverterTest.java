package ar.edu.unq.desapp.comprandoencasa.controller.to.mapper;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.AddressTO;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.SellerTO;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.ObjectConverter;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Efectivo;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.PaymentMethod;
import ar.edu.unq.desapp.meta.SpringIntegrationTest;
import com.google.maps.model.LatLng;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SellerTO2CommerceConverterTest extends SpringIntegrationTest {

    @Autowired
    private ObjectConverter objectConverter;

    private SellerTO sellerTO;
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
        sellerTO = new SellerTO();
        sellerTO.setArrivalRange("20km");
        sellerTO.setCommerceAddress(addressTO);
        sellerTO.setCommerceBusinessSector("sector");
        sellerTO.setCommerceName("a commerce");
        List<String> daysAndHours = new ArrayList<>();
        daysAndHours.add("an hour");
        sellerTO.setDaysAndHoursOpen(daysAndHours);
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        paymentMethods.add(new Efectivo("pesos"));
        sellerTO.setPaymentMethods(paymentMethods);
        sellerTO.setUserId("userId");
    }

    @Test
    public void converting_fromSellerTO_to_Commerce_mapsAllFields() {
        Commerce commerce = objectConverter.convertTo(Commerce.class, sellerTO);

        assertThat(commerce.getName(), is(sellerTO.getCommerceName()));
        assertThat(commerce.getDaysAndHoursOpen(), is(sellerTO.getDaysAndHoursOpen()));
        assertThat(commerce.getAddress().getStreet(), is(sellerTO.getCommerceAddress().getStreet()));
        assertThat(commerce.getBusinessSector(), is(sellerTO.getBusinessSector()));
        assertThat(commerce.getAddress().getLatitud(), is(addressTO.getLatitud()));
    }
}
