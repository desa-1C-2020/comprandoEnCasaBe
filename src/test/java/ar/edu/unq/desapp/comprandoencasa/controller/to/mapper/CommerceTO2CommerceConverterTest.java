package ar.edu.unq.desapp.comprandoencasa.controller.to.mapper;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.AddressTO;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.CommerceTO;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.DayOfWeekWithTimeRangeTO;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.ObjectConverter;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.PaymentMethod;
import ar.edu.unq.desapp.meta.SpringIntegrationTest;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CommerceTO2CommerceConverterTest extends SpringIntegrationTest {

    @Autowired
    private ObjectConverter objectConverter;

    private CommerceTO commerceTO;
    private AddressTO addressTO;

    @Before
    public void setUp() {
        DayOfWeekWithTimeRangeTO dayOfWeekWithTimeRangeTO = new DayOfWeekWithTimeRangeTO();
        int saturday = 6;
        dayOfWeekWithTimeRangeTO.setDayOfWeek(saturday);
        dayOfWeekWithTimeRangeTO.setTimeRanges(Collections.singletonList(Pair.of(8, 12)));
        long lat = 10L;
        long lng = 20L;
        addressTO = new AddressTO();
        addressTO.setStreet("street");
        addressTO.setLatitud(Double.longBitsToDouble(lat));
        addressTO.setLongitud(Double.longBitsToDouble(lng));
        commerceTO = new CommerceTO();
        commerceTO.setArrivalRange("20km");
        commerceTO.setAddress(addressTO);
        commerceTO.setBusinessSector("sector");
        commerceTO.setName("a commerce");
        commerceTO.setDaysAndHoursOpen(Collections.singletonList(dayOfWeekWithTimeRangeTO));
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        paymentMethods.add(PaymentMethod.DEBIT);
        commerceTO.setPaymentMethods(paymentMethods);
    }

    @Test
    public void converting_fromSellerTO_to_Commerce_mapsAllFields() {
        Commerce commerce = objectConverter.convertTo(Commerce.class, commerceTO);

        assertThat(commerce.getName(), is(commerceTO.getName()));
        assertThat(commerce.getAddress().getStreet(), is(commerceTO.getAddress().getStreet()));
        assertThat(commerce.getBusinessSector(), is(commerceTO.getBusinessSector()));
        assertThat(commerce.getAddress().getLatitud(), is(addressTO.getLatitud()));
        assertThat(commerce.getDaysAndHoursOpen().get(0).getDayOfWeek(), is("Saturday"));
        assertThat(commerce.getPaymentMethods().get(0), is(PaymentMethod.DEBIT));
    }
}
