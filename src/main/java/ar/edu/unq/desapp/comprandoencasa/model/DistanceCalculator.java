package ar.edu.unq.desapp.comprandoencasa.model;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.configurations.GoogleConnector;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import com.google.maps.model.LatLng;

import java.util.List;
import java.util.stream.Collectors;

public class DistanceCalculator {
    private GoogleConnector googleConnector;

    public DistanceCalculator(GoogleConnector googleConnector) {
        this.googleConnector = googleConnector;
    }

    public List<Commerce> getByLatLngInRange(LatLng latLngFrom, Long maxDistance, List<Commerce> commerceList) {
        return commerceList
            .stream()
            .filter(commerce -> isInsideRange(latLngFrom, commerce, maxDistance))
            .collect(Collectors.toList());
    }

    private boolean isInsideRange(LatLng latLngFrom, Commerce commerce, Long maxDistance) {
        Optional<Long> distanceInMeters = distanceInMetersBetweenTwoLatLng(latLngFrom, commerce.getLatLong());
        if (distanceInMeters.isAbsent()) {
            return false;
        }

        return distanceInMeters.get() <= maxDistance;
    }

    public Optional<Long> distanceInMetersBetweenTwoLatLng(LatLng latLngFrom, LatLng latLong) {
        return googleConnector.distanceInMetersBetweenTwoLatLng(latLngFrom, latLong);
    }
}
