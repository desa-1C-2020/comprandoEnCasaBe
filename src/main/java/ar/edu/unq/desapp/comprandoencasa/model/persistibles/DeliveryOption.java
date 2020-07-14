package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import ar.edu.unq.desapp.comprandoencasa.support.PersistibleSupport;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "delivery_option")
public class DeliveryOption extends PersistibleSupport {
    @Enumerated(EnumType.STRING)
    private DeliveryOptionType deliveryOptionType;
    private LocalDateTime suggestedDay;

    public DeliveryOption() {
    }

    public DeliveryOption(DeliveryOptionType deliveryOptionType, LocalDateTime suggestedDay) {
        this.deliveryOptionType = deliveryOptionType;
        this.suggestedDay = suggestedDay;
    }

    public LocalDateTime getSuggestedDay() {
        return suggestedDay;
    }

    public void setSuggestedDay(LocalDateTime suggestedDay) {
        this.suggestedDay = suggestedDay;
    }

    public DeliveryOptionType getDeliveryOptionType() {
        return deliveryOptionType;
    }

    public void setDeliveryOptionType(DeliveryOptionType deliveryOptionType) {
        this.deliveryOptionType = deliveryOptionType;
    }
}
