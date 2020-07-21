package ar.edu.unq.desapp.comprandoencasa.extensions;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.ItemByCommerceTO;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.ShoppingListItemTO;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.ShoppingListTO;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.ItemsByCommerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.ShoppingList;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.ShoppingListItem;

import java.util.List;
import java.util.stream.Collectors;

public class ObjectMapper {
    public List<ShoppingListTO> mapToShoppingListsTo(List<ShoppingList> shoppingLists) {
        return shoppingLists.stream().map(shoppingList -> mapToShoppingListT(shoppingList)).collect(Collectors.toList());
    }

    private ShoppingListTO mapToShoppingListT(ShoppingList shoppingList) {
        List<ItemByCommerceTO> itemByCommerceTos = shoppingList
            .getItemsByCommerce()
            .stream()
            .map(itemByCommerce -> mapToItemByCommerceTo(itemByCommerce))
            .collect(Collectors.toList());

        ShoppingListTO shoppingListTo = new ShoppingListTO();
        shoppingListTo.setTotal(shoppingList.getTotal());
        shoppingListTo.setItemByCommerceTo(itemByCommerceTos);
        return null;
    }

    private ItemByCommerceTO mapToItemByCommerceTo(ItemsByCommerce itemByCommerce) {
        Long commerceId = itemByCommerce.getCommerce().getId();
        List<ShoppingListItemTO> shoppingListItemTOS = itemByCommerce
            .getItems()
            .stream()
            .map(this::mapToShoppingListItemTo)
            .collect(Collectors.toList());
        ItemByCommerceTO itemByCommerceTo = new ItemByCommerceTO();
        itemByCommerceTo.setCommerceId(commerceId);

        itemByCommerceTo.setItems(shoppingListItemTOS);
        return itemByCommerceTo;
    }

    private ShoppingListItemTO mapToShoppingListItemTo(ShoppingListItem shoppingListItem) {
        ShoppingListItemTO shoppingListItemTo = new ShoppingListItemTO();
        shoppingListItemTo.setProductId(shoppingListItem.getProduct().getId());
        shoppingListItemTo.setQuantity(shoppingListItem.getQuantity());
        shoppingListItemTo.setPrice(shoppingListItem.getPrice());
        return shoppingListItemTo;
    }
}
