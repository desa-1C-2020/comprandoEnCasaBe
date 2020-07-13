package ar.edu.unq.desapp.comprandoencasa.service;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Address;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.DayOfWeekWithTimeRange;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Efectivo;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.TimeRange;
import ar.edu.unq.desapp.meta.SpringIntegrationTest;
import com.google.maps.model.LatLng;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

public class PurchaseServiceTest extends SpringIntegrationTest {

    private PurchaseService purchaseService;

    @Mock
    private UserFinder userFinder;

    @Mock
    private CommerceFinder commerceFinder;

    @Before
    public void setUp() {
        purchaseService = new PurchaseService(userFinder, commerceFinder);
    }

    @Test
    public void whenGetTakeAwayOptionsForWrongDay_thnReturnTheNextAvaileableDay() {
        Commerce commerce = commerce();
        when(commerceFinder.findById(1L)).thenReturn(Optional.of(commerce));

        LocalDateTime takeAwayOptionFor = purchaseService.getTakeAwayOptionFor(Collections.singletonList(1L), "20200713:130000");

        assertThat(takeAwayOptionFor.toString(), is("2020-07-20T08:00:01"));
    }

    public Commerce commerce() {
        Efectivo efectivo = new Efectivo("pesos");
        List<Efectivo> paymentMethods = new ArrayList<>();
        paymentMethods.add(efectivo);
        LatLng aCommerceLatLng = new LatLng(-34.7066345, -58.2819718);
        Address aCommerceAddress = Address.create("Roque Sáenz Peña 284, Bernal, Buenos Aires", aCommerceLatLng);
        DayOfWeekWithTimeRange horarios = new DayOfWeekWithTimeRange(DayOfWeek.MONDAY,
            Collections.singletonList(new TimeRange(8, 12)));
        return new Commerce("Kiosco carlos", "Kiosco", aCommerceAddress, paymentMethods,
            Collections.singletonList(horarios), "3km");
    }
}
