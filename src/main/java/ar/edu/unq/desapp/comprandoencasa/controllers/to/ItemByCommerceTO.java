package ar.edu.unq.desapp.comprandoencasa.controllers.to;

import java.util.List;

public class ItemByCommerceTO {
    private Long commerceId;
    private List<ShoppingListItemTO> items;

    public Long getCommerceId() {
        return commerceId;
    }

    public void setCommerceId(Long commerceId) {
        this.commerceId = commerceId;
    }

    public List<ShoppingListItemTO> getItems() {
        return items;
    }

    public void setItems(List<ShoppingListItemTO> items) {
        this.items = items;
    }
}
