package ar.edu.unq.desapp.comprandoencasa.configurations;

import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.MapperConverter;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.MapperRepository;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.ObjectConverter;
import ar.edu.unq.desapp.comprandoencasa.repositories.mapstruct.SpringMapperRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Class to configure mappers and abstractions on spring
 */
@Configuration
public class MappersConfiguration {

    @Bean
    public MapperRepository mapperRepository(ApplicationContext springContext) {
        return SpringMapperRepository.create(springContext);
    }

    @Bean
    public ObjectConverter objectConverter(MapperRepository repository) {
        return MapperConverter.create(repository);
    }

}
