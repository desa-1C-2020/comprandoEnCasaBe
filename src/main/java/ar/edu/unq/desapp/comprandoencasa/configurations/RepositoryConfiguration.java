package ar.edu.unq.desapp.comprandoencasa.configurations;

import ar.edu.unq.desapp.comprandoencasa.repositories.CommerceRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.mem.CommerceRepositoryMem;
import ar.edu.unq.desapp.comprandoencasa.repositories.SaleableItemRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.mem.SaleableItemRepositoryMem;
import ar.edu.unq.desapp.comprandoencasa.repositories.ShoppingListRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.mem.ShoppingListRepositoryMem;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserBuyerRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.mem.UserBuyerRepositoryMem;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.mem.UserRepositoryMem;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserSellerRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.mem.UserSellerRepositoryMem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {
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
}
