package ar.edu.unq.desapp.comprandoencasa.configurations;

import ar.edu.unq.desapp.comprandoencasa.controllers.CommerceRepository;
import ar.edu.unq.desapp.comprandoencasa.model.Commerce;
import ar.edu.unq.desapp.comprandoencasa.repositories.CommerceRepositoryMem;
import com.google.maps.model.LatLng;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RepositoryConfiguration {
    @Bean
    public CommerceRepository commerceRepository() {
        CommerceRepositoryMem commerceRepositoryMem = new CommerceRepositoryMem();
        simulateCommerceFakeData(commerceRepositoryMem);
        return commerceRepositoryMem;
    }

    private void simulateCommerceFakeData(CommerceRepositoryMem commerceRepositoryMem) {
        List<String> paymentMethods = new ArrayList<>();
        List<String> horarios = new ArrayList<>();
        paymentMethods.add("Efectivo");
        horarios.add("Lunes a viernes de 10 a 18hs");
        Commerce kiosco = new Commerce("Kiosco", "Roque Sáenz Peña 284, Bernal, Buenos Aires", paymentMethods, horarios, "3km");
        Commerce kiosco2 = new Commerce("Kiosco", "Roque Sáenz Peña 106, Bernal, Buenos Aires", paymentMethods, horarios, "5km");
        Commerce almacen = new Commerce("Almacen", "Roque Sáenz Peña 700, Bernal, Buenos Aires", paymentMethods, horarios, "4km");
        Commerce perfumeria = new Commerce("Perfumeria", "Lebensohn Nº 789, B1876 Bernal, Buenos Aires", paymentMethods, horarios, "2km");

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
