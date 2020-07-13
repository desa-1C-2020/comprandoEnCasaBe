package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.support.PersistibleSupport;
import com.google.maps.model.LatLng;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "commerce")
public class Commerce extends PersistibleSupport {
    public static final String saleableItems_FIELD = "saleableItems";
    private String name;
    private String businessSector;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Efectivo> paymentMethods;
    @OneToMany(cascade = CascadeType.ALL)
    private List<DayOfWeekWithTimeRange> daysAndHoursOpen;
    private String arrivalRange;
    @OneToMany(cascade = CascadeType.ALL)
    private List<SaleableItem> saleableItems;

    //For springboot serializer
    public Commerce() {
    }

    public Commerce(String name, String businessSector, Address address, List<Efectivo> paymentMethods,
                    List<DayOfWeekWithTimeRange> daysAndHoursOpen, String arrivalRange) {
        this.name = name;
        this.businessSector = businessSector;
        this.address = address;
        this.paymentMethods = paymentMethods;
        this.daysAndHoursOpen = daysAndHoursOpen;
        this.arrivalRange = arrivalRange;
        this.saleableItems = new ArrayList<>();
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

    public List<Efectivo> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<Efectivo> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public List<DayOfWeekWithTimeRange> getDaysAndHoursOpen() {
        return daysAndHoursOpen;
    }

    public void setDaysAndHoursOpen(List<DayOfWeekWithTimeRange> daysAndHoursOpen) {
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

    public boolean containsProductWithId(Long productId) {
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

    private SaleableItem findByProductId(Long productId) {
        return saleableItems
            .stream()
            .filter(saleableItem -> saleableItem.sameProductId(productId))
            .findFirst()
            .get();
    }

    public SaleableItem removeSaleableItemByProductId(Long productId) {
        SaleableItem saleableItem = findByProductId(productId);
        saleableItems.remove(saleableItem);
        return saleableItem;
    }

    public void updateSaleableItem(SaleableItem toUpdate) {
        Stream<SaleableItem> saleableItemStream = saleableItems.stream().filter(saleableItem -> saleableItem.sameProductId(toUpdate.getProductId()));
        Optional<SaleableItem> saleableItemOptional =
            Nary.create(saleableItemStream).findFirstNary();
        if (saleableItemOptional.isAbsent()) {
            String errorMessage = "No se puede actualizar. No existe el producto con id: [" + toUpdate.getProductId() + "]";
            throw new RuntimeException(errorMessage);
        }
        SaleableItem saleableItem = saleableItemOptional.get();
        saleableItem.updateWith(toUpdate);
    }

    public Product getProductById(Long saleableId) {
        Optional<SaleableItem> saleableItemOptional = Nary
            .create(saleableItems)
            .filterOptional(saleableItem -> saleableItem.sameProductId(saleableId));
        SaleableItem saleableItem = saleableItemOptional.get();
        return saleableItem.getProduct();
    }

    public boolean containsProductByName(String productToFind) {
        long count = saleableItems
            .stream()
            .filter(saleableItem -> saleableItem.containsInProductName(productToFind)).count();
        return count != 0;
    }

    public boolean isOpenIn(LocalDateTime suggestedDateTime) {
        return daysAndHoursOpen.stream().anyMatch(rangeOpen -> rangeOpen.match(suggestedDateTime));
    }

    public String daysAndHoursOpenAsString() {
        List<String> ranges = daysAndHoursOpen
            .stream()
            .map(DayOfWeekWithTimeRange::rangeToString)
            .collect(Collectors.toList());
        String startMessage = "Rangos abierto: \n";
        String rangesJoinning = String.join("\n", ranges);
        return startMessage + rangesJoinning;
    }
}
