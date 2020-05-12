package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import java.util.List;

public class ItemsByCommerce {
    private Commerce commerce;
    private List<ShoppingListItem> items;

    public ItemsByCommerce(Commerce commerce, List<ShoppingListItem> shoppingList) {
        this.commerce = commerce;
        this.items = shoppingList;
    }
}
