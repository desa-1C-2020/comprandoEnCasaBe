package ar.edu.unq.desapp.comprandoencasa.service;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Address;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.DayOfWeekWithTimeRange;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.DeliveryRegister;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.PaymentMethod;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.TimeRange;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.repositories.PurchaseRepository;
import ar.edu.unq.desapp.meta.SpringIntegrationTest;
import com.google.maps.model.LatLng;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.time.DayOfWeek;
import java.time.LocalDate;
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
    private CommerceFinder commerceFinder;
    @Mock
    private UserFinder userFinder;
    @Mock
    private DeliveryService deliveryService;
    @Mock
    private ShoppingListCreator creator;
    @Mock
    private SaleRegisterService saleRegisterService;
    @Mock
    private PurchaseRepository purchaseRepository;

    @Before
    public void setUp() {
        purchaseService = new PurchaseService(commerceFinder, userFinder, deliveryService, creator,
            purchaseRepository, saleRegisterService);
    }

    @Test
    public void whenGetTakeAwayOptionsForWrongDay_thnReturnTheNextAvaileableDay() {
        Commerce commerce = commerce();
        when(commerceFinder.findById(1L)).thenReturn(Optional.of(commerce));

        //Este test va a romper por el now(), cambiar estos strings en funcion del now().
        LocalDateTime takeAwayOptionFor = purchaseService.getTakeAwayOptionFor(Collections.singletonList(1L), "20200713:130000");

        assertThat(takeAwayOptionFor.toString(), is("2020-07-20T08:00:01"));
    }

    @Test
    public void whenGetDeliveryOptionsAndThereAreFullTomorrow_thenReturnTheNextAvaileableDay() {
        User user = User.create("pepe", "carlos", "algo@mail.com", null, null);
        DeliveryRegister deliveryRegister = new DeliveryRegister(user, true, false, LocalDate.of(2020, 7, 14));
        when(userFinder.findUserById(1L)).thenReturn(user);
        when(deliveryService.reserveFor(user)).thenReturn(deliveryRegister);

        LocalDateTime deliveryOption = purchaseService.getDeliveryOption(1L);

        assertThat(deliveryOption.toString(), is("2020-07-14T18:30"));
    }

    public Commerce commerce() {
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        paymentMethods.add(PaymentMethod.CASH);
        LatLng aCommerceLatLng = new LatLng(-34.7066345, -58.2819718);
        Address aCommerceAddress = Address.create("Roque Sáenz Peña 284, Bernal, Buenos Aires", aCommerceLatLng);
        DayOfWeekWithTimeRange horarios = new DayOfWeekWithTimeRange(DayOfWeek.MONDAY,
            Collections.singletonList(new TimeRange(8, 12)));
        return new Commerce("Kiosco carlos", "Kiosco", aCommerceAddress, paymentMethods,
            Collections.singletonList(horarios), "3km");
    }
}
