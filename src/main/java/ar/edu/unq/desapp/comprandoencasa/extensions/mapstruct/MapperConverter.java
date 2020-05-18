package ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct;


import ar.com.kfgodel.nary.api.optionals.Optional;

import javax.lang.model.type.NullType;
import java.lang.reflect.Type;
import java.util.function.Function;

/**
 * This class implements the object converter using mapstruct mappers
 */
public class MapperConverter implements ObjectConverter {

    private MapperRepository repository;

    public static MapperConverter create(MapperRepository repository) {
        MapperConverter converter = new MapperConverter();
        converter.repository = repository;
        return converter;
    }

    @Override
    public <O> O convertTo(Class<O> outputType, Object inputValue) throws IllegalStateException {
        return this.convertTo((Type) outputType, inputValue);
    }

    @Override
    public <O> O convertTo(Type outputType, Object inputValue) throws IllegalStateException {
        Class inputType = Optional.ofNullable(inputValue)
            .mapNary(Object::getClass)
            .orElse((Class) NullType.class);
        return convertFrom(inputType, outputType, inputValue);
    }

    @Override
    public <O> O convertFrom(Type inputType, Type outputType, Object inputValue) throws IllegalStateException {
        Optional<Function> foundMapper = (Optional) repository.findMapperFor(inputType, outputType);
        return (O) foundMapper
            .mapNary(mapper -> mapper.apply(inputValue))
            .orElseThrow(() -> new IllegalStateException("Missing mapper for output[" + outputType + "] and input: [" + inputType + "]. Converting: " + inputValue));
    }
}
