package ar.edu.unq.desapp.comprandoencasa.configurations;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Address;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Efectivo;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.PaymentMethod;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserBuyer;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserSeller;
import ar.edu.unq.desapp.comprandoencasa.repositories.CommerceRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.CommerceRepositoryMem;
import ar.edu.unq.desapp.comprandoencasa.repositories.SaleableItemRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.SaleableItemRepositoryMem;
import ar.edu.unq.desapp.comprandoencasa.repositories.ShoppingListRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.ShoppingListRepositoryMem;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserBuyerRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserBuyerRepositoryMem;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserRepositoryMem;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserSellerRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserSellerRepositoryMem;
import com.google.maps.model.LatLng;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RepositoryConfiguration {
    public static Logger logger = LoggerFactory.getLogger(RepositoryConfiguration.class);

    @Bean
    public CommerceRepository commerceRepository() {
        CommerceRepositoryMem commerceRepositoryMem = new CommerceRepositoryMem();
        simulateCommerceFakeData(commerceRepositoryMem);
        return commerceRepositoryMem;
    }

    @Bean
    public UserRepository userRepository() {
        UserRepositoryMem userRepositoryMem = new UserRepositoryMem();
        simulateUserFakeData(userRepositoryMem, commerceRepository(), userBuyerRepository(), userSellerRepository());
        return userRepositoryMem;
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

    private void simulateUserFakeData(UserRepository repo, CommerceRepository commerceRepository,
                                      UserBuyerRepository userBuyerRepository, UserSellerRepository userSellerRepository) {
        Commerce otherCommerce = commerceRepository.getAll().get(1);

        User userBuyer = User.create("Marcos", "Alvarenga", "marcos@10pines.com");
        UserBuyer userBuyer1 = new UserBuyer(userBuyer);

        User userSellerWithCommerce = User.create("Daniel", "Alvarenga", "marcos+2@10pines.com");
        UserSeller userSeller = new UserSeller(userSellerWithCommerce, otherCommerce);

        String userBuyerId = "ID DEL USUARIO BUYER************ -> " + userBuyer1.getUser().getUid();
        String userSellerId = "ID DEL USUARIO SELLER************ -> " + userSeller.getUser().getUid();
        logger.info(userBuyerId);
        logger.info(userSellerId);
        userBuyerRepository.save(userBuyer1);
        userSellerRepository.save(userSeller);
        repo.addUser(userBuyer);
        repo.addUser(userSellerWithCommerce);
    }

    private void simulateCommerceFakeData(CommerceRepositoryMem commerceRepositoryMem) {
        Efectivo efectivo = new Efectivo("pesos");
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        paymentMethods.add(efectivo);
        List<String> horarios = new ArrayList<>();
        horarios.add("Lunes a viernes de 10 a 18hs");

        Address kioscoAddress = new Address("Roque Sáenz Peña 284, Bernal, Buenos Aires", new LatLng(-34.7066345, -58.2819718));
        Address kiosco2Address = new Address("Roque Sáenz Peña 106, Bernal, Buenos Aires", new LatLng(-34.7166345, -58.2822718));
        Address almacenAddress = new Address("Roque Sáenz Peña 700, Bernal, Buenos Aires", new LatLng(-34.7104061, -58.2823677));
        Address perfumeriaAddress = new Address("Lebensohn Nº 789, B1876 Bernal, Buenos Aires", new LatLng(-34.7105312, -58.2742587));

        Commerce kiosco = new Commerce("un kiosco", "Kiosco", kioscoAddress, paymentMethods, horarios, "3km");
        Commerce kiosco2 = new Commerce("otro kiosco", "Kiosco", kiosco2Address, paymentMethods, horarios, "5km");
        Commerce almacen = new Commerce("un almacen", "Almacen", almacenAddress, paymentMethods, horarios, "4km");
        Commerce perfumeria = new Commerce("una perfumeria", "Perfumeria", perfumeriaAddress, paymentMethods, horarios, "2km");

        commerceRepositoryMem.add(kiosco);
        commerceRepositoryMem.add(kiosco2);
        commerceRepositoryMem.add(almacen);
        commerceRepositoryMem.add(perfumeria);
    }
}
