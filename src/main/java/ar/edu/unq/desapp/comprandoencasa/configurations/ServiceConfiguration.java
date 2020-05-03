package ar.edu.unq.desapp.comprandoencasa.configurations;

import ar.edu.unq.desapp.comprandoencasa.controllers.DistanceCalculator;
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
}
