package ar.edu.unq.desapp.comprandoencasa.controllers.to;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.PaymentMethod;

import java.math.BigDecimal;

public class PurchaseTO {
    private ShoppingListTo shoppingListTo;
    private PaymentMethod selectedPaymentMethod;
    private DeliveryOptionTO deliveryOption;
    private BigDecimal total; //total del carrito, + un posible cargo por envío.

    public DeliveryOptionTO getDeliveryOption() {
        return deliveryOption;
    }

    public void setDeliveryOption(DeliveryOptionTO deliveryOption) {
        this.deliveryOption = deliveryOption;
    }

    public ShoppingListTo getShoppingListTo() {
        return shoppingListTo;
    }

    public void setShoppingListTo(ShoppingListTo shoppingListTo) {
        this.shoppingListTo = shoppingListTo;
    }

    public PaymentMethod getSelectedPaymentMethod() {
        return selectedPaymentMethod;
    }

    public void setSelectedPaymentMethod(PaymentMethod selectedPaymentMethod) {
        this.selectedPaymentMethod = selectedPaymentMethod;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
