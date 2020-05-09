package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import com.google.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class Commerce {
    private String name;
    private String businessSector;
    private String adress;
    private List<String> paymentMethods;
    private List<String> daysAndHoursOpen;
    private String range;
    private LatLng latLng;
    private List<Product> products;

    //For springboot serializer
    public Commerce() {
    }

    public Commerce(String name, String businessSector, String adress, List<String> paymentMethods, List<String> daysAndHoursOpen,
                    String range) {
        this.name = name;
        this.businessSector = businessSector;
        this.adress = adress;
        this.paymentMethods = paymentMethods;
        this.daysAndHoursOpen = daysAndHoursOpen;
        this.range = range;
        this.products = new ArrayList<>();
    }

    public String getBusinessSector() {
        return businessSector;
    }

    public void setBusinessSector(String businessSector) {
        this.businessSector = businessSector;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public List<String> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<String> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public List<String> getDaysAndHoursOpen() {
        return daysAndHoursOpen;
    }

    public void setDaysAndHoursOpen(List<String> daysAndHoursOpen) {
        this.daysAndHoursOpen = daysAndHoursOpen;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public LatLng getLatLong() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public boolean containsProduct(Product product) {
        return products.contains(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
