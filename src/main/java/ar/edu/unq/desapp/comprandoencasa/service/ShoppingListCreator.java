package ar.edu.unq.desapp.comprandoencasa.service;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.ItemByCommerceTo;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.ShoppingListItemTO;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.ShoppingListTo;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.ItemsByCommerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Product;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.ShoppingList;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.ShoppingListItem;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.repositories.CommerceRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.ShoppingListRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingListCreator {

    private UserFinder userFinder;
    private ShoppingListRepository shoppingListRepository;
    private CommerceRepository commerceRepository;

    public ShoppingListCreator(UserFinder userFinder, ShoppingListRepository shoppingListRepository,
                               CommerceRepository commerceRepository) {
        this.userFinder = userFinder;
        this.shoppingListRepository = shoppingListRepository;
        this.commerceRepository = commerceRepository;
    }

    public ShoppingList createAndSave(ShoppingListTo shoppingListTo) {
        //Aca deberia ver de guardar los errores, pero si crear la lista con los que no fallaron.
        //Revisar esto pero por ahora si falla uno, fallan todos.
        String userId = shoppingListTo.getUserId();
        User user = userFinder.findUserById(userId);
        Date creationDateTime = new Date();
        BigDecimal total = shoppingListTo.getTotal();

        List<ItemByCommerceTo> itemByCommerceTo = shoppingListTo.getItemByCommerceTo();

        List<ItemsByCommerce> itemsByCommerces = itemByCommerceTo
            .stream()
            .map(this::getItemByCommerce)
            .collect(Collectors.toList());

        ShoppingList shoppingList = new ShoppingList(user, itemsByCommerces, total, creationDateTime);
        shoppingListRepository.save(shoppingList);
        return shoppingList;
    }

    private ItemsByCommerce getItemByCommerce(ItemByCommerceTo itemByCommerceTo) {
        String commerceId = itemByCommerceTo.getCommerceId();
        List<ShoppingListItemTO> itemsTo = itemByCommerceTo.getItems();
        Commerce commerce = getCommerceOrThrow(commerceId);
        List<ShoppingListItem> shoppingList = itemsTo
            .stream()
            .map(item -> getShoppingListItem(item, commerce))
            .collect(Collectors.toList());

        return new ItemsByCommerce(commerce, shoppingList);
    }

    private ShoppingListItem getShoppingListItem(ShoppingListItemTO shoppingListItemTo, Commerce commerce) {
        String productId = shoppingListItemTo.getProductId();
        if (!commerce.containsProductWithId(productId)) {
            String errorMessage = "No existe el producto con id: [" + productId + "] en el comercio [" +
                commerce.getName() + "]. No se puede crear la lista de compras";
            throw new RuntimeException(errorMessage);
        }
        Product product = commerce.getProductById(productId);
        return new ShoppingListItem(product, shoppingListItemTo.getQuantity(), shoppingListItemTo.getPrice());
    }

    private Commerce getCommerceOrThrow(String commerceId) {
        Optional<Commerce> commerceOptional = commerceRepository.getById(commerceId);
        if (commerceOptional.isAbsent()) {
            throw new RuntimeException("No existe el comercio con id: [" + commerceId + "]. No se puede crear la lista de compras");
        }

        return commerceOptional.get();
    }

    public List<ShoppingList> recreateAllListsForUserWithId(String userId) {
        User user = userFinder.findUserById(userId);
        return shoppingListRepository.getAllByUser(user);
    }
}
