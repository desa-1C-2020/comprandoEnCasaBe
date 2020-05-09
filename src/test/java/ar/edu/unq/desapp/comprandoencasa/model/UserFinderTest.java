package ar.edu.unq.desapp.comprandoencasa.model;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserRol.BUYER;
import static ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserRol.SELLER;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserFinderTest {

    private UserFinder userFinder;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userFinder = new UserFinder(userRepository);
    }

    @Test
    public void whenFindAnExistingSellerUserInRepositoryById_ThenReturnsTheExistentUser() {
        User savedUser = User.createWithoutCommerce("carlos", "gonzalez", "carlos@gmail.com", SELLER);
        when(userRepository.findBy(anyString())).thenReturn(Optional.of(savedUser));

        User findUser = userFinder.findSeller(savedUser.getUid());

        assertThat(findUser, is(savedUser));
    }

    @Test
    public void whenFindANonExistingUserInRepositoryById_ThenFailsWithException() {
        String userUid = randomUUID().toString();
        when(userRepository.findBy(anyString())).thenReturn(Optional.empty());

        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> userFinder.findSeller(userUid))
            .withMessage("No existe el usuario con id: [" + userUid + "]");
    }

    @Test
    public void whenFindAnExistingUserButIsNotSellerInRepositoryById_ThenFailsWithException() {
        User savedBuyerUser = User.createWithoutCommerce("carlos", "gonzalez", "carlos@gmail.com", BUYER);
        when(userRepository.findBy(anyString())).thenReturn(Optional.of(savedBuyerUser));

        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> userFinder.findSeller(savedBuyerUser.getUid()))
            .withMessage("Usuario usuario con id: [" + savedBuyerUser.getUid() + "], no habilitado para vender.");
    }
}
