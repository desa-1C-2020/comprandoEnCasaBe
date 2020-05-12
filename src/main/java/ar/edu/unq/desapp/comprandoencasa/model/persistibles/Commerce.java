package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.api.optionals.Optional;
import com.google.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;

public class Commerce {
    private String id;
    private String name;
    private String businessSector;
    private Address address;
    private List<PaymentMethod> paymentMethods;
    private List<String> daysAndHoursOpen;
    private String arrivalRange;
    private List<Product> products;
    //For springboot serializer
    public Commerce() {
    }
    public Commerce(String name, String businessSector, Address adress, List<PaymentMethod> paymentMethods,
                    List<String> daysAndHoursOpen, String arrivalRange) {
        this.name = name;
        this.businessSector = businessSector;
        this.address = adress;
        this.paymentMethods = paymentMethods;
        this.daysAndHoursOpen = daysAndHoursOpen;
        this.arrivalRange = arrivalRange;
        this.products = new ArrayList<>();
        this.id = randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBusinessSector() {
        return businessSector;
    }

    public void setBusinessSector(String businessSector) {
        this.businessSector = businessSector;
    }

    public Address getAdress() {
        return address;
    }

    public void setAdress(Address address) {
        this.address = address;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public List<String> getDaysAndHoursOpen() {
        return daysAndHoursOpen;
    }

    public void setDaysAndHoursOpen(List<String> daysAndHoursOpen) {
        this.daysAndHoursOpen = daysAndHoursOpen;
    }

    public String getArrivalRange() {
        return arrivalRange;
    }

    public void setArrivalRange(String arrivalRange) {
        this.arrivalRange = arrivalRange;
    }

    public LatLng getLatLong() {
        return address.getLatLng();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public boolean containsProduct(Product product) {
        return products.stream().anyMatch(product1 -> product.sameProduct(product));
    }

    public boolean containsProductWithId(String productId) {
        return products.stream().anyMatch(product -> product.sameId(productId));
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public void removeProductById(String productId) {
        Optional<Product> first = Nary.create(products).filterOptional(product -> product.sameId(productId));
        products.remove(first.get());
    }

    public void updateProduct(Product productToUpdate) {
        Optional<Product> productOptional = Nary.create(products).filterOptional(product -> product.sameProduct(productToUpdate));

        if (!productOptional.isAbsent()) {
            Product product = productOptional.get();
            product.updateWith(productToUpdate);
        }
    }

    public Product getProductById(String productId) {
        Optional<Product> productOptional = Nary.create(products).filterOptional(product -> product.sameId(productId));
        return productOptional.get();
    }
}
