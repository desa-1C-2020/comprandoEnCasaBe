package ar.edu.unq.desapp.comprandoencasa.model;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.configurations.GoogleConnector;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Address;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Efectivo;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.PaymentMethod;
import com.google.maps.model.LatLng;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DistanceCalculatorTest {

    private DistanceCalculator distanceCalculator;

    @Mock
    private GoogleConnector googleConnector;

    @Test
    public void whenCalculatesNearCommercesInGivenRangeAndPositionAndExistsCommerces_thenReturnsTheLocalsInsideRange() {
        Commerce kioscoCarlos = createDistanceCalculatorWith(new LatLng(-34.7066345, -58.2819718));
        LatLng latLngFrom = new LatLng(-34.7040003, -58.2754042);

        long outsideRange = 5000L;
        simulateGoogleRespondsForAnyCommerceWithGivenRange(outsideRange);
        simulateGoogleRespondsCommerceWithGivenRange(latLngFrom, kioscoCarlos.getLatLong(), 200L);

        Long range = Long.valueOf("2000");
        List<Commerce> commercesInRange = distanceCalculator.getByLatLngInRange(latLngFrom, range);

        assertThat(commercesInRange, containsInAnyOrder(kioscoCarlos));
    }

    @Test
    public void whenCalculatesNearCommercesByInGivenRangeAndPositionButNotExistsCommerces_thenReturnsEmptyList() {
        createDistanceCalculatorWith(new LatLng(-34.7066345, -58.2819718));

        long outsideRange = 5000L;
        simulateGoogleRespondsForAnyCommerceWithGivenRange(outsideRange);
        LatLng latLngFrom = new LatLng(-34.7040003, -58.2754042);

        Long range = Long.valueOf("2000");
        List<Commerce> commercesInRange = distanceCalculator.getByLatLngInRange(latLngFrom, range);

        assertThat(commercesInRange, empty());
    }

    @Test
    public void whenCalculatesNearCommercesByInGivenRangeAndPositionButFailsGoogleConnector_thenReturnsEmptyList() {
        createDistanceCalculatorWith(new LatLng(-34.7066345, -58.2819718));
        when(googleConnector.distanceInMetersBetweenTwoLatLng(any(), any())).thenReturn(Optional.empty());
        LatLng latLngFrom = new LatLng(-34.7040003, -58.2754042);

        Long range = Long.valueOf("2000");
        List<Commerce> commercesInRange = distanceCalculator.getByLatLngInRange(latLngFrom, range);

        assertThat(commercesInRange, empty());
    }

    private Commerce createDistanceCalculatorWith(LatLng aCommerceLatLng) {
        LatLng otherCommerceLatLng = new LatLng(-34.7166345, -58.2822718);
        List<Commerce> commerceList = createCommerces(aCommerceLatLng, otherCommerceLatLng);
        distanceCalculator = new DistanceCalculator(commerceList, googleConnector);

        return commerceList.stream().filter(commerce -> commerce.getLatLong() == aCommerceLatLng).findFirst().get();
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
        Address aCommerceAddress = new Address("Roque Sáenz Peña 284, Bernal, Buenos Aires", aCommerceLatLng);
        Address otherCommerceAddress = new Address("Roque Sáenz Peña 106, Bernal, Buenos Aires", otherCommerceLatLng);
        Commerce aCommerce = new Commerce("Kiosco carlos", "Kiosco", aCommerceAddress, paymentMethods, horarios, "3km");
        Commerce otherCommerce = new Commerce("Almacen pepe", "Almacen", otherCommerceAddress, paymentMethods, horarios, "5km");

        List<Commerce> repo = new ArrayList<>();
        repo.add(aCommerce);
        repo.add(otherCommerce);
        return repo;
    }
}
