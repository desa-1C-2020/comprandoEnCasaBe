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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

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

        Optional<DistanceMatrixRow[]> optional = distanceMatrixCalculationBetween(origins, destinations);
        return optional.mapOptional(this::sumDistances);
    }

    private Long sumDistances(DistanceMatrixRow[] distanceMatrixRows) {

        Stream<DistanceMatrixRow> distanceMatrixRowStream = Arrays.stream(distanceMatrixRows);
        distanceMatrixRowStream.map(distanceMatrixRow -> distanceMatrixRow.elements[0].distance.inMeters);
        return null;
    }

    public Optional<Long> distanceInMetersBetweenTwoLatLng(LatLng latLngFrom, LatLng latLngTo) {
        LatLng[] origins = new LatLng[]{latLngFrom};
        LatLng[] destionations = new LatLng[]{latLngTo};

        Optional<DistanceMatrixRow[]> optional = distanceMatrixCalculationBetween(origins, destionations);

        return optional.mapOptional(distanceMatrixRows -> distanceMatrixRows[0].elements[0].distance.inMeters);
    }

    private Optional<DistanceMatrixRow[]> distanceMatrixCalculationBetween(LatLng[] origins, LatLng[] destinations) {
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

        return Optional.ofNullable(distanceMatrix.rows);
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
