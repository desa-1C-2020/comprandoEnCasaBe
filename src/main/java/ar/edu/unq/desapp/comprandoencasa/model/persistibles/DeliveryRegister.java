package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import ar.edu.unq.desapp.comprandoencasa.support.PersistibleSupport;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "delivery_register")
public class DeliveryRegister extends PersistibleSupport {
    @OneToOne
    private User user;
    private Boolean reserved;
    private Boolean delivered;
    private LocalDateTime createdDateTime;
    private LocalDate deliverDate;

    public DeliveryRegister() {
    }

    public DeliveryRegister(User user, Boolean reserved, Boolean delivered, LocalDate deliverDate) {
        this.user = user;
        this.reserved = reserved;
        this.delivered = delivered;
        this.deliverDate = deliverDate;
        this.createdDateTime = LocalDateTime.now();
    }

    public Boolean getReserved() {
        return reserved;
    }

    public void setReserved(Boolean reserved) {
        this.reserved = reserved;
    }

    public Boolean getDelivered() {
        return delivered;
    }

    public void setDelivered(Boolean delivered) {
        this.delivered = delivered;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(LocalDate deliverDate) {
        this.deliverDate = deliverDate;
    }
}
