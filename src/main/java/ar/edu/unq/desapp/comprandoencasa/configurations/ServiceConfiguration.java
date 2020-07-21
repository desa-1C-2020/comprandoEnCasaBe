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
import ar.edu.unq.desapp.comprandoencasa.sendgrid.SendGridClient;
import ar.edu.unq.desapp.comprandoencasa.service.CommerceFinderService;
import ar.edu.unq.desapp.comprandoencasa.service.DeliveryService;
import ar.edu.unq.desapp.comprandoencasa.service.EmailSenderService;
import ar.edu.unq.desapp.comprandoencasa.service.ProductFinderService;
import ar.edu.unq.desapp.comprandoencasa.service.PurchaseService;
import ar.edu.unq.desapp.comprandoencasa.service.SaleRegisterService;
import ar.edu.unq.desapp.comprandoencasa.service.SaleableItemService;
import ar.edu.unq.desapp.comprandoencasa.service.ShoppingListCreatorService;
import ar.edu.unq.desapp.comprandoencasa.service.UserFinderService;
import ar.edu.unq.desapp.comprandoencasa.service.UserLogerService;
import ar.edu.unq.desapp.comprandoencasa.service.UserRegistrarService;
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
    public UserFinderService userFinder(UserRepository userRepository, UserSellerRepository userSellerRepository,
                                 UserBuyerRepository userBuyerRepository) {
        return new UserFinderService(userRepository, userSellerRepository, userBuyerRepository);
    }

    @Bean
    public SaleableItemService userSellerService(UserFinderService userFinder, SaleableItemRepository saleableItemRepository,
                                                 CommerceRepository commerceRepository) {
        return new SaleableItemService(userFinder, saleableItemRepository, commerceRepository);
    }

    @Bean
    public UserRegistrarService userRegistrar(UserFinderService userFinder, UserRepository userRepository,
                                       UserBuyerRepository userBuyerRepository,
                                       UserSellerRepository userSellerRepository, ObjectConverter objectConverter) {
        return new UserRegistrarService(userFinder, userRepository, userBuyerRepository, userSellerRepository,
            objectConverter);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public ShoppingListCreatorService shoppingListCreator(UserFinderService userFinder,
                                                   ShoppingListRepository shoppingListRepository,
                                                   CommerceRepository commerceRepository) {
        return new ShoppingListCreatorService(userFinder, shoppingListRepository, commerceRepository);
    }

    @Bean
    public UserLogerService userLoger(UserFinderService userFinder) {
        return new UserLogerService(userFinder);
    }

    @Bean
    public CommerceFinderService commerceFinder(CommerceRepository commerceRepository, GoogleConnector googleConnector) {
        return new CommerceFinderService(commerceRepository, googleConnector);
    }

    @Bean
    public ProductFinderService productFinder(CommerceFinderService commerceFinder, GoogleConnector googleConnector, ObjectConverter converter) {
        return new ProductFinderService(commerceFinder, googleConnector, converter);
    }

    @Bean
    public PurchaseService purchaseService(CommerceFinderService commerceFinder, UserFinderService userFinder,
                                           DeliveryService deliveryService, ShoppingListCreatorService shoppingListCreator,
                                           PurchaseRepository purchaseRepository, SaleRegisterService saleRegisterService,
                                           EmailSenderService emailSender) {
        return new PurchaseService(commerceFinder, userFinder, deliveryService, shoppingListCreator,
            purchaseRepository, saleRegisterService, emailSender);
    }

    @Bean
    public SaleRegisterService saleRegisterService(SaleRegisterRepository saleRegisterRepository,
                                                   CommerceRepository commerceRepository, UserFinderService userFinder) {
        return new SaleRegisterService(saleRegisterRepository, commerceRepository, userFinder);
    }

    @Bean
    public DeliveryService deliveryService(DeliveryRepository deliveryRepository) {
        return new DeliveryService(deliveryRepository, maxDeliverysPerDay);
    }

    @Bean
    public EmailSenderService emailSender(SendGridClient sendGridClient, UserFinderService userFinder) {
        return new EmailSenderService(sendGridClient, userFinder);
    }
}
