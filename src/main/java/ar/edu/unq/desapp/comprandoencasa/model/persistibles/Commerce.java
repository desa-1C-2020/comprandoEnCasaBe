package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.api.optionals.Optional;
import com.google.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;

public class Commerce {
    public static final String name_FIELD = "name";
    public static final String id_FIELD = "id";
    public static final String address_FIELD = "address";
    public static final String saleableItems_FIELD = "saleableItems";
    private String id;
    private String name;
    private String businessSector;
    private Address address;
    private List<PaymentMethod> paymentMethods;
    private List<String> daysAndHoursOpen;
    private String arrivalRange;
    private List<SaleableItem> saleableItems;

    //For springboot serializer
    public Commerce() {
    }

    public Commerce(String name, String businessSector, Address address, List<PaymentMethod> paymentMethods,
                    List<String> daysAndHoursOpen, String arrivalRange) {
        this.name = name;
        this.businessSector = businessSector;
        this.address = address;
        this.paymentMethods = paymentMethods;
        this.daysAndHoursOpen = daysAndHoursOpen;
        this.arrivalRange = arrivalRange;
        this.saleableItems = new ArrayList<>();
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
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

    public List<SaleableItem> getSaleableItems() {
        return saleableItems;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addSaleableItem(SaleableItem saleableItem) {
        saleableItems.add(saleableItem);
    }

    public boolean containsProduct(Product product) {
        return saleableItems.stream().anyMatch(saleableItem -> saleableItem.sameProduct(product));
    }

    public boolean containsProductWithId(String productId) {
        return saleableItems.stream().anyMatch(saleableItem -> saleableItem.sameProductId(productId));
    }

    public void removeProduct(Product product) {
        SaleableItem saleableItem = findByProduct(product);
        saleableItems.remove(saleableItem);
    }

    private SaleableItem findByProduct(Product product) {
        return saleableItems
            .stream()
            .filter(saleableItem -> saleableItem.sameProduct(product))
            .findFirst()
            .get();
    }

    private SaleableItem findByProductId(String productId) {
        return saleableItems
            .stream()
            .filter(saleableItem -> saleableItem.sameProductId(productId))
            .findFirst()
            .get();
    }

    public SaleableItem removeSaleableItemByProductId(String productId) {
        SaleableItem saleableItem = findByProductId(productId);
        saleableItems.remove(saleableItem);
        return saleableItem;
    }

    public void updateSaleableItem(SaleableItem toUpdate) {
        Optional<SaleableItem> saleableItemOptional =
            Nary.create(saleableItems).filterOptional(saleableItem -> saleableItem.sameProductId(toUpdate.getProductId()));

        if (saleableItemOptional.isAbsent()) {
            String errorMessage = "No se puede actualizar. No existe el producto con id: [" + toUpdate.getProductId() + "]";
            throw new RuntimeException(errorMessage);
        }
        SaleableItem saleableItem = saleableItemOptional.get();
        saleableItem.updateWith(toUpdate);
    }

    public Product getProductById(String saleableId) {
        Optional<SaleableItem> saleableItemOptional = Nary
            .create(saleableItems)
            .filterOptional(saleableItem -> saleableItem.sameProductId(saleableId));
        SaleableItem saleableItem = saleableItemOptional.get();
        return saleableItem.getProduct();
    }
}
