package ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct;

import java.util.function.Function;

/**
 * This interface is for mappers that can be used as functions and be collected by the {@link MapperRepository}.<br>
 * This is needed to detect on runtime the mapper functions (discriminated from non mapper functions) because the
 * {@link org.mapstruct.Mapper} annotation is lost on compilation
 *
 * @param <I> Input parameter type for the function
 * @param <O> Output type for the mapper
 */
public interface MapperFunction<I, O> extends Function<I, O> {
}
