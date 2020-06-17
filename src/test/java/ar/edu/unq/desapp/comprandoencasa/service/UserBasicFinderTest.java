package ar.edu.unq.desapp.comprandoencasa.service;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserBasic;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserBuyer;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserSeller;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserBuyerRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserSellerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserBasicFinderTest {

    private UserFinder userFinder;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserSellerRepository userSellerRepository;

    @Mock
    private UserBuyerRepository userBuyerRepository;

    @Before
    public void setUp() {
        userFinder = new UserFinder(userRepository, userSellerRepository, userBuyerRepository);
    }

    @Test
    public void whenFindAnExistingSellerUserInRepositoryById_ThenReturnsTheExistentUser() {
        UserBasic savedUserBasic = UserBasic.create("carlos", "gonzalez", "carlos@gmail.com", "password", null);
        Commerce commerce = new Commerce("un nombre de comercio", null, null, null, null, null);
        UserSeller userSeller = new UserSeller(savedUserBasic, commerce);
        when(userRepository.findById(anyString())).thenReturn(Optional.of(savedUserBasic));
        when(userSellerRepository.findByUser(any())).thenReturn(Optional.of(userSeller));

        UserSeller foundUser = userFinder.findSellerByUserId(savedUserBasic.getUid());

        assertThat(foundUser.getUserBasic(), is(savedUserBasic));
    }

    @Test
    public void whenFindANonExistingUserInRepositoryById_ThenFailsWithException() {
        String userUid = randomUUID().toString();
        when(userRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> userFinder.findSellerByUserId(userUid))
            .withMessage("No existe el usuario con id: [" + userUid + "]");
    }

    @Test
    public void whenFindAExistingUserInRepositoryByIdButIsNotRegisterdAsSeller_ThenFailsWithException() {
        UserBasic savedUserBasic = UserBasic.create("carlos", "gonzalez", "carlos@gmail.com", "password", null);
        when(userRepository.findById(anyString())).thenReturn(Optional.of(savedUserBasic));
        when(userSellerRepository.findByUser(savedUserBasic)).thenReturn(Optional.empty());

        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> userFinder.findSellerByUserId(savedUserBasic.getUid()))
            .withMessage("Usuario no registrado como vendedor. ID [" + savedUserBasic.getUid() + "]");
    }

    @Test
    public void whenWantKnowIfAnUserSellerIsSeller_thenReturnsTrue() {
        UserBasic savedUserBasic = UserBasic.create("carlos", "gonzalez", "carlos@gmail.com", "password", null);
        Commerce commerce = new Commerce("un nombre de comercio", null, null, null, null, null);
        UserSeller userSeller = new UserSeller(savedUserBasic, commerce);
        when(userSellerRepository.findByUser(any())).thenReturn(Optional.of(userSeller));

        boolean isSeller = userFinder.isSeller(savedUserBasic);

        assertThat(isSeller, is(true));
    }

    @Test
    public void whenWantKnowIfAnUserBuyerIsSeller_thenReturnsFalse() {
        UserBasic savedUserBasic = UserBasic.create("carlos", "gonzalez", "carlos@gmail.com", "password", null);
        when(userSellerRepository.findByUser(any())).thenReturn(Optional.empty());

        boolean isSeller = userFinder.isSeller(savedUserBasic);

        assertThat(isSeller, is(false));
    }

    @Test
    public void whenWantKnowIfAnUserBuyerIsBuyer_thenReturnsTrue() {
        UserBasic savedUserBasic = UserBasic.create("carlos", "gonzalez", "carlos@gmail.com", "password", null);
        UserBuyer userBuyer = new UserBuyer(savedUserBasic);
        when(userBuyerRepository.findByUser(any())).thenReturn(Optional.of(userBuyer));

        boolean isBuyer = userFinder.isBuyer(savedUserBasic);

        assertThat(isBuyer, is(true));
    }

    @Test
    public void whenWantKnowIfAnUserSellerIsBuyer_thenReturnsFalse() {
        UserBasic savedUserBasic = UserBasic.create("carlos", "gonzalez", "carlos@gmail.com", "password", null);
        when(userBuyerRepository.findByUser(any())).thenReturn(Optional.empty());

        boolean isBuyer = userFinder.isBuyer(savedUserBasic);

        assertThat(isBuyer, is(false));
    }

    @Test
    public void whenWantFindByExistentEmail_thenReturnsTheExistentUser() {
        UserBasic savedUserBasic = UserBasic.create("carlos", "gonzalez", "carlos@gmail.com", "password", null);
        when(userRepository.findByEmail(savedUserBasic.getEmail())).thenReturn(Optional.of(savedUserBasic));

        UserBasic userBasicFounded = userFinder.findByEmail(savedUserBasic.getEmail());

        assertThat(userBasicFounded, is(savedUserBasic));
    }

    @Test
    public void whenWantFindByNonExistentEmail_thenThrowsAnException() {
        String emailToFind = "nonExistent@email.com";
        when(userRepository.findByEmail(emailToFind)).thenReturn(Optional.empty());

        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> userFinder.findByEmail(emailToFind))
            .withMessage("Usuario o contraseña incorrectos.");
    }

    @Test
    public void whenFindAnExistingSellerUserInRepositoryByUser_ThenReturnsTheExistentUser() {
        UserBasic savedUserBasic = UserBasic.create("carlos", "gonzalez", "carlos@gmail.com", "password", null);
        Commerce commerce = new Commerce("un nombre de comercio", null, null, null, null, null);
        UserSeller userSeller = new UserSeller(savedUserBasic, commerce);
        when(userSellerRepository.findByUser(any())).thenReturn(Optional.of(userSeller));

        UserSeller foundUser = userFinder.findSellerByUser(savedUserBasic);

        assertThat(foundUser.getUserBasic(), is(savedUserBasic));
    }

    @Test
    public void whenFindAExistingUserInRepositoryByUserButIsNotRegisterdAsSeller_ThenFailsWithException() {
        UserBasic savedUserBasic = UserBasic.create("carlos", "gonzalez", "carlos@gmail.com", "password", null);
        when(userSellerRepository.findByUser(savedUserBasic)).thenReturn(Optional.empty());

        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> userFinder.findSellerByUser(savedUserBasic))
            .withMessage("Usuario no registrado como vendedor. ID [" + savedUserBasic.getUid() + "]");
    }
}
