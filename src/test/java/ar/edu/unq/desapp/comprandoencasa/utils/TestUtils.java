package ar.edu.unq.desapp.comprandoencasa.utils;

import static org.junit.Assert.fail;

public class TestUtils {
    static public <T extends Exception> T assertThrows(Runnable runnable, Class<T> expectedExceptionClass) {
        try {
            runnable.run();
        } catch (Exception ex) {
            if (expectedExceptionClass.isInstance(ex)) return expectedExceptionClass.cast(ex);
        }
        fail();
        throw new RuntimeException("assertThrows expects callable to throw an exception");
    }
}