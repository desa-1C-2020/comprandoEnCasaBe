package ar.edu.unq.desapp.comprandoencasa.controllers.to;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.PaymentMethod;

import java.util.List;

public class CommerceTO {
    private String name;
    private String businessSector;
    private AddressTO address;
    private List<PaymentMethod> paymentMethods;
    private List<DayOfWeekWithTimeRangeTO> daysAndHoursOpen;
    private String arrivalRange;

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public List<DayOfWeekWithTimeRangeTO> getDaysAndHoursOpen() {
        return daysAndHoursOpen;
    }

    public void setDaysAndHoursOpen(List<DayOfWeekWithTimeRangeTO> daysAndHoursOpen) {
        this.daysAndHoursOpen = daysAndHoursOpen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessSector() {
        return businessSector;
    }

    public void setBusinessSector(String businessSector) {
        this.businessSector = businessSector;
    }

    public AddressTO getAddress() {
        return address;
    }

    public void setAddress(AddressTO address) {
        this.address = address;
    }

    public String getArrivalRange() {
        return arrivalRange;
    }

    public void setArrivalRange(String arrivalRange) {
        this.arrivalRange = arrivalRange;
    }
}


