package ar.edu.unq.desapp.comprandoencasa.model;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.configurations.GoogleConnector;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Address;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Efectivo;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.PaymentMethod;
import com.google.maps.model.LatLng;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DistanceCalculatorTest {

    private DistanceCalculator distanceCalculator;

    @Mock
    private GoogleConnector googleConnector;

    @Before
    public void setUp() {
        distanceCalculator = new DistanceCalculator(googleConnector);
    }

    @Test
    public void whenCalculatesNearCommercesInGivenRangeAndPositionAndExistsCommerces_thenReturnsTheLocalsInsideRange() {
        LatLng aCommerceLatLng = new LatLng(-34.7066345, -58.2819718);
        LatLng otherCommerceLatLng = new LatLng(-34.7166345, -58.2822718);
        List<Commerce> commerceList = createCommerces(aCommerceLatLng, otherCommerceLatLng);

        Commerce kioscoCarlos = commerceList.stream().filter(commerce -> commerce.getLatLong() == aCommerceLatLng).findFirst().get();
        LatLng latLngFrom = new LatLng(-34.7040003, -58.2754042);

        long outsideRange = 5000L;
        simulateGoogleRespondsForAnyCommerceWithGivenRange(outsideRange);
        simulateGoogleRespondsCommerceWithGivenRange(latLngFrom, kioscoCarlos.getLatLong(), 200L);

        Long range = Long.valueOf("2000");


        List<Commerce> commercesInRange = distanceCalculator.getByLatLngInRange(latLngFrom, range, commerceList);


        assertThat(commercesInRange, containsInAnyOrder(kioscoCarlos));
    }

    @Test
    public void whenCalculatesNearCommercesByInGivenRangeAndPositionButNotExistsCommerces_thenReturnsEmptyList() {
        LatLng aCommerceLatLng = new LatLng(-34.7066345, -58.2819718);
        LatLng otherCommerceLatLng = new LatLng(-34.7166345, -58.2822718);
        List<Commerce> commerceList = createCommerces(aCommerceLatLng, otherCommerceLatLng);

        commerceList.stream().filter(commerce -> commerce.getLatLong() == aCommerceLatLng).findFirst().get();

        long outsideRange = 5000L;
        simulateGoogleRespondsForAnyCommerceWithGivenRange(outsideRange);
        LatLng latLngFrom = new LatLng(-34.7040003, -58.2754042);

        Long range = Long.valueOf("2000");


        List<Commerce> commercesInRange = distanceCalculator.getByLatLngInRange(latLngFrom, range, commerceList);


        assertThat(commercesInRange, empty());
    }

    @Test
    public void whenCalculatesNearCommercesByInGivenRangeAndPositionButFailsGoogleConnector_thenReturnsEmptyList() {
        LatLng aCommerceLatLng = new LatLng(-34.7066345, -58.2819718);
        LatLng otherCommerceLatLng = new LatLng(-34.7166345, -58.2822718);
        List<Commerce> commerceList = createCommerces(aCommerceLatLng, otherCommerceLatLng);

        commerceList.stream().filter(commerce -> commerce.getLatLong() == aCommerceLatLng).findFirst().get();
        when(googleConnector.distanceInMetersBetweenTwoLatLng(any(), any())).thenReturn(Optional.empty());
        LatLng latLngFrom = new LatLng(-34.7040003, -58.2754042);

        Long range = Long.valueOf("2000");


        List<Commerce> commercesInRange = distanceCalculator.getByLatLngInRange(latLngFrom, range, commerceList);


        assertThat(commercesInRange, empty());
    }

    @Test
    public void whenCalculatesDistanceBetweenTwoLatLngValids_thenReturnsTheValueWrappedInOptional() {
        LatLng toLatLng = new LatLng(-34.7066345, -58.2819718);
        long distanceMeters = 12L;
        distanceCalculator = new DistanceCalculator(googleConnector);
        when(googleConnector.distanceInMetersBetweenTwoLatLng(any(), any())).thenReturn(Optional.of(distanceMeters));

        LatLng latLngFrom = new LatLng(-34.7040003, -58.2754042);

        Optional<Long> distance = distanceCalculator.distanceInMetersBetweenTwoLatLng(latLngFrom, toLatLng);

        assertThat(distance.get(), is(distanceMeters));
    }

    @Test
    public void whenCalculatesDistanceBetweenTwoLatLngWithOneNoValid_thenReturnsEmptyValue() {
        LatLng toLatLng = new LatLng(-34.7066345, -58.2819718);
        LatLng latLngFrom = new LatLng(-34.7040003, -58.2754042);
        distanceCalculator = new DistanceCalculator(googleConnector);
        when(googleConnector.distanceInMetersBetweenTwoLatLng(any(), any())).thenReturn(Optional.empty());

        Optional<Long> distance = distanceCalculator.distanceInMetersBetweenTwoLatLng(latLngFrom, toLatLng);

        assertThat(distance.isAbsent(), is(true));
    }

    private void simulateGoogleRespondsCommerceWithGivenRange(LatLng latLngFrom, LatLng commerceLatLong, long distanceInMeters) {
        Optional<Long> givenRange = Optional.of(distanceInMeters);
        when(googleConnector.distanceInMetersBetweenTwoLatLng(latLngFrom, commerceLatLong)).thenReturn(givenRange);
    }

    private void simulateGoogleRespondsForAnyCommerceWithGivenRange(long distanceInMeters) {
        Optional<Long> givenRange = Optional.of(distanceInMeters);
        when(googleConnector.distanceInMetersBetweenTwoLatLng(any(), any())).thenReturn(givenRange);
    }

    public List<Commerce> createCommerces(LatLng aCommerceLatLng, LatLng otherCommerceLatLng) {
        Efectivo efectivo = new Efectivo("pesos");
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        paymentMethods.add(efectivo);
        List<String> horarios = new ArrayList<>();
        horarios.add("Lunes a viernes de 10 a 18hs");
        Address aCommerceAddress = Address.create("Roque Sáenz Peña 284, Bernal, Buenos Aires", aCommerceLatLng);
        Address otherCommerceAddress = Address.create("Roque Sáenz Peña 106, Bernal, Buenos Aires", otherCommerceLatLng);
        Commerce aCommerce = new Commerce("Kiosco carlos", "Kiosco", aCommerceAddress, paymentMethods, horarios, "3km");
        Commerce otherCommerce = new Commerce("Almacen pepe", "Almacen", otherCommerceAddress, paymentMethods, horarios, "5km");

        List<Commerce> repo = new ArrayList<>();
        repo.add(aCommerce);
        repo.add(otherCommerce);
        return repo;
    }
}
