package ar.edu.unq.desapp.comprandoencasa.configurations;

import ar.edu.unq.desapp.comprandoencasa.extensions.ObjectMapper;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.ObjectConverter;
import ar.edu.unq.desapp.comprandoencasa.repositories.CommerceRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.DeliveryRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.PurchaseRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.SaleRegisterRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.SaleableItemRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.ShoppingListRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserBuyerRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserSellerRepository;
import ar.edu.unq.desapp.comprandoencasa.service.CommerceFinder;
import ar.edu.unq.desapp.comprandoencasa.service.DeliveryService;
import ar.edu.unq.desapp.comprandoencasa.service.ProductFinder;
import ar.edu.unq.desapp.comprandoencasa.service.PurchaseService;
import ar.edu.unq.desapp.comprandoencasa.service.SaleRegisterService;
import ar.edu.unq.desapp.comprandoencasa.service.SaleableItemService;
import ar.edu.unq.desapp.comprandoencasa.service.ShoppingListCreator;
import ar.edu.unq.desapp.comprandoencasa.service.UserFinder;
import ar.edu.unq.desapp.comprandoencasa.service.UserLoger;
import ar.edu.unq.desapp.comprandoencasa.service.UserRegistrar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {
    @Value("${google.api.key}")
    private String googleApiKey;

    @Value("${delivery.maxDeliverysPerDay}")
    private int maxDeliverysPerDay;

    @Bean
    public GoogleConnector googleConnector() {
        return new GoogleConnector(googleApiKey);
    }

    @Bean
    public UserFinder userFinder(UserRepository userRepository, UserSellerRepository userSellerRepository,
                                 UserBuyerRepository userBuyerRepository) {
        return new UserFinder(userRepository, userSellerRepository, userBuyerRepository);
    }

    @Bean
    public SaleableItemService userSellerService(UserFinder userFinder, SaleableItemRepository saleableItemRepository,
                                                 CommerceRepository commerceRepository) {
        return new SaleableItemService(userFinder, saleableItemRepository, commerceRepository);
    }

    @Bean
    public UserRegistrar userRegistrar(UserFinder userFinder, UserRepository userRepository,
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

    @Bean
    public UserLoger userLoger(UserFinder userFinder) {
        return new UserLoger(userFinder);
    }

    @Bean
    public CommerceFinder commerceFinder(CommerceRepository commerceRepository, GoogleConnector googleConnector) {
        return new CommerceFinder(commerceRepository, googleConnector);
    }

    @Bean
    public ProductFinder productFinder(CommerceFinder commerceFinder, GoogleConnector googleConnector, ObjectConverter converter) {
        return new ProductFinder(commerceFinder, googleConnector, converter);
    }

    @Bean
    public PurchaseService purchaseService(CommerceFinder commerceFinder, UserFinder userFinder,
                                           DeliveryService deliveryService, ShoppingListCreator shoppingListCreator,
                                           PurchaseRepository purchaseRepository, SaleRegisterService saleRegisterService) {
        return new PurchaseService(commerceFinder, userFinder, deliveryService, shoppingListCreator,
            purchaseRepository, saleRegisterService);
    }

    @Bean
    public SaleRegisterService saleRegisterService(SaleRegisterRepository saleRegisterRepository,
                                                   CommerceRepository commerceRepository, UserFinder userFinder) {
        return new SaleRegisterService(saleRegisterRepository, commerceRepository, userFinder);
    }

    @Bean
    public DeliveryService deliveryService(DeliveryRepository deliveryRepository) {
        return new DeliveryService(deliveryRepository, maxDeliverysPerDay);
    }
}
