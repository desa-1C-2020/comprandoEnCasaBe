package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import ar.edu.unq.desapp.comprandoencasa.support.PersistibleSupport;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "delivery_option")
public class DeliveryOption extends PersistibleSupport {
    private DeliveryOptionType deliveryOptionType;
    private LocalDateTime suggestedDay;

    public DeliveryOption() {
    }

    public DeliveryOption(DeliveryOptionType deliveryOptionType, LocalDateTime suggestedDay) {
        this.deliveryOptionType = deliveryOptionType;
        this.suggestedDay = suggestedDay;
    }
}
