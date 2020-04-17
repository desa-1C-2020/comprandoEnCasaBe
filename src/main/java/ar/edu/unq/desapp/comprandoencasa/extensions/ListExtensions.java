package ar.edu.unq.desapp.comprandoencasa.extensions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Helpful methods to interact with lists!
 */
@SuppressWarnings("unused")
public class ListExtensions {

    @SafeVarargs
    public static <T> List<T> listOf(T... elementos) {
        return Arrays.asList(elementos);
    }

    @SafeVarargs
    public static <T> List<T> concat(List<T>... lists) {
        ArrayList<T> concatenation = new ArrayList<>();
        for (List<T> list : lists) {
            concatenation.addAll(list);
        }
        return concatenation;
    }
}
