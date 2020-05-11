package ar.edu.unq.desapp.comprandoencasa.controllers.to;

public class AddressTo {
    private String street;
    private Long latitud;
    private Long longitud;

    public AddressTo(String street, Long latitud, Long longitud) {
        this.street = street;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Long getLatitud() {
        return latitud;
    }

    public void setLatitud(Long latitud) {
        this.latitud = latitud;
    }

    public Long getLongitud() {
        return longitud;
    }

    public void setLongitud(Long longitud) {
        this.longitud = longitud;
    }
}
