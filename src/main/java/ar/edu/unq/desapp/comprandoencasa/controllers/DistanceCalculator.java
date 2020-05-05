package ar.edu.unq.desapp.comprandoencasa.controllers;

import ar.edu.unq.desapp.comprandoencasa.configurations.GoogleConnector;
import ar.edu.unq.desapp.comprandoencasa.model.Commerce;
import com.google.maps.errors.ApiException;
import com.google.maps.model.Distance;
import com.google.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class DistanceCalculator {
    private GoogleConnector googleConnector;

    public DistanceCalculator(GoogleConnector googleConnector) {
        this.googleConnector = googleConnector;
    }

    public List<Commerce> getInFrom(List<Commerce> commerceList, Double latitud, Double longitud, Long maxDistance) {
        LatLng latLngFrom = new LatLng(latitud, longitud);
        return commerceList.stream().filter(commerce -> isInsideRange(maxDistance, latLngFrom, commerce))
            .collect(Collectors.toList());

    }

    private boolean isInsideRange(Long maxDistance, LatLng latLngFrom, Commerce commerce) {
        Distance distance = null;
        try {
            distance = googleConnector.distanceBetweenTwoLatLng(latLngFrom, commerce.getLatLong());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(distance == null){
            return false;
        }
        return distance.inMeters <= maxDistance;
    }
}
