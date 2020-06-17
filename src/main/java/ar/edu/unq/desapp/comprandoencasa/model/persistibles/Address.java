package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import ar.edu.unq.desapp.comprandoencasa.support.PersistibleSupport;
import com.google.maps.model.LatLng;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address extends PersistibleSupport {
    private String street;
    private LatLng latLng;

    public static Address create(String street, LatLng latLng) {
        Address address = new Address();
        address.street = street;
        address.latLng = latLng;
        return address;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public double getLatitud() {
        return latLng.lat;
    }

    public double getLongitud() {
        return latLng.lng;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
