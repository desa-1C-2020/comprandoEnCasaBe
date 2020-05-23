package ar.edu.unq.desapp.comprandoencasa.service;

import ar.com.kfgodel.nary.api.optionals.Optional;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.Distance;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixRow;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;

import java.io.IOException;
import java.util.List;

public class GoogleConnector {
    private GeoApiContext context;

    public GoogleConnector(String googleApiKey) {
        context = new GeoApiContext.Builder().apiKey(googleApiKey).build();
    }

    public AddressComponent[] coordsFromAddress(String address) throws ApiException, InterruptedException, IOException {
        GeocodingResult[] results = getGeoCodeFrom(context, address);
        AddressComponent[] addressComponents = mapToAddressComponent(results[0]);

        return addressComponents;
    }

    public Distance distanceBetweenTwoAddess(String addressFrom, String addressTo) throws InterruptedException, ApiException, IOException {
        String[] origins = new String[]{addressFrom};
        String[] destinations = new String[]{addressTo};

        DistanceMatrix distanceMatrix = DistanceMatrixApi.newRequest(context)
            .origins(origins)
            .destinations(destinations)
            .mode(TravelMode.BICYCLING)
            .await();

        return distanceMatrix.rows[0].elements[0].distance;
    }

    public Optional<Long> totalDistanceInMetersBetween(List<LatLng> addressesDestionations, LatLng addressOrigin) {
        LatLng[] origins = new LatLng[]{addressOrigin};
        LatLng[] destinations = addressesDestionations.stream().toArray(LatLng[]::new);

        return distanceMatrixCalculationBetween(origins, destinations);
    }

    public Optional<Long> distanceInMetersBetweenTwoLatLng(LatLng latLngFrom, LatLng latLngTo) {
        LatLng[] origins = new LatLng[]{latLngFrom};
        LatLng[] destionations = new LatLng[]{latLngTo};

        return distanceMatrixCalculationBetween(origins, destionations);
    }

    private DistanceMatrixRow distanceMatrixCalculationBetween(LatLng[] origins, LatLng[] destinations) {
        DistanceMatrix distanceMatrix;
        try {
            distanceMatrix = DistanceMatrixApi.newRequest(context)
                .origins(origins)
                .destinations(destinations)
                .mode(TravelMode.BICYCLING)
                .await();
        } catch (ApiException e) {
            e.printStackTrace();
            return Optional.empty();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return Optional.empty();
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }

        return Optional.ofNullable(distanceMatrix.rows[0];
//        return Optional.ofNullable(distanceMatrixRow.elements[0].distance.inMeters);
    }

    private AddressComponent[] mapToAddressComponent(GeocodingResult result) {
        AddressComponent[] addressComponents = result.addressComponents;

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(addressComponents));

        return addressComponents;
    }

    private GeocodingResult[] getGeoCodeFrom(GeoApiContext context, String address) throws ApiException,
        InterruptedException, IOException {
        return GeocodingApi.geocode(context, address).await();
    }
}
