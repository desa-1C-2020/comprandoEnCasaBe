package ar.edu.unq.desapp.comprandoencasa.controllers.to;

import java.util.List;

public class ItemByCommerceTo {
    private String commerceId;
    private List<ShoppingListItemTo> items;

    public String getCommerceId() {
        return commerceId;
    }

    public void setCommerceId(String commerceId) {
        this.commerceId = commerceId;
    }

    public List<ShoppingListItemTo> getItems() {
        return items;
    }

    public void setItems(List<ShoppingListItemTo> items) {
        this.items = items;
    }
}
