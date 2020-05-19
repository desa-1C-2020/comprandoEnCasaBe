package ar.edu.unq.desapp.comprandoencasa.configurations;

import ar.edu.unq.desapp.comprandoencasa.extensions.ObjectMapper;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.ObjectConverter;
import ar.edu.unq.desapp.comprandoencasa.repositories.CommerceRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.SaleableItemRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.ShoppingListRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserBuyerRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserSellerRepository;
import ar.edu.unq.desapp.comprandoencasa.service.SaleableItemService;
import ar.edu.unq.desapp.comprandoencasa.service.ShoppingListCreator;
import ar.edu.unq.desapp.comprandoencasa.service.UserFinder;
import ar.edu.unq.desapp.comprandoencasa.service.UserRegistrar;
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
    public SaleableItemService userSellerService(UserFinder userFinder, SaleableItemRepository saleableItemRepository) {
        return new SaleableItemService(userFinder, saleableItemRepository);
    }

    @Bean
    public UserRegistrar userRegistrar(UserFinder userFinder, ObjectMapper objectMapper, UserRepository userRepository,
                                       UserBuyerRepository userBuyerRepository,
                                       UserSellerRepository userSellerRepository, ObjectConverter objectConverter) {
        return new UserRegistrar(userFinder, userRepository, userBuyerRepository, userSellerRepository,
            objectConverter);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public ShoppingListCreator shoppingListCreator(UserFinder userFinder,
                                                   ShoppingListRepository shoppingListRepository,
                                                   CommerceRepository commerceRepository) {
        return new ShoppingListCreator(userFinder, shoppingListRepository, commerceRepository);
    }
}
