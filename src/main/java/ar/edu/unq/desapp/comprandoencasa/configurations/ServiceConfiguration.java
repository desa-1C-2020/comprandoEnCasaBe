package ar.edu.unq.desapp.comprandoencasa.configurations;

import ar.edu.unq.desapp.comprandoencasa.model.DistanceCalculator;
import ar.edu.unq.desapp.comprandoencasa.service.UserService;
import ar.edu.unq.desapp.comprandoencasa.model.UserFinder;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserRepository;
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
    public DistanceCalculator distanceCalculator() {
        return new DistanceCalculator(googleConnector());
    }

    @Bean
    public UserFinder userFinder(UserRepository userRepository) {
        return new UserFinder(userRepository);
    }

    @Bean
    public UserService userService(UserFinder userFinder, UserRepository userRepository) {
        return new UserService(userFinder, userRepository);
    }
}
