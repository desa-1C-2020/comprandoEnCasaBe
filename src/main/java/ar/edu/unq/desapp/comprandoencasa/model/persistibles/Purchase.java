package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import ar.edu.unq.desapp.comprandoencasa.support.PersistibleSupport;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "purchase")
public class Purchase extends PersistibleSupport {
    @OneToOne
    private ShoppingList shoppingList;
    private PaymentMethod paymentMethod;
    @OneToOne(cascade = CascadeType.ALL)
    private DeliveryOption deliveryOption;
    private BigDecimal total; //total del carrito, + un posible cargo por envío. lo calcula el front
    private LocalDateTime creationDateTime;
    @OneToOne
    private User user;

    public Purchase() {
        //Used for hibernate
    }

    public Purchase(ShoppingList shoppingList, PaymentMethod paymentMethod, DeliveryOption deliveryOption,
                    BigDecimal total, LocalDateTime creationDateTime, User user) {
        this.shoppingList = shoppingList;
        this.paymentMethod = paymentMethod;
        this.deliveryOption = deliveryOption;
        this.total = total;
        this.creationDateTime = creationDateTime;
        this.user = user;
    }

    public DeliveryOption getDeliveryOption() {
        return deliveryOption;
    }

    public void setDeliveryOption(DeliveryOption deliveryOption) {
        this.deliveryOption = deliveryOption;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}