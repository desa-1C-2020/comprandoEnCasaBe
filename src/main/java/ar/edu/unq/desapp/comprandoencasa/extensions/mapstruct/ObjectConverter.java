package ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct;

import ar.com.kfgodel.nary.api.optionals.Optional;
import com.fasterxml.jackson.core.type.TypeReference;

import javax.lang.model.type.NullType;
import java.lang.reflect.Type;

/**
 * This interface defines general conversion methods for transforming objects between different types.<br>
 * This interface offers alternative methods to accept different representations for object types
 * (Class, Type, TypeReference), but they end up in one base case that must be defined by implementations.
 */
public interface ObjectConverter {
    /**
     * Converts the given input value into an instance of the indicated type
     *
     * @param outputType The expected result type
     * @param inputValue The input value
     * @param <O>        Type variable for result type
     * @return The converted result
     * @throws IllegalStateException If no conversion is available for the parameters
     */
    default <O> O convertTo(Class<O> outputType, Object inputValue) throws IllegalStateException {
        return this.convertTo((Type) outputType, inputValue);
    }

    /**
     * Converts the given input value into an instance of the indicated type. <br>
     * This method allows type reference for parameterized types
     *
     * @param outputType The expected result type
     * @param inputValue The input value
     * @param <O>        Type variable for result type
     * @return The converted result
     * @throws IllegalStateException If no conversion is available for the parameters
     */
    default <O> O convertTo(TypeReference<O> outputType, Object inputValue) throws IllegalStateException {
        return this.convertTo(outputType.getType(), inputValue);
    }

    /**
     * Converts the given input value into an instance of the indicated type.<br>
     * This is a generic version that allows parameterized types as output type, but loses type inference for result
     *
     * @param outputType The expected result type
     * @param inputValue The input value
     * @param <O>        Type variable for result type
     * @return The converted result
     * @throws IllegalStateException If no conversion is available for the parameters
     */
    default <O> O convertTo(Type outputType, Object inputValue) throws IllegalStateException {
        Class inputType = Optional.ofNullable(inputValue)
            .mapNary(Object::getClass)
            .orElse((Class) NullType.class);
        return convertFrom(inputType, outputType, inputValue);
    }

    /**
     * Converts the given input value into an instance of the indicated output type. <br>
     * It takes an inputType to override input value's type when looking for a valid conversion.<br>
     *
     * @param inputType  The complete input type (not available from input value)
     * @param outputType The expected output type
     * @param inputValue The input value to convert
     * @param <O>        The expected Output type
     * @return The converted result
     * @throws IllegalStateException If no conversion is available for the parameters
     */
    default <I, O> O convertFrom(Class<I> inputType, Class<O> outputType, Object inputValue) throws IllegalStateException {
        return this.convertFrom((Type) inputType, outputType, inputValue);
    }

    /**
     * Converts the given input value into an instance of the indicated output type.<br>
     * It takes an inputType to override input value's type when looking for a valid conversion.<br>
     * This method eases it usage by allowing type references
     *
     * @param inputType  The complete input type (not available from input value)
     * @param outputType The expected output type
     * @param inputValue The input value to convert
     * @param <O>        The expected Output type
     * @return The converted result
     * @throws IllegalStateException If no conversion is available for the parameters
     */
    default <I, O> O convertFrom(TypeReference<I> inputType, TypeReference<O> outputType, Object inputValue) throws IllegalStateException {
        return this.convertFrom(inputType.getType(), outputType.getType(), inputValue);
    }

    /**
     * Converts the given input value into an instance of the indicated output type. It uses the inputType to look for the best conversion.<br>
     * This method version allows parameterized types on input and output
     *
     * @param inputType  The complete input type (not available from input value)
     * @param outputType The expected output type
     * @param inputValue The input value to convert
     * @param <O>        The expected Output type
     * @return The converted result
     * @throws IllegalStateException If no conversion is available for the parameters
     */
    <O> O convertFrom(Type inputType, Type outputType, Object inputValue) throws IllegalStateException;

}
