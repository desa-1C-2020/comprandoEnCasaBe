package ar.edu.unq.desapp.comprandoencasa.configurations;

import ar.edu.unq.desapp.comprandoencasa.repositories.CommerceRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.DeliveryRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.PurchaseRegisterRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.PurchaseRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.SaleableItemRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.ShoppingListRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserBuyerRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserSellerRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.memoria.CommerceRepositoryMem;
import ar.edu.unq.desapp.comprandoencasa.repositories.memoria.DeliveryRepositoryMem;
import ar.edu.unq.desapp.comprandoencasa.repositories.memoria.PurchaseRegisterRepositoryMem;
import ar.edu.unq.desapp.comprandoencasa.repositories.memoria.PurchaseRepositoryMem;
import ar.edu.unq.desapp.comprandoencasa.repositories.memoria.SaleableItemRepositoryMem;
import ar.edu.unq.desapp.comprandoencasa.repositories.memoria.ShoppingListRepositoryMem;
import ar.edu.unq.desapp.comprandoencasa.repositories.memoria.UserBuyerRepositoryMem;
import ar.edu.unq.desapp.comprandoencasa.repositories.memoria.UserRepositoryMem;
import ar.edu.unq.desapp.comprandoencasa.repositories.memoria.UserSellerRepositoryMem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class RepositoryMemConfiguration {
    @Bean
    public CommerceRepository commerceRepository() {
        return new CommerceRepositoryMem();
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryMem();
    }

    @Bean
    public ShoppingListRepository shoppingListRepository() {
        return new ShoppingListRepositoryMem();
    }

    @Bean
    public UserBuyerRepository userBuyerRepository() {
        return new UserBuyerRepositoryMem();
    }

    @Bean
    public UserSellerRepository userSellerRepository() {
        return new UserSellerRepositoryMem();
    }

    @Bean
    public SaleableItemRepository saleableItemRepository() {
        return new SaleableItemRepositoryMem();
    }

    @Bean
    public DeliveryRepository deliveryRepository() {
        return new DeliveryRepositoryMem();
    }

    @Bean
    public PurchaseRegisterRepository purchaseRegisterRepository() {
        return new PurchaseRegisterRepositoryMem();
    }

    @Bean
    public PurchaseRepository purchaseRepository() {
        return new PurchaseRepositoryMem();
    }
}
