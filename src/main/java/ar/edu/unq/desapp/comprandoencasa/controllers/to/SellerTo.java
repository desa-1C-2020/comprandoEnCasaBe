package ar.edu.unq.desapp.comprandoencasa.controllers.to;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.PaymentMethod;

import javax.validation.constraints.NotNull;
import java.util.List;

public class SellerTo {
    @NotNull
    private String userId;
    @NotNull
    private String commerceName;
    @NotNull
    private String commerceBusinessSector;
    @NotNull
    private AddressTo commerceAddress;
    private List<PaymentMethod> paymentMethods;
    private List<String> daysAndHoursOpen;
    private String arrivalRange;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCommerceName() {
        return commerceName;
    }

    public void setCommerceName(String commerceName) {
        this.commerceName = commerceName;
    }

    public String getCommerceBusinessSector() {
        return commerceBusinessSector;
    }

    public void setCommerceBusinessSector(String commerceBusinessSector) {
        this.commerceBusinessSector = commerceBusinessSector;
    }

    public AddressTo getCommerceAddress() {
        return commerceAddress;
    }

    public void setCommerceAddress(AddressTo commerceAddress) {
        this.commerceAddress = commerceAddress;
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
}


