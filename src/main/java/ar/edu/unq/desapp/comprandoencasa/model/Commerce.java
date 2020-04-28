package ar.edu.unq.desapp.comprandoencasa.model;

import java.util.List;

public class Commerce {
    private final String businessSector;
    private final String adress;
    private final List<String> paymentMethods;
    private final List<String> daysAndHoursOpen;
    private final String range;

    public Commerce(String businessSector, String adress, List<String> paymentMethods, List<String> daysAndHoursOpen,
                    String range) {
        this.businessSector = businessSector;
        this.adress = adress;
        this.paymentMethods = paymentMethods;
        this.daysAndHoursOpen = daysAndHoursOpen;
        this.range = range;
    }
}
