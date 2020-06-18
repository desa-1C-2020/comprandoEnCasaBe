package ar.edu.unq.desapp.comprandoencasa.controllers.to;

import javax.validation.constraints.NotNull;
import java.util.List;

public class SellerTO {
    public static final String commerceName_FIELD = "commerceName";
    public static final String commerceAddress_FIELD = "commerceAddress";
    @NotNull
    private Long userId;
    @NotNull
    private String commerceName;
    @NotNull
    private String commerceBusinessSector;
    @NotNull
    private AddressTO commerceAddress;
    private List<PaymentMethodTO> paymentMethods;
    private List<String> daysAndHoursOpen;
    private String arrivalRange;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCommerceName() {
        return commerceName;
    }

    public void setCommerceName(String commerceName) {
        this.commerceName = commerceName;
    }

    public String getBusinessSector() {
        return commerceBusinessSector;
    }

    public void setCommerceBusinessSector(String commerceBusinessSector) {
        this.commerceBusinessSector = commerceBusinessSector;
    }

    public AddressTO getCommerceAddress() {
        return commerceAddress;
    }

    public void setCommerceAddress(AddressTO commerceAddress) {
        this.commerceAddress = commerceAddress;
    }

    public List<PaymentMethodTO> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethodTO> paymentMethods) {
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


