package ar.edu.unq.desapp.comprandoencasa.configurations;

import ar.edu.unq.desapp.comprandoencasa.sendgrid.SendGridClient;
import com.sendgrid.SendGrid;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SengridClientConfiguration {
    @Bean
    public SendGrid sendgrid(SendGridProperties sendgridProperties) {
        return new SendGrid(sendgridProperties.getApiKey());
    }

    @Bean
    public SendGridClient sendGridClient(SendGrid sendGrid, SendGridProperties configuration) {
        return SendGridClient.create(sendGrid, configuration);
    }
}