package ar.edu.unq.desapp.comprandoencasa.model;

import com.google.maps.model.LatLng;

import java.util.List;

public class Commerce {
    private String businessSector;
    private String adress;
    private List<String> paymentMethods;
    private List<String> daysAndHoursOpen;

    public void setBusinessSector(String businessSector) {
        this.businessSector = businessSector;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setPaymentMethods(List<String> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public void setDaysAndHoursOpen(List<String> daysAndHoursOpen) {
        this.daysAndHoursOpen = daysAndHoursOpen;
    }

    public void setRange(String range) {
        this.range = range;
    }

    private String range;
    private LatLng latLng;

    public Commerce(){

    }

    public Commerce(String businessSector, String adress, List<String> paymentMethods, List<String> daysAndHoursOpen,
                    String range) {
        this.businessSector = businessSector;
        this.adress = adress;
        this.paymentMethods = paymentMethods;
        this.daysAndHoursOpen = daysAndHoursOpen;
        this.range = range;
    }

    public String getBusinessSector() {
        return businessSector;
    }

    public String getAdress() {
        return adress;
    }

    public List<String> getPaymentMethods() {
        return paymentMethods;
    }

    public List<String> getDaysAndHoursOpen() {
        return daysAndHoursOpen;
    }

    public String getRange() {
        return range;
    }

    public LatLng getLatLong() {
        return latLng;
    }

    public void setLatLng(LatLng latLng){
        this.latLng = latLng;
    }
}
