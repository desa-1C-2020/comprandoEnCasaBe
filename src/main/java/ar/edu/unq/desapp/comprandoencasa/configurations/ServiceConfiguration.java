package ar.edu.unq.desapp.comprandoencasa.configurations;

import ar.edu.unq.desapp.comprandoencasa.model.ObjectMapper;
import ar.edu.unq.desapp.comprandoencasa.model.UserFinder;
import ar.edu.unq.desapp.comprandoencasa.model.UserRegistrar;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserBuyerRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserSellerRepository;
import ar.edu.unq.desapp.comprandoencasa.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {
    @Value("${google.api.key}")
    private String googleApiKey;

    @Bean
    public GoogleConnector googleConnector() {
        return new GoogleConnector(googleApiKey);
    }

    @Bean
    public UserFinder userFinder(UserRepository userRepository, UserSellerRepository userSellerRepository) {
        return new UserFinder(userRepository, userSellerRepository);
    }

    @Bean
    public UserService userService(UserFinder userFinder, UserSellerRepository userSellerRepository) {
        return new UserService(userFinder, userSellerRepository);
    }

    @Bean
    public UserRegistrar userRegistrar(UserFinder userFinder, ObjectMapper objectMapper, UserRepository userRepository,
                                       UserBuyerRepository userBuyerRepository, UserSellerRepository userSellerRepository) {
        return new UserRegistrar(userFinder, objectMapper, userRepository, userBuyerRepository, userSellerRepository);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
