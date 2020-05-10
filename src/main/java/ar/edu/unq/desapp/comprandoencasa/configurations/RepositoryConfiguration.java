package ar.edu.unq.desapp.comprandoencasa.configurations;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserRol;
import ar.edu.unq.desapp.comprandoencasa.repositories.CommerceRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.CommerceRepositoryMem;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserRepositoryMem;
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
        simulateUserFakeData(userRepositoryMem, commerceRepository());
        return userRepositoryMem;
    }

    private void simulateUserFakeData(UserRepository repo, CommerceRepository commerceRepository) {
        Commerce anyCommerce = commerceRepository.getAll().get(0);
        Commerce otherCommerce = commerceRepository.getAll().get(1);
        User userBuyerWithCommerce = User.createWithCommerce("Marcos", "Alvarenga", "marcos@10pines.com", UserRol.BUYER, anyCommerce);
        User userSellerWithCommerce = User.createWithCommerce("Daniel", "Alvarenga", "marcos+2@10pines.com", UserRol.SELLER, otherCommerce);
        String userBuyerId = "ID DEL USUARIO BUYER************ -> " + userBuyerWithCommerce.getUid();
        String userSellerId = "ID DEL USUARIO SELLER************ -> " + userSellerWithCommerce.getUid();
        logger.info(userBuyerId);
        logger.info(userSellerId);
        repo.addUser(userBuyerWithCommerce);
        repo.addUser(userSellerWithCommerce);
    }

    private void simulateCommerceFakeData(CommerceRepositoryMem commerceRepositoryMem) {
        List<String> paymentMethods = new ArrayList<>();
        List<String> horarios = new ArrayList<>();
        paymentMethods.add("Efectivo");
        horarios.add("Lunes a viernes de 10 a 18hs");
        Commerce kiosco = new Commerce("un nombre de comercio", "Kiosco", "Roque Sáenz Peña 284, Bernal, Buenos Aires", paymentMethods, horarios, "3km");
        Commerce kiosco2 = new Commerce("un nombre de comercio", "Kiosco", "Roque Sáenz Peña 106, Bernal, Buenos Aires", paymentMethods, horarios, "5km");
        Commerce almacen = new Commerce("un nombre de comercio", "Almacen", "Roque Sáenz Peña 700, Bernal, Buenos Aires", paymentMethods, horarios, "4km");
        Commerce perfumeria = new Commerce("un nombre de comercio", "Perfumeria", "Lebensohn Nº 789, B1876 Bernal, Buenos Aires", paymentMethods, horarios, "2km");

        kiosco.setLatLng(new LatLng(-34.7066345, -58.2819718));
        kiosco2.setLatLng(new LatLng(-34.7166345, -58.2822718));
        almacen.setLatLng(new LatLng(-34.7104061, -58.2823677));
        perfumeria.setLatLng(new LatLng(-34.7105312, -58.2742587));
        commerceRepositoryMem.add(kiosco);
        commerceRepositoryMem.add(kiosco2);
        commerceRepositoryMem.add(almacen);
        commerceRepositoryMem.add(perfumeria);
    }
}
