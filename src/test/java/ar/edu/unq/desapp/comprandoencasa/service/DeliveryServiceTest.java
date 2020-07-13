package ar.edu.unq.desapp.comprandoencasa.service;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.DeliveryRegister;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.repositories.DeliveryRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.memoria.DeliveryRepositoryMem;
import ar.edu.unq.desapp.meta.SpringIntegrationTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeliveryServiceTest extends SpringIntegrationTest {

    private DeliveryService deliveryService;

    @Autowired
    private DeliveryRepository deliveryRepository;
    private User user;
    private LocalDate today;

    @Before
    public void setUp() {
        user = User.create("pepe", "carlos", "algo@mail.com", null, null);
        today = LocalDate.now();
        DeliveryRegister deliveryToday = new DeliveryRegister(user, true, false, today);
        DeliveryRegister deliveryTomorrow = new DeliveryRegister(user, true, false, today.plusDays(1));
        DeliveryRegister deliveryPostTomorrow = new DeliveryRegister(user, true, false, today.plusDays(2));
        deliveryRepository.save(deliveryToday);
        deliveryRepository.save(deliveryTomorrow);
        deliveryRepository.save(deliveryPostTomorrow);
    }


    @Test
    public void whenThereAreDaysWithFullCapacity_thenReturn_theNextDayWithEmptyDeliverySlot() {
        deliveryService = new DeliveryService(deliveryRepository, 1);

        DeliveryRegister newRegister = deliveryService.reserveFor(user);

        LocalDate expectedDate = LocalDate.now();
        assertThat(newRegister.getUser(), is(user));
        assertThat(newRegister.getDeliverDate(), is(expectedDate.plusDays(3)));
    }

    @Test
    public void whenThereAreOneDaysWithAnEmptySlot_ThenReturnThatDay() {
        deliveryService = new DeliveryService(deliveryRepository, 2);

        DeliveryRegister newRegister = deliveryService.reserveFor(user);

        LocalDate expectedDate = LocalDate.now();
        assertThat(newRegister.getUser(), is(user));
        assertThat(newRegister.getDeliverDate(), is(expectedDate.plusDays(1)));
    }

    @After
    public void clearRegister() {
        ((DeliveryRepositoryMem) deliveryRepository).clear();
    }
}
