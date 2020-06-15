package ar.edu.unq.desapp.comprandoencasa.service;

import ar.edu.unq.desapp.comprandoencasa.configurations.GoogleConnector;
import ar.edu.unq.desapp.comprandoencasa.model.DistanceCalculator;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.repositories.CommerceRepository;
import com.google.maps.model.LatLng;

import java.util.List;

public class CommerceFinder {
    private CommerceRepository commerceRepository;
    private GoogleConnector googleConnector;

    public CommerceFinder(CommerceRepository commerceRepository, GoogleConnector googleConnector) {
        this.commerceRepository = commerceRepository;
        this.googleConnector = googleConnector;
    }

    public List<Commerce> findAllInsideRange(String maxDistanceMeters, LatLng latLngFrom) {
        Long maxDistanceLong = (long) Double.parseDouble(maxDistanceMeters);

        List<Commerce> commerceList = commerceRepository.getAll();
        DistanceCalculator distanceCalculator = new DistanceCalculator(googleConnector);

        return distanceCalculator.getByLatLngInRange(latLngFrom, maxDistanceLong, commerceList);
    }
}
