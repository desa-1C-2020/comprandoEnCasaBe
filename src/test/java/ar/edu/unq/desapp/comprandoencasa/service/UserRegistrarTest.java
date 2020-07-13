package ar.edu.unq.desapp.comprandoencasa.service;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.AddressTO;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.CommerceTO;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.DayOfWeekWithTimeRangeTO;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.PaymentMethodTO;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.ObjectConverter;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserBuyer;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserSeller;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserBuyerRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserSellerRepository;
import ar.edu.unq.desapp.meta.SpringIntegrationTest;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserRegistrarTest extends SpringIntegrationTest {

    private UserRegistrar userRegistrar;

    @Mock
    private UserFinder userFinder;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserSellerRepository userSellerRepository;

    @Mock
    private UserBuyerRepository userBuyerRepository;

    @Autowired
    private ObjectConverter converter;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @Captor
    private ArgumentCaptor<UserBuyer> userBuyerCaptor;

    @Before
    public void setUp() {
        userRegistrar = new UserRegistrar(userFinder, userRepository, userBuyerRepository,
            userSellerRepository, converter);
    }

    @Test
    public void whenAUserRegistrarRegistersAUserBuyer_thenSaveInTheRepository() {
        User existentUser = User.create("name", "surname", "existent@email.com", "password", null);
        when(userFinder.findUserById(1L)).thenReturn(existentUser);
        AddressTO addressTo = new AddressTO();
        addressTo.setStreet("street");
        addressTo.setLatitud(Double.parseDouble("123"));
        addressTo.setLongitud(Double.parseDouble("-123"));

        userRegistrar.updateBuyer(1L, addressTo);

        verify(userRepository, times(1)).addUser(userCaptor.capture());
        verify(userBuyerRepository, times(1)).save(userBuyerCaptor.capture());
        assertThat(userBuyerCaptor.getValue().getUser(), is(userCaptor.getValue()));
    }

    @Test
    public void whenAUserRegistrarRegistersAUserSeller_thenSaveInTheRepository() {
        User existentUser = User.create("name", "surname", "existent@email.com", "password", null);
        when(userFinder.findUserById(1L)).thenReturn(existentUser);
        CommerceTO commerceTO = getCommerce();

        UserSeller userSeller = userRegistrar.updateSeller(1L, commerceTO);

        assertThat(userSeller.getCommerce().getName(), is(commerceTO.getName()));
        assertThat(userSeller.getCommerce().getAddress().getStreet(), is(commerceTO.getAddress().getStreet()));
        assertThat(userSeller.getCommerce().getDaysAndHoursOpen().get(0).getDayOfWeek(), is("Sunday"));
        verify(userSellerRepository, times(1)).save(userSeller);
        verify(userBuyerRepository, never()).save(any());
    }

    private CommerceTO getCommerce() {
        DayOfWeekWithTimeRangeTO dayOfWeekWithTimeRangeTO = new DayOfWeekWithTimeRangeTO();
        int saturday = 7;
        dayOfWeekWithTimeRangeTO.setDayOfWeek(saturday);
        dayOfWeekWithTimeRangeTO.setTimeRanges(Collections.singletonList(Pair.of(8, 12)));
        long lat = 10L;
        long lng = 20L;
        AddressTO addressTO = new AddressTO();
        addressTO.setStreet("street");
        addressTO.setLatitud(Double.longBitsToDouble(lat));
        addressTO.setLongitud(Double.longBitsToDouble(lng));
        CommerceTO commerceTO = new CommerceTO();
        commerceTO.setArrivalRange("20km");
        commerceTO.setAddress(addressTO);
        commerceTO.setBusinessSector("sector");
        commerceTO.setName("a commerce");
        commerceTO.setDaysAndHoursOpen(Collections.singletonList(dayOfWeekWithTimeRangeTO));
        List<PaymentMethodTO> paymentMethods = new ArrayList<>();
        PaymentMethodTO paymentMethodTO = new PaymentMethodTO();
        paymentMethodTO.setAccept("Efectivo");
        paymentMethodTO.setType("Efectivo");
        paymentMethods.add(paymentMethodTO);
        commerceTO.setPaymentMethods(paymentMethods);
        return commerceTO;
    }

    @After
    public void clearRegister() {
        userRegistrar = null;
    }
}
