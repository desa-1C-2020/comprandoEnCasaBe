package ar.edu.unq.desapp.comprandoencasa.service;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Address;
import ar.edu.unq.desapp.meta.SpringIntegrationTest;
import com.google.maps.model.LatLng;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.isNotNull;

public class GoogleConnectorTest extends SpringIntegrationTest {

    @Autowired
    private GoogleConnector googleConnector;

    @Test
    public void xxx() {
        Address kioscoAddress = Address.create("Roque Sáenz Peña 284, Bernal, Buenos Aires", new LatLng(-34.7066345, -58.2819718));
        Address kiosco2Address = Address.create("Roque Sáenz Peña 106, Bernal, Buenos Aires", new LatLng(-34.7166345, -58.2822718));
        Address almacenAddress = Address.create("Roque Sáenz Peña 700, Bernal, Buenos Aires", new LatLng(-34.7104061, -58.2823677));
        Address perfumeriaAddress = Address.create("Lebensohn Nº 789, B1876 Bernal, Buenos Aires", new LatLng(-34.7105312, -58.2742587));
        List<LatLng> origenes = new ArrayList<>();
        origenes.add(kioscoAddress.getLatLng());
        origenes.add(kiosco2Address.getLatLng());
        origenes.add(almacenAddress.getLatLng());
        origenes.add(perfumeriaAddress.getLatLng());

        LatLng destino = new LatLng(-34.7040003, -58.2754042);


        Optional<Long> longOptional = googleConnector.totalDistanceInMetersBetween(origenes, destino);


        assertThat(longOptional, isNotNull());
    }
}
