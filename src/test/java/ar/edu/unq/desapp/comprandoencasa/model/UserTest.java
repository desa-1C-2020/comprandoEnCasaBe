package ar.edu.unq.desapp.comprandoencasa.model;

import ar.edu.unq.desapp.comprandoencasa.extensions.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {

    @Test
    public void whenWantCreateUserWithWellformedMail_thenTheUserIsCreated() {
        User user = new User("carlos", "gonzalez", "carlos@gmail.com");

        assertThat(user.name(), is("carlos"));
        assertThat(user.surname(), is("gonzalez"));
        assertThat(user.email(), is("carlos@gmail.com"));
    }

    @Test
    public void whenWantCreateUserWithoutEmail_thenTheUserIsNotCreated() {
        assertInvalidEmail(null);
    }

    @Test
    public void whenWantCreateUserWithMalformedEmail_thenTheUserIsNotCreated() {
        assertInvalidEmail("carlosgmail.com");
    }

    private void assertInvalidEmail(String email) {
        Exception thrown = TestUtils.assertThrows(() -> new User("carlos", "gonzalez", email), RuntimeException.class);

        assertThat(thrown.getMessage(), is("El email '" + email + "' no es válido."));
    }
}
