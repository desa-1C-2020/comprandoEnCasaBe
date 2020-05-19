package ar.edu.unq.desapp.comprandoencasa.controllers.to;

import java.util.List;

public class ItemByCommerceTo {
    private String commerceId;
    private List<ShoppingListItemTO> items;

    public String getCommerceId() {
        return commerceId;
    }

    public void setCommerceId(String commerceId) {
        this.commerceId = commerceId;
    }

    public List<ShoppingListItemTO> getItems() {
        return items;
    }

    public void setItems(List<ShoppingListItemTO> items) {
        this.items = items;
    }
}
