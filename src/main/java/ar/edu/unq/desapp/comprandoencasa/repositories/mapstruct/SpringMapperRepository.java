package ar.edu.unq.desapp.comprandoencasa.repositories.mapstruct;

import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.MapperFunction;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.MapperRepository;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.TypeMapping;
import org.apache.commons.lang3.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * This class represents the repository to get mappers available on spring
 */
public class SpringMapperRepository implements MapperRepository {
    public static Logger LOG = LoggerFactory.getLogger(SpringMapperRepository.class);

    private Map<TypeMapping, Function> mappers;

    public static SpringMapperRepository create(ApplicationContext springContext) {
        SpringMapperRepository repository = new SpringMapperRepository();
        repository.mappers = new HashMap<>();
        return repository;
    }

    @Override
    public <I, O> Optional<Function<I, O>> findMapperFor(Class<I> inputType, Class<O> outputType) {
        return this.findMapperFor((Type) inputType, outputType); // Force cast to call the other method
    }

    @Override
    public <I, O> Optional<Function<I, O>> findMapperFor(Type inputType, Type outputType) {
        TypeMapping mapping = TypeMapping.create(inputType, outputType);
        return Optional.ofNullable(mappers.get(mapping));
    }

    @Override
    public List<Function<Object, Object>> findAll() {
        return new ArrayList<>((Collection) mappers.values()); // Cast to help compiler match different types
    }

    @Override
    public void loadMappersFrom(ApplicationContext springContext) {
        Collection<MapperFunction> availableBeans = springContext.getBeansOfType(MapperFunction.class).values();
        LOG.info("Registering {} mappers from context", availableBeans.size());
        Nary.create(availableBeans)
            .forEach(this::addMapper);
    }

    private void addMapper(MapperFunction<Object, Object> functionalMapper) {
        Optional<TypeMapping> extracted = extractMappingFrom(functionalMapper);
        extracted
            .ifAbsent(() -> LOG.warn("Ignoring non functional mapper: " + functionalMapper))
            .ifPresent(mapping -> mappers.put(mapping, functionalMapper));
    }

    private Optional<TypeMapping> extractMappingFrom(MapperFunction<Object, Object> functionalMapper) {
        Nary<Type> genericInterfaces = this.extractHierarchyInterfaces(functionalMapper);
        return genericInterfaces
            .filterNary(ParameterizedType.class::isInstance)
            .mapNary(ParameterizedType.class::cast)
            .filterNary(interfaceType -> interfaceType.getRawType().equals(MapperFunction.class))
            .mapNary(this::extractMappingFrom)
            .findFirstNary();
    }

    private Nary<Type> extractHierarchyInterfaces(MapperFunction<Object, Object> functionalMapper) {
        Class functionClass = functionalMapper.getClass();
        Nary<Class<?>> superTypes = Nary.create(ClassUtils.hierarchy(functionClass, ClassUtils.Interfaces.INCLUDE));
        return Nary.of(functionClass)
            .concatStream(superTypes)
            .flatMapNary(superClass -> Nary.create(superClass.getGenericInterfaces()));
    }

    private TypeMapping extractMappingFrom(ParameterizedType parameterizedType) {
        Type[] typeArguments = parameterizedType.getActualTypeArguments();
        if (typeArguments.length != 2) {
            throw new IllegalArgumentException("Function type is supposed to have 2 arguments. Wrong type: " + parameterizedType + "?");
        }
        Type inputType = typeArguments[0];
        Type oputputType = typeArguments[1];
        return TypeMapping.create(inputType, oputputType);
    }

}
