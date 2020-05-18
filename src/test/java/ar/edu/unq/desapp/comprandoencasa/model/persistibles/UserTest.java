package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import ar.edu.unq.desapp.comprandoencasa.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {

    @Test
    public void whenWantCreateUserWithWellformedMail_thenTheUserIsCreated() {
        User user = User.create("carlos", "gonzalez", "carlos@gmail.com");

        assertThat(user.getName(), is("carlos"));
        assertThat(user.getSurname(), is("gonzalez"));
        assertThat(user.getEmail(), is("carlos@gmail.com"));
    }

    @Test
    public void whenWantCreateUserWithoutEmail_thenTheUserIsNotCreated() {
        assertInvalidEmail(null);
    }

    @Test
    public void whenWantCreateUserWithMalformedEmail_thenTheUserIsNotCreated() {
        assertInvalidEmail("carlosgmail.com");
    }

    @Test
    public void whenCheckIfSameIdThatSameUser_thenReturnTrue() {
        User user = User.create("carlos", "gonzalez", "carlos@gmail.com");

        assertThat(user.sameId(user.getUid()), is(true));
    }

    @Test
    public void whenCheckIfSameIdForDifferentUser_thenReturnFalse() {
        User user = User.create("carlos", "gonzalez", "carlos@gmail.com");
        User otherUser = User.create("martin", "gonzalez", "martin@gmail.com");

        assertThat(user.sameId(otherUser.getUid()), is(false));
    }

    private void assertInvalidEmail(String email) {
        Exception thrown = TestUtils.assertThrows(() -> User.create("carlos", "gonzalez", email), RuntimeException.class);

        assertThat(thrown.getMessage(), is("El email: [" + email + "] no es válido."));
    }
}
