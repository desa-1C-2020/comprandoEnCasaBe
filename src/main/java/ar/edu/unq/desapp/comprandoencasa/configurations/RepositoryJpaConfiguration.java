package ar.edu.unq.desapp.comprandoencasa.configurations;

import ar.edu.unq.desapp.comprandoencasa.repositories.CommerceRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.SaleableItemRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.ShoppingListRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserBuyerRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserSellerRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.impl.UserBuyerRepositoryImpl;
import ar.edu.unq.desapp.comprandoencasa.repositories.impl.UserRepositoryImpl;
import ar.edu.unq.desapp.comprandoencasa.repositories.impl.UserSellerRepositoryImpl;
import ar.edu.unq.desapp.comprandoencasa.repositories.memoria.CommerceRepositoryMem;
import ar.edu.unq.desapp.comprandoencasa.repositories.memoria.SaleableItemRepositoryMem;
import ar.edu.unq.desapp.comprandoencasa.repositories.memoria.ShoppingListRepositoryMem;
import ar.edu.unq.desapp.comprandoencasa.repositories.memoria.UserSellerRepositoryMem;
import ar.edu.unq.desapp.comprandoencasa.repositories.spring.UserBuyerRepositoryJpa;
import ar.edu.unq.desapp.comprandoencasa.repositories.spring.UserRepositoryJpa;
import ar.edu.unq.desapp.comprandoencasa.repositories.spring.UserSellerRepositoryJpa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!test")
public class RepositoryJpaConfiguration {
    @Bean
    public UserRepository userRepository(UserRepositoryJpa userRepositoryJpa) {
        return UserRepositoryImpl.create(userRepositoryJpa);
    }

    @Bean
    public UserBuyerRepository userBuyerRepository(UserBuyerRepositoryJpa userBuyerRepositoryJpa) {
        return UserBuyerRepositoryImpl.create(userBuyerRepositoryJpa);
    }

    @Bean
    public UserSellerRepository userSellerRepository(UserSellerRepositoryJpa userSellerRepositoryJpa) {
        return UserSellerRepositoryImpl.create(userSellerRepositoryJpa);
    }

    //Reemplazar estos a medida que se hacen los mapeos
    @Bean
    public UserSellerRepository userSellerRepository() {
        return new UserSellerRepositoryMem();
    }

    @Bean
    public CommerceRepository commerceRepository() {
        return new CommerceRepositoryMem();
    }

    @Bean
    public ShoppingListRepository shoppingListRepository() {
        return new ShoppingListRepositoryMem();
    }

    @Bean
    public SaleableItemRepository saleableItemRepository() {
        return new SaleableItemRepositoryMem();
    }
}
