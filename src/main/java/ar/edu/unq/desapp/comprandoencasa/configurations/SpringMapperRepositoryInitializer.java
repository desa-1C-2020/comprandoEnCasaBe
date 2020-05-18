package ar.edu.unq.desapp.comprandoencasa.configurations;

import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.MapperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SpringMapperRepositoryInitializer {
    @Autowired
    private MapperRepository mapperRepository;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        mapperRepository.loadMappersFrom(event.getApplicationContext());
    }
}
