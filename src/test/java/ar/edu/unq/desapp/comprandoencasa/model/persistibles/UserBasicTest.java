package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import ar.edu.unq.desapp.comprandoencasa.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserBasicTest {

    @Test
    public void whenWantCreateUserWithWellformedMail_thenTheUserIsCreated() {
        UserBasic userBasic = userCarlos();

        assertThat(userBasic.getName(), is("carlos"));
        assertThat(userBasic.getSurname(), is("gonzalez"));
        assertThat(userBasic.getEmail(), is("carlos@gmail.com"));
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
        UserBasic userBasic = userCarlos();

        assertThat(userBasic.sameId(userBasic.getUid()), is(true));
    }

    @Test
    public void whenCheckIfSameIdForDifferentUser_thenReturnFalse() {
        UserBasic userBasic = userCarlos();
        UserBasic otherUserBasic = UserBasic.create("martin", "gonzalez", "martin@gmail.com", "password", null);

        assertThat(userBasic.sameId(otherUserBasic.getUid()), is(false));
    }

    private void assertInvalidEmail(String email) {
        Exception thrown = TestUtils.assertThrows(() -> UserBasic.create("carlos", "gonzalez", email, "password", null), RuntimeException.class);

        assertThat(thrown.getMessage(), is("El email: [" + email + "] no es válido."));
    }

    private UserBasic userCarlos() {
        return UserBasic.create("carlos", "gonzalez", "carlos@gmail.com", "password", null);
    }
}
