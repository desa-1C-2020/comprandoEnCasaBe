package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import java.util.List;

public enum PurchaseStatus {
    PENDING, PAID_OUT, CANCELED;

    public void affectStock(Commerce commerce, List<ShoppingListItem> items) {
        commerce.affectStock(items);
    }
}
