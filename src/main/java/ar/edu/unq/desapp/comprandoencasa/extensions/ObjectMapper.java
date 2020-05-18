package ar.edu.unq.desapp.comprandoencasa.extensions;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.ItemByCommerceTo;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.RegisterUserTO;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.SaleableItemTo;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.ShoppingListItemTo;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.ShoppingListTo;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.ItemsByCommerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Product;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.SaleableItem;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.ShoppingList;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.ShoppingListItem;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;

import java.util.List;
import java.util.stream.Collectors;

public class ObjectMapper {
    public User mapToToUser(RegisterUserTO userto) {
        return User.create(userto.getName(), userto.getSurname(), userto.getEmail());
    }

    public SaleableItem mapToSaleableProduct(SaleableItemTo saleableItemTo) {
        Product product = new Product(saleableItemTo.getName(), saleableItemTo.getBrand(), saleableItemTo.getImageUrl());
        product.setId(saleableItemTo.getProductId());
        return new SaleableItem(saleableItemTo.getStock(), saleableItemTo.getPrice(), product);
    }

    public List<ShoppingListTo> mapToShoppingListsTo(List<ShoppingList> shoppingLists) {
        return shoppingLists.stream().map(shoppingList -> mapToShoppingListT(shoppingList)).collect(Collectors.toList());
    }

    private ShoppingListTo mapToShoppingListT(ShoppingList shoppingList) {
        List<ItemByCommerceTo> itemByCommerceTos = shoppingList
            .getItemsByCommerce()
            .stream()
            .map(itemByCommerce -> mapToItemByCommerceTo(itemByCommerce))
            .collect(Collectors.toList());
        ShoppingListTo shoppingListTo = new ShoppingListTo();
        shoppingListTo.setTotal(shoppingList.getTotal());
        shoppingListTo.setCreationDateTime(shoppingList.getCreationDateTime());
        shoppingListTo.setUserId(shoppingList.getUser().getUid());
        shoppingListTo.setItemByCommerceTo(itemByCommerceTos);
        return null;
    }

    private ItemByCommerceTo mapToItemByCommerceTo(ItemsByCommerce itemByCommerce) {
        String commerceId = itemByCommerce.getCommerce().getId();
        List<ShoppingListItemTo> shoppingListItemTos = itemByCommerce
            .getItems()
            .stream()
            .map(this::mapToShoppingListItemTo)
            .collect(Collectors.toList());
        ItemByCommerceTo itemByCommerceTo = new ItemByCommerceTo();
        itemByCommerceTo.setCommerceId(commerceId);

        itemByCommerceTo.setItems(shoppingListItemTos);
        return itemByCommerceTo;
    }

    private ShoppingListItemTo mapToShoppingListItemTo(ShoppingListItem shoppingListItem) {
        ShoppingListItemTo shoppingListItemTo = new ShoppingListItemTo();
        shoppingListItemTo.setProductId(shoppingListItem.getProduct().getId());
        shoppingListItemTo.setQuantity(shoppingListItem.getQuantity());
        shoppingListItemTo.setPrice(shoppingListItem.getPrice());
        return shoppingListItemTo;
    }
}
