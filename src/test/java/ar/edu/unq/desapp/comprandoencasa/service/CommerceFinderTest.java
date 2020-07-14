package ar.edu.unq.desapp.comprandoencasa.service;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.configurations.GoogleConnector;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Address;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.DayOfWeekWithTimeRange;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.PaymentMethod;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.TimeRange;
import ar.edu.unq.desapp.comprandoencasa.repositories.CommerceRepository;
import com.google.maps.model.LatLng;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CommerceFinderTest {

    private CommerceFinder commerceFinder;

    @Mock
    private CommerceRepository commerceRepository;

    @Mock
    private GoogleConnector googleConnector;

    @Before
    public void setUp() {
        commerceFinder = new CommerceFinder(commerceRepository, googleConnector);
    }

    @Test
    public void whenFindAllCommercesInRangeAndNoOneIsAround_thenReturnAEmptyList() {
        LatLng latLngFrom = new LatLng(123, 123);
        String maxDistance = "10";
        List<Commerce> commercesSaved = commercesSaved();
        when(commerceRepository.getAll()).thenReturn(commercesSaved);
        when(googleConnector.distanceInMetersBetweenTwoLatLng(any(), any())).thenReturn(Optional.empty());

        List<Commerce> commercesInRange = commerceFinder.findAllInsideRange(maxDistance, latLngFrom);

        assertThat(commercesInRange, empty());
    }

    @Test
    public void whenFindAllCommercesInRangeAndThereAreOneInRange_thenReturnAAListWithTheCommerceInRange() {
        LatLng latLngFrom = new LatLng(123, 123);
        String maxDistanceMeters = "1000";
        List<Commerce> commercesSaved = commercesSaved();
        when(commerceRepository.getAll()).thenReturn(commercesSaved);
        when(googleConnector.distanceInMetersBetweenTwoLatLng(any(), any())).thenReturn(Optional.of(Long.valueOf(maxDistanceMeters)));

        List<Commerce> commercesInRange = commerceFinder.findAllInsideRange(maxDistanceMeters, latLngFrom);

        assertThat(commercesInRange, is(commercesSaved));
    }

    public List<Commerce> commercesSaved() {
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        paymentMethods.add(PaymentMethod.CASH);
        LatLng aCommerceLatLng = new LatLng(-34.7066345, -58.2819718);
        Address aCommerceAddress = Address.create("Roque Sáenz Peña 284, Bernal, Buenos Aires", aCommerceLatLng);
        DayOfWeekWithTimeRange horarios = new DayOfWeekWithTimeRange(DayOfWeek.MONDAY,
            Collections.singletonList(new TimeRange(8, 12)));
        Commerce aCommerce = new Commerce("Kiosco carlos", "Kiosco", aCommerceAddress, paymentMethods,
            Collections.singletonList(horarios), "3km");
        List<Commerce> repo = new ArrayList<>();
        repo.add(aCommerce);
        return repo;
    }
}
