package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import com.google.maps.model.LatLng;

public class Address {
    private String adress;
    private LatLng latLng;

    public Address(String adress, LatLng latLng) {
        this.adress = adress;
        this.latLng = latLng;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
