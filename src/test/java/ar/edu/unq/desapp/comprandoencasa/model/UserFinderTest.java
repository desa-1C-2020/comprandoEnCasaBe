package ar.edu.unq.desapp.comprandoencasa.model;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserSeller;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserSellerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserRol.SELLER;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserFinderTest {

    private UserFinder userFinder;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserSellerRepository userSellerRepository;

    @Before
    public void setUp() {
        userFinder = new UserFinder(userRepository, userSellerRepository);
    }

    @Test
    public void whenFindAnExistingSellerUserInRepositoryById_ThenReturnsTheExistentUser() {
        User savedUser = User.create("carlos", "gonzalez", "carlos@gmail.com");
        Commerce commerce = new Commerce("un nombre de comercio", null, null, null, null, null);
        UserSeller userSeller = new UserSeller(savedUser, SELLER, commerce);
        when(userRepository.findBy(anyString())).thenReturn(Optional.of(savedUser));
        when(userSellerRepository.findByUser(any())).thenReturn(Optional.of(userSeller));

        UserSeller foundUser = userFinder.findSellerById(savedUser.getUid());

        assertThat(foundUser.getUser(), is(savedUser));
    }

    @Test
    public void whenFindANonExistingUserInRepositoryById_ThenFailsWithException() {
        String userUid = randomUUID().toString();
        when(userRepository.findBy(anyString())).thenReturn(Optional.empty());

        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> userFinder.findSellerById(userUid))
            .withMessage("No existe el usuario con id: [" + userUid + "]");
    }

    @Test
    public void whenFindAExistingUserInRepositoryByIdButIsNotRegisterdAsSeller_ThenFailsWithException() {
        User savedUser = User.create("carlos", "gonzalez", "carlos@gmail.com");
        when(userRepository.findBy(anyString())).thenReturn(Optional.of(savedUser));
        when(userSellerRepository.findByUser(savedUser)).thenReturn(Optional.empty());

        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> userFinder.findSellerById(savedUser.getUid()))
            .withMessage("Usuario no registrado como vendedor. ID [" + savedUser.getUid() + "]");
    }
}
