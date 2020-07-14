package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import java.util.List;

public enum SaleStatus {
    PENDING,
    PAID_OUT {
        @Override
        public void affectStock(Commerce commerce, List<ShoppingListItem> items) {
            //Do Nothing
        }
    }, CANCELED {
        @Override
        public void affectStock(Commerce commerce, List<ShoppingListItem> items) {
            commerce.increaseStock(items);
        }
    };

    public void affectStock(Commerce commerce, List<ShoppingListItem> items) {
        commerce.decreaseStock(items);
    }
}
