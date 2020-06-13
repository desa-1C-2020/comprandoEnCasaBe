package ar.edu.unq.desapp.comprandoencasa.model;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Address;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.service.GoogleConnector;
import com.google.maps.model.LatLng;

import java.util.List;
import java.util.stream.Collectors;

public class DistanceCalculator {
    private List<Commerce> commerceList;
    private GoogleConnector googleConnector;

    public DistanceCalculator(List<Commerce> commerceList, GoogleConnector googleConnector) {
        this.commerceList = commerceList;
        this.googleConnector = googleConnector;
    }

    public List<Commerce> getByLatLngInRange(LatLng latLngFrom, Long maxDistance) {
        return commerceList
            .stream()
            .filter(commerce -> isInsideRange(latLngFrom, commerce, maxDistance))
            .collect(Collectors.toList());
    }

    private boolean isInsideRange(LatLng latLngFrom, Commerce commerce, Long maxDistance) {
        Optional<Long> distanceInMeters = googleConnector.distanceInMetersBetweenTwoLatLng(latLngFrom, commerce.getLatLong());
        if (distanceInMeters.isAbsent()) {
            return false;
        }

        return distanceInMeters.get() <= maxDistance;
    }

    public Long calculateTotalDistance(List<Address> commercesAddress, Address destination) {
        List<LatLng> latLngList = commercesAddress.stream().map(commerceAddress -> commerceAddress.getLatLng()).collect(Collectors.toList());
        Optional<Long> distanceInMeters = googleConnector.totalDistanceInMetersBetween(latLngList, destination.getLatLng());
        return null;
    }
}
