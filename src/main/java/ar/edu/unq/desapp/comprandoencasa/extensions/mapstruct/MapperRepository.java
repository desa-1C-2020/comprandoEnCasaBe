package ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct;


import ar.com.kfgodel.nary.api.optionals.Optional;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Type;
import java.util.List;
import java.util.function.Function;

/**
 * This type represents a source of mappers for conversions
 */
public interface MapperRepository {

    /**
     * Finds the existing mapper for conversion from input type to output type.<br>
     * Returning empty if none is found
     *
     * @param inputType  The class to indicate input type
     * @param outputType The class to indicate output type
     * @param <I>        Accepted input type
     * @param <O>        Expected output type
     * @return The optional function that maps between types
     */
    <I, O> Optional<Function<I, O>> findMapperFor(Class<I> inputType, Class<O> outputType);

    /**
     * Finds the existing mapper to convert an instance from input type to an instance of output type.<br>
     * Returns an empty optional if none is found
     *
     * @param inputType  The generic type to indicate the function argument type
     * @param outputType The generic type to indicate the function expected output type
     * @param <I>        Function input type
     * @param <O>        Function output type
     * @return The optional function if found
     */
    <I, O> Optional<Function<I, O>> findMapperFor(Type inputType, Type outputType);

    /**
     * It returns all the available mappers in this repository
     *
     * @return The list of mappers
     */
    List<Function<Object, Object>> findAll();

    void loadMappersFrom(ApplicationContext springContext);
}
